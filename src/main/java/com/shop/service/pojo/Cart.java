package com.shop.service.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 购物车类型 */
public class Cart {
    /** 购物车ID */
    @TableId
    private String Id;
    /** 商品 ID */
    private String goodsId;
    /** 用户 ID */
    private String userId;
    /** 商品名称 */
    private String name;
    /** 图片 */
    private String picture;
    /** 数量 */
    private Integer count;
    /** 加入时价格 */
    private Double price;
    /** 当前的价格 */
    private Double nowPrice;
    /** 库存 */
    private Integer stock;
    /** 是否选中 */
    private Boolean selected;

}
