package com.shop.service.pojo.order.orderPre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 结算信息 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    /** 商品总价 */
    private Double totalPrice;
    /** 邮费 */
    private Integer postFee;
    /** 应付金额 */
    private Double totalPayPrice;
}
