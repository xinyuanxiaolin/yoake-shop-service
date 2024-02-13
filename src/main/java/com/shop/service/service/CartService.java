package com.shop.service.service;

import com.shop.service.pojo.Cart;

import java.util.List;

public interface CartService {


    void add(String skuId, Integer count);

    List<Cart> get();
}
