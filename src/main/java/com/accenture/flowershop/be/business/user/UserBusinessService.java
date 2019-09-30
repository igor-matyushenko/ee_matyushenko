package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserBusinessService {

    List<User> getAllUser();

    BigDecimal userSumDiscount(BigDecimal priceFlower, int discountUser, Long quantityToBasket);

    User userVerification(String login, String password);

    User userRegistration(User user);

    Boolean checkLogin(String login);

    boolean payCreatedOrder(String login, Long idOrder);

    User findUserByLogin(String loginUser);

    void updateUser(User user);


}
