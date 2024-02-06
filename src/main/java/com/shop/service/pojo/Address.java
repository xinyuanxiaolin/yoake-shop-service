package com.shop.service.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//收货地址实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    /** 收货地址 id */
    private Integer id;
    /** 省市区 */
    private String  fullLocation;
    /** 用户id */
    private Integer userId;
    /**收货人姓名 */
    private String  receiver;
    /**联系方式 */
    private String  contact;
    /**所在省份编码 */
    private String  provinceCode;
    /**市对应编码 */
    private String cityCode;
    /**区/县对应编码 */
    private String  countyCode;
    /** 收货人详细地址*/
    private String address;
    /**是否设置为默认地址（数值类型） 1是 0否 */
    @TableField("is_default")
    private Integer isDefault;
}
