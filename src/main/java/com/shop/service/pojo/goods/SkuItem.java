package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** sku信息 */
public class SkuItem {
    /** id */
    private String  id;
    /** 库存 */
    private Integer inventory;
    /** 原价 */
    private Double oldPrice;
    /** sku图片 */
    private String  picture;
    /** 当前价格 */
    private Double price;
    /** sku编码 */
    private String skuCode;
    /** 规格集合[ 规格信息 ] */
    private List<SkuSpecItem> specs;
}
