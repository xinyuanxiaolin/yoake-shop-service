package com.shop.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.service.pojo.order.OrderCreateParams;
import com.shop.service.pojo.order.Orders;
import com.shop.service.pojo.order.orderPre.OrderPreResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderService extends IService<Orders> {



    OrderPreResult getPreNow(Integer goodsId, Integer count, Integer addressId);

    OrderPreResult getPre();

    Orders postOrder(OrderCreateParams data);

    Orders getOrderById(Integer id);

    void cancelOrder(Integer id, String cancelReason);

    void deleteOrder(List<String> ids);
}
