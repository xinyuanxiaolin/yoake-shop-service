package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品详情的实体类封装
public class GoodsDetail {
    /** id */
    private String id;
    /** 商品名称 */
    private String name;
    /** 商品描述 */
    private String desc;
    /** 当前价格 */
    private Double price;
    /** 原价 */
    private Double oldPrice;
    /** 商品详情: 包含详情属性 + 详情图片 */
    private Details details;
    /** 主图图片集合[ 主图图片链接 ] */
    private List<String> mainPictures;
    /** 同类商品[ 商品信息 ] */
    private List<GoodsItem> similarProducts;
    /** sku集合[ sku信息 ] */
    private List<SkuItem> skus;
    /** 可选规格集合备注[ 可选规格信息 ] */
    private List<SpecItem> specs;
}
