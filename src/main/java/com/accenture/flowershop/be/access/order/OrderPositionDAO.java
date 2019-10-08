package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.OrderPosition;

import java.util.List;

public interface OrderPositionDAO {


    List<OrderPosition> getActualOrderPositionByUserId(Long idUser, Long idOrder);


}
