package com.shop.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shop.service.common.JwtToken;
import com.shop.service.mapper.CartMapper;
import com.shop.service.pojo.Cart;
import com.shop.service.pojo.goods.GoodsItem;
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
    public void add(String goodsId, Integer count) {
        //首先通过goodsId获取到基本goods信息
        GoodsItem goodsItem = cartMapper.getGoodsInfo(goodsId);
        //获取userId
        Integer userId = jwtToken.getUserIdByToken();
        //判断是否存在购物车相同的商品goodsId
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",goodsId).eq("user_id",userId);
        Cart e =cartMapper.selectOne(queryWrapper);
        if(e==null){
            //拼接
            Cart cart =new Cart(null,goodsId,userId.toString(),goodsItem.getName(),goodsItem.getPicture(),
                    count,goodsItem.getPrice(),goodsItem.getNowPrice(),goodsItem.getStock(),false);
            //添加
            cartMapper.insert(cart);
        }else{
            //存在,原有数量加count
            e.setCount(e.getCount()+count);
            cartMapper.updateById(e);
        }

    }

//获取购物车列表
    @Override
    public List<Cart> get() {
        QueryWrapper<Cart> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_id",jwtToken.getUserIdByToken());
        List<Cart> res = cartMapper.selectList(queryWrapper);


        return res;
    }

//删除购物车单品/清空购物车
    @Override
    public void delete(List<String> ids) {
        cartMapper.deleteBatchIds(ids);
    }

//修改购物单品
    @Override
    public void put(String id, Cart cart) {
        Integer userId = jwtToken.getUserIdByToken();
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id).eq("user_id",userId).set( cart.getCount()!=null,"count",cart.getCount())
                .set(cart.getSelected()!=null,"selected",cart.getSelected());
        cartMapper.update(null,updateWrapper);
    }

//购物车全选/取消全选
    @Override
    public void putSelected(Cart cart) {
        //将userId相关的购物车的所有selected改为传来的对应boolean值
        UpdateWrapper<Cart> cartUpdateWrapper = new UpdateWrapper<>();
        cartUpdateWrapper.eq("user_id",jwtToken.getUserIdByToken()).set( cart.getSelected() !=null,"selected",cart.getSelected());
        cartMapper.update(null,cartUpdateWrapper);

    }
}
