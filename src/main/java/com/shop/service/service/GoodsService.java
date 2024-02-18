package com.shop.service.service;

import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.category.PutCategory;
import com.shop.service.pojo.goods.AdminGoodsDetail;
import com.shop.service.pojo.goods.GoodsDetail;
import com.shop.service.pojo.goods.GoodsPublishAndEdit;

import java.util.List;

public interface GoodsService{
    List<CategoryTopItem> getCategoryTop();
    List<CategoryTopItem> getHomeCategory();

    GoodsDetail getGoodsDetailById(Integer id);

    void addCategory(String name, Integer level, String picture,Integer parentId);

    void deleteOneCategory(Integer id);

    void deleteTwoCategory(Integer id);

    void putCategory(PutCategory data);

    void publishGoods(GoodsPublishAndEdit data);

    List<AdminGoodsDetail> goodsDetail();

    GoodsPublishAndEdit getGoodsById(Integer parentId, Integer id);

    void putGoods(GoodsPublishAndEdit data);
}
