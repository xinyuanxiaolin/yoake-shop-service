package com.shop.service.controller;

import com.shop.service.pojo.Cart;
import com.shop.service.pojo.Result;
import com.shop.service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /*加入购物车*/
    @PostMapping
    public Result addCart(@RequestBody Cart cart){
        cartService.add(cart.getSkuId(),cart.getCount());
        return Result.success();
    }

    /*获取购物车列表*/
    @GetMapping
    public Result getCart(){
        List<Cart> res = cartService.get();
        return Result.success(res);
    }





}
