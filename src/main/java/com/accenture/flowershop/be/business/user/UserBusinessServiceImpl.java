package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.order.OrderPositionBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderPosition;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserListDTO;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    private static final Logger log = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private OrderPositionBusinessService orderPositionBusinessService;


    @Override
    public void save(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(Long userId) {
        User user = findUserById(userId);
        if(user == null) return  null;
        for(Order o : user.getOrderList()){
            o.getBasketOrder().size();
         }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getUserList();
    }

    @Override
    public void updateDiscountOfUser(long userId, int userDiscount) {
        User userEntity = findUserById(userId);
        userEntity.setDiscount(userDiscount);
        userDAO.updateUser(userEntity);
    }

    @Override
    @Transactional
    public User userVerification(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            log.warn("Wrong login or password" + login);
            return null;
        }
        User user = findUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                log.debug("Login Access" + login);
                user.getOrderList().size();
                for(Order o : user.getOrderList())
                    o.getBasketOrder().size();
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public User userRegistration(User user) {
        if (userDAO.findUserByLogin(user.getLogin()) == null) {
            userDAO.saveUser(user);
            log.debug("Registration successful:" + user.getLogin());
            return user;
        }
        log.warn("Registration invalid: " + user.getLogin());
        return null;
    }

    @Override
    public Boolean checkLogin(String login) {
        log.debug("Check login: " + login);
        User user = userDAO.findUserByLogin(login);
        if (user == null) return false;
        return true;
    }

    @Override
    public double getDiscountOfUser(User user) {
        if(user.getDiscount() == 0){
            return 0D;
        }
        return (double) (user.getDiscount()*0.01);
    }

    @Override
    @Transactional
    public boolean payOrder(Long userId, Long orderId) {
        User user = findUserById(userId);
        user.getOrderList().size();
        Order order = orderBusinessService.getOrderById(orderId);
        order.getBasketOrder().size();
        if (user.getBalance().compareTo(order.getTotalPrice()) < 0) {
            return false;
        }
        order.setStatusOrder(StatusOrder.PAID);
        orderBusinessService.updateOrder(order);
        updateUser(pay(user,order.getTotalPrice()));
        return true;
    }
    @Override
    public User pay(User user, BigDecimal price){
        user.setBalance(user.getBalance().subtract(price));
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        log.debug("FindUserByLogin" + login);
        return userDAO.findUserByLogin(login);
    }

    @Override
    public User findUserById(Long userId) {
        return userDAO.findUserById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        User updateUser = findUserById(user.getId());
        if(user.getLogin() != null) updateUser.setLogin(user.getLogin());
        if(user.getPassword() != null) updateUser.setPassword(user.getPassword());
        if(user.getBalance() != null) updateUser.setBalance(user.getBalance());
        if(user.getDiscount() != null) updateUser.setDiscount(user.getDiscount());
        if(user.getAddress() != null) updateUser.setAddress(user.getAddress());
        if(user.getFirstName() != null) updateUser.setFirstName(user.getFirstName());
        if(user.getLastName() != null) updateUser.setLastName(user.getLastName());
        userDAO.updateUser(updateUser);
        return true;
    }
}
