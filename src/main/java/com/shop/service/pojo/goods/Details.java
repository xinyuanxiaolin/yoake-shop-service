package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 商品详情: 包含详情属性 + 详情图片 */
public class Details {
    /** 商品属性集合[ 属性信息 ] */
    private List<DetailsPropertyItem> properties;
    /** 商品详情图片集合[ 图片链接 ] */
    private List<String> pictures;
}
