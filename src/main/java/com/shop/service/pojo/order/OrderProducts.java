package com.shop.service.pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 订单-商品关联类*/
public class OrderProducts {
    /** 关联表id*/
    private Integer id;
    /** 订单id*/
    private Integer orderId;
    /** 商品id*/
    private Integer goodsId;
    /** 购买数量*/
    private Integer quantity;
}
