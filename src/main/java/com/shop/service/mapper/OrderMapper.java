package com.shop.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.pojo.order.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

    @Insert("insert into order_products(order_id,goods_id,quantity) values (#{orderId},#{goodsId},#{count})")
    void addOrderProducts(@Param("count") Integer count,@Param("goodsId") Integer goodsId,@Param("orderId") Integer orderId);
}
