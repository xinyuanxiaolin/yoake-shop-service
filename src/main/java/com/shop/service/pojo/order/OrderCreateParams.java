package com.shop.service.pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 提交订单 请求参数 */
public class OrderCreateParams {
    /** 所选地址Id */
    private String addressId;
    /** 订单备注 */
    private String buyerMessage;
    /** 商品集合[ 商品信息 ] */
    private List<GoodsList> goods;
    /** 提交订单方式,1为直接购买,2为购物车购买*/
    private Integer payType;
    /** 总价*/
    private Double totalPayPrice;

}
