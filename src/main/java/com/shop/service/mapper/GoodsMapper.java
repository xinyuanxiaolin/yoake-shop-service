package com.shop.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.pojo.category.CategoryTopItem;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GoodsMapper extends BaseMapper<CategoryTopItem> {
}
