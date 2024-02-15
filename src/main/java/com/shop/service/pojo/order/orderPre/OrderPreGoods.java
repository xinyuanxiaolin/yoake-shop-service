package com.shop.service.pojo.order.orderPre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 商品信息 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPreGoods {
    /** 商品id */
    private String id;
    /** 数量 */
    private Integer count;
    /** 商品名称 */
    private String name;
    /** 实付单价 */
    private Double nowPrice;
    /** 图片 */
    private String picture;
    /** 原单价 */
    private Double price;
}
