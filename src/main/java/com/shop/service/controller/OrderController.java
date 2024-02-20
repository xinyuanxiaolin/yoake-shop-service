package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.common.JwtToken;
import com.shop.service.pojo.Result;
import com.shop.service.pojo.order.OrderCreateParams;
import com.shop.service.pojo.order.Orders;
import com.shop.service.pojo.order.orderList.OrderListResult;
import com.shop.service.pojo.order.orderPre.OrderPreResult;
import com.shop.service.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/member/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private JwtToken jwtToken;

    /** 预购模块*/

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

    /** 订单增删改查模块*/
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

    /*获取订单列表*/
    @GetMapping
    public Result getOrderList(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam Integer orderState){
        //分页查询获取数据
        Page<Orders> pages =new Page<>(page,pageSize);
        QueryWrapper<Orders> queryWrapper =new QueryWrapper<>();
        //查询需判断两种情况,当orderState为0时应该查询用户相关的所有订单
        if(orderState==0){
            queryWrapper.eq("user_id",jwtToken.getUserIdByToken());
        }else{
            queryWrapper.eq("order_state",orderState).eq("user_id",jwtToken.getUserIdByToken());
        }
        //获得分页查询后的数据
        Page<Orders> orders = orderService.page(pages,queryWrapper);
        //先进行基本的信息分配
        OrderListResult res = new OrderListResult(orders.getTotal(),null,orders.getCurrent(),orders.getPages(),orders.getSize());
        //接下来对商品内的数据进行封装
        //直接调用通过id获取订单详情
        List<Orders> ordersList = orders.getRecords();
        List<Orders> jiegou = ordersList.stream().map(v->orderService.getOrderById(v.getId())).collect(Collectors.toList());
        res.setItems(jiegou);
        return Result.success(res);
    }

    /*取消订单*/
    @PutMapping("/{id}/cancel")
    public Result cancelOrderById(@PathVariable Integer id ,@RequestBody Orders orders){

        orderService.cancelOrder(id,orders.getCancelReason());
        return Result.success();
    }

    /*删除订单*/
    @DeleteMapping
    public Result deleteOrderByIds(@RequestBody Map<String, List<String>> data){
        orderService.deleteOrder(data.get("ids"));
        return Result.success();

    }

    /**货物运输模块*/

    //模拟发货
    @GetMapping("consignment/{id}")
    public Result getOrderConsignment(@PathVariable String id){
        //将待发货(2)装变为待收货(3)
        orderService.changeOrderState(id,3);
        return Result.success();
    }

    //确认收货
    @PutMapping("/{id}/receipt")
    public Result getOrderReceipt(@PathVariable String id){
        //将待收货(3)装变为待评价(4)
        orderService.changeOrderState(id,4);
        return Result.success();
    }

    /** 管理员模块*/
    /*获取订单列表条件查询管理员版*/
    @GetMapping("/list")
    public Result getAOrderList(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam Integer orderState,String searchText){
        //分页查询获取数据
        Page<Orders> pages =new Page<>(pageNum,pageSize);
        QueryWrapper<Orders> queryWrapper =new QueryWrapper<>();
        //查询需判断两种情况,当orderState为0时应该查询用户相关的所有订单
        if(orderState!=0) {
            queryWrapper.eq("order_state", orderState);
        }
        //条件查询
        if(searchText.length()!=0){
            queryWrapper.and(wrapper -> wrapper.like("id", searchText).or().like("receiver_contact", searchText)
                    .or().like("receiver_mobile", searchText));

        }
        //获得分页查询后的数据
        Page<Orders> orders = orderService.page(pages,queryWrapper);
        //先进行基本的信息分配
        OrderListResult res = new OrderListResult(orders.getTotal(),null,orders.getCurrent(),orders.getPages(),orders.getSize());
        //接下来对商品内的数据进行封装
        //直接调用通过id获取订单详情
        List<Orders> ordersList = orders.getRecords();
        List<Orders> jiegou = ordersList.stream().map(v->orderService.getOrderById(v.getId())).collect(Collectors.toList());
        res.setItems(jiegou);
        return Result.success(res);
    }

}
