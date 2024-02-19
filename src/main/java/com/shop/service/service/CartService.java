package com.shop.service.service;

import com.shop.service.pojo.Cart;

import java.util.List;

public interface CartService {


    void add(String goodsId, Integer count);

    List<Cart> get();

    void delete(List<String> ids);

    void put(String id, Cart cart);

    void putSelected(Cart cart);
}
