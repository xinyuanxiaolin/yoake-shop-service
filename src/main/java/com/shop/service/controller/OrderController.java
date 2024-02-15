package com.shop.service.controller;

import com.shop.service.pojo.Result;
import com.shop.service.pojo.order.OrderCreateParams;
import com.shop.service.pojo.order.Orders;
import com.shop.service.pojo.order.orderPre.OrderPreResult;
import com.shop.service.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/member/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*填写订单-获取直接购买订单*/
    @GetMapping("pre/now")
    public Result getOrderPreNow(@RequestParam(value = "goodsId") Integer goodsId,
                                 @RequestParam(value = "count") Integer count,
                                 @RequestParam(value = "addressId") Integer addressId){

//        log.info("{},{},{}",goodsId,count,addressId);
        OrderPreResult res = orderService.getPreNow(goodsId,count,addressId);
        return Result.success(res);
    }
    /*填写订单-获取购物车内预付订单*/
    @GetMapping("/pre")
    public  Result getOrderPre(){
        OrderPreResult res = orderService.getPre();
        return Result.success(res);

    }
    /*提交订单*/
    @PostMapping
    public Result postOrder(@RequestBody OrderCreateParams data){
        Orders res = orderService.postOrder(data);

        return Result.success(res);
    }

    /*获取订单详情*/
    @GetMapping("/{id}")
    public Result getOrderById(@PathVariable Integer id){

        Orders res =orderService.getOrderById(id);
        return Result.success(res);
    }


}
