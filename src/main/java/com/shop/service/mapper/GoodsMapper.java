package com.shop.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.goods.*;
import com.shop.service.pojo.order.orderPre.OrderPreGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface GoodsMapper extends BaseMapper<CategoryTopItem> {


    @Select("select * from category where id = #{id}")
    GoodsDetail getInfo(Integer id);

    @Select("select * from goods_properties where goods_id=#{id}")
    List<DetailsPropertyItem> getPropertiesById(Integer id);

    @Select("select picture from goods_pictures where goods_id =#{id} and type = 2")
    List<String> getDetailsPictures(Integer id);
    @Select("select picture from goods_pictures where goods_id =#{id} and type = 1")
    List<String> getMainPicturesById(Integer id);


    List<OrderPreGoods> getOrderPreGoods( @Param("ids") List<Integer> ids);
}
