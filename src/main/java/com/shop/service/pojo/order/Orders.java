package com.shop.service.pojo.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shop.service.pojo.goods.GoodsItem;
import com.shop.service.pojo.order.orderPre.OrderPreGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 订单详情 返回信息 */
public class Orders {
    /** 订单id */
    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 订单状态 */
    private Integer orderState;
    /** 收货人*/
    private String receiverContact;
    /** 收货人电话*/
    private String receiverMobile;
    /** 收货人地址完整地址*/
    private String receiverAddress;
    /** 创建时间*/
    private LocalDateTime createTime;
    /** 运费*/
    private Integer postFee;
    /** 商品总价*/
    private Double totalMoney;
    /** 加上运费后应付金额*/
    private Double payMoney;
    /** 订单备注*/
    private String buyerMessage;
    /** 商品列表*/
    @TableField(exist = false)
    private List<GoodsItem> goods;



}
