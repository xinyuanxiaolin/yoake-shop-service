package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 商品发布和编辑商品实体类*/
public class GoodsPublishAndEdit {
    /** 商品id*/
    private Integer id;
    /** 商品主图合集*/
    private List<String> mainPictures;
    /** 商品分类,里面是从一级分类到二级分类的id*/
    private List<Integer> categoryList;
    /** 商品海报图*/
    private List<String> pictures;
    /** 商品名称*/
    private String name;
    /** 商品库存*/
    private Integer stock;
    /** 商品价格*/
    private Double price;
    /** 商品描述*/
    private String desc;
    /** 商品属性*/
    private List<DetailsPropertyItem> properties;



}
