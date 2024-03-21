package com.shop.service.pojo.category;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//一级分类实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class CategoryTopItem {
    /** 一级分类id */
    @TableId
    private Integer  id;
    /** 一级分类名称 */
    private String name;
    /** 父节点id */
    private Integer parentId;
    /** 一级分类图片 */
    private String picture;
    /** 商品描述 */
    @TableField("`desc`")  //使用模版字符串套住
    private String desc;
    /** 商品库存 */
    private Integer stock;
    /** 商品原价格 */
    private Double price;
    /** 商品现价格 */
    private Double nowPrice;
    /** 商品折扣 */
    private Integer discount;
    /** 列表等级*/
    private Integer listLevel;
    /** 一级分类图片集[ 一级分类图片项 ] */
    @TableField(exist = false)
    private List<String> imageBanners;
    /** 二级分类集合[ 二级分类项 ] */
    @TableField(exist = false)
    private  List<CategoryChildItem> children;
    /** 是否为禁用或者是商品下架了*/
    private Integer removed;

}

