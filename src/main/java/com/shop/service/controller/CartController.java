package com.shop.service.controller;

import com.shop.service.pojo.Cart;
import com.shop.service.pojo.Result;
import com.shop.service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /*加入购物车*/
    @PostMapping
    public Result addCart(@RequestBody Cart cart){
        cartService.add(cart.getGoodsId(),cart.getCount());
        return Result.success();
    }

    /*获取购物车列表*/
    @GetMapping
    public Result getCart(){
        List<Cart> res = cartService.get();
        return Result.success(res);
    }
    /* 删除购物车单品/清空购物车 */
    @DeleteMapping
    public Result deleteCart(@RequestBody Map<String, ArrayList> data){
        cartService.delete(data.get("ids"));
        return Result.success();

    }

    /*
      修改购物单品
     selected 选中状态 count 商品数量
     */
    @PutMapping("/{goodsId}")
    public Result putCart(@PathVariable String goodsId,@RequestBody Cart cart){
        cartService.put(goodsId,cart);
        return Result.success();
    }


   /* 购物车全选/取消全选 */
    @PutMapping("/selected")
    public Result putSelected(@RequestBody Cart cart){
        cartService.putSelected(cart);
        return Result.success();
    }





}
