package com.shop.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.common.JwtToken;
import com.shop.service.mapper.CartMapper;
import com.shop.service.pojo.Cart;
import com.shop.service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private JwtToken jwtToken;

//加入购物车
    @Override
    public void add(String skuId, Integer count) {


    }

//获取购物车列表
    @Override
    public List<Cart> get() {
        QueryWrapper<Cart> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_id",jwtToken.getUserIdByToken());
        List<Cart> res = cartMapper.selectList(queryWrapper);
        //接下来拼接attrsText字段回显给前端


        return null;
    }
}
