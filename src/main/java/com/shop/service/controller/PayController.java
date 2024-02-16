package com.shop.service.controller;

import com.shop.service.pojo.Result;
import com.shop.service.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    /*模拟支付-开发环境使用*/
    @GetMapping("/mock")
    public Result getPayMock(@RequestParam(name = "orderId") String orderId ){
        payService.payMock(orderId);
        return Result.success();
    }


}
