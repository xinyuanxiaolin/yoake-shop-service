package com.shop.service.pojo.category;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class GoodsItem {
    /** 商品id */
    private  Integer id;
    /** 父节点id */
    private Integer parentId;
    /** 商品描述 */
    @TableField("`desc`")  //使用模版字符串套住
    private  String desc;
    /** 商品折扣 */
    private Integer discount;
    /** 商品名称 */
    private String name;
    /** 商品已下单数量 */
    private Integer orderNum;
    /** 商品图片 */
    private String picture;
    /** 商品价格 */
    private Integer price;

}
