package com.shop.service.pojo.order.orderPre;

import com.shop.service.pojo.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 获取预付订单 返回信息 */
public class OrderPreResult {
    /** 商品集合 [ 商品信息 ] */
    private List<OrderPreGoods> goods;
    /** 结算信息 */
    private Summary summary;
    /** 用户地址列表 [ 地址信息 ] */
    private List<Address> userAddresses;
}

