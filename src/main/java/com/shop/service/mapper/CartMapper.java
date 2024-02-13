package com.shop.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.pojo.Cart;
import com.shop.service.pojo.goods.GoodsItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select * from category where id = #{goodsId}")
    GoodsItem getGoodsInfo(String goodsId);

    @Select("select name from category where id =#{goodsId}")
    String getGoodsName(Integer goodsId);


}
