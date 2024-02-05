package com.shop.service.service;

import com.shop.service.pojo.category.CategoryChildItem;
import com.shop.service.pojo.category.CategoryTopItem;

import java.util.List;
import java.util.Map;

public interface GoodsService{
    List<CategoryTopItem> getCategoryTop();
    List<CategoryTopItem> getHomeCategory();
}
