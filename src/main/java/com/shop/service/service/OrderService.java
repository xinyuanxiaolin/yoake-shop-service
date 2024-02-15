package com.shop.service.service;

import com.shop.service.pojo.order.OrderCreateParams;
import com.shop.service.pojo.order.Orders;
import com.shop.service.pojo.order.orderPre.OrderPreResult;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderService {



    OrderPreResult getPreNow(Integer goodsId, Integer count, Integer addressId);

    OrderPreResult getPre();

    Orders postOrder(OrderCreateParams data);

    Orders getOrderById(Integer id);
}
