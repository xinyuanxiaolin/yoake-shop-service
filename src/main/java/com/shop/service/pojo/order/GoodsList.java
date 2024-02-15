package com.shop.service.pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsList{
    /** 数量 */
    private Integer count;
    /** 商品id */
    private Integer goodsId;
}