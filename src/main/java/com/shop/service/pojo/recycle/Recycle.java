package com.shop.service.pojo.recycle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//二手手机回收实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recycle {
    /**
     * 二手手机回收id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /** 二手回收商品名*/
    private String goodsName;
    /** 商品图片url*/
    private String goodsPicture;
    /**
     * 机身成色，0 全新或者仅拆封，未激活，1 机身外观完美，无使用痕迹，2 外观有轻微使用痕迹，3 外观有明显划痕/磕碰/掉漆，4 外观有变形/碎裂/缺失/刻字，5 机身弯曲或断裂
     */
    private Integer type;
    /**
     * 用户估价
     */
    private Double evaluate;
    /**
     * 回收状况 0代表待处理 1代表待客户确认 2代表回收成功 3 代表已取消
     */
    private Integer state;
    /** 创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 联系人*/
    private String receiverContact;
    /** 联系电话*/
    private String receiverMobile;
    /** 联系地址*/
    private String receiverAddress;



}
