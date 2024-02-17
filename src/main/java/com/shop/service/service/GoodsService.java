package com.shop.service.service;

import com.shop.service.pojo.Guess;
import com.shop.service.pojo.category.CategoryChildItem;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.category.PutCategory;
import com.shop.service.pojo.goods.GoodsDetail;

import java.util.List;
import java.util.Map;

public interface GoodsService{
    List<CategoryTopItem> getCategoryTop();
    List<CategoryTopItem> getHomeCategory();

    GoodsDetail getGoodsDetailById(Integer id);

    void addCategory(String name, Integer level, String picture,Integer parentId);

    void deleteOneCategory(Integer id);

    void deleteTwoCategory(Integer id);

    void putCategory(PutCategory data);
}
