package com.shop.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.pojo.order.OrderProducts;
import com.shop.service.pojo.order.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PayMapper extends BaseMapper<Orders> {

}
