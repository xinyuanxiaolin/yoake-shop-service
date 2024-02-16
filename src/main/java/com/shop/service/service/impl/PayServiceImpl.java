package com.shop.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shop.service.common.JwtToken;
import com.shop.service.mapper.GoodsMapper;
import com.shop.service.mapper.PayMapper;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.order.OrderProducts;
import com.shop.service.pojo.order.Orders;
import com.shop.service.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private JwtToken jwtToken;

    //模拟支付
    @Override
    public void payMock(String orderId) {
        //实现模拟支付,即通过订单id把订单状态修改成待发货(2)
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(orderId!=null,"id",orderId).eq("user_id",jwtToken.getUserIdByToken()).set("order_state",2);
        payMapper.update(null,updateWrapper);
        //对应商品的的库存也要减少
        //首先先找到对应关联表的goods_id和quantity
        List<OrderProducts> orderProducts = payMapper.getOrderProducts(orderId);
        //通过拿到的goods_id去更新category商品表

        orderProducts.forEach(v->{
            CategoryTopItem categoryTopItem = goodsMapper.selectById(v.getGoodsId());
            categoryTopItem.setStock(categoryTopItem.getStock()-v.getQuantity());
            goodsMapper.updateById(categoryTopItem);
        });


    }
}
