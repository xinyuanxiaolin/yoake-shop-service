package com.shop.service.pojo.order.orderList;

import com.shop.service.pojo.order.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** 订单列表 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResult {
    /** 总记录数 */
    private Long counts;
    /** 数据集合    [ 订单信息 ] */
    private List<Orders> items;
    /** 当前页码 */
    private Long page;
    /** 总页数 */
    private Long pages;
    /** 页尺寸 */
    private Long pageSize;
}
