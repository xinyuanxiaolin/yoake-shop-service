package com.shop.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.pojo.order.OrderProducts;
import com.shop.service.pojo.order.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

    @Insert("insert into order_products(order_id,goods_id,quantity) values (#{orderId},#{goodsId},#{count})")
    void addOrderProducts(@Param("count") Integer count,@Param("goodsId") Integer goodsId,@Param("orderId") Integer orderId);

    @Select("select * from order_products where order_id = #{id}")
    List<OrderProducts> getOrderProducts(Integer id);
}
