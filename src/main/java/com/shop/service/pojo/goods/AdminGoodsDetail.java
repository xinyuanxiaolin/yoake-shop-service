package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 获取商品后台的数据详情*/
public class AdminGoodsDetail {
    /** 商品id*/
    private Integer id;
    /** 二级分类id*/
    private Integer parentId;
    /** 图片*/
    private String picture;
    /** 商品名字*/
    private String name;
    /** 商品分类*/
    private String category;
    /** 现价*/
    private Double nowPrice;
    /** 原价*/
    private Double price;
    /** 库存*/
    private Integer stock;
    /** desc描述*/
    private String desc;

}
