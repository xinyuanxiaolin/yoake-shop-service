package com.shop.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    /** 轮播图id */
    private Integer id;
    /** 轮播图商品跳转地址 */
    private String hrefUrl;
    /** 图片地址 */
    private String imgUrl;
    /** 类型 1为主页轮播图 2为分类页轮播图 */
    private String type;


}
