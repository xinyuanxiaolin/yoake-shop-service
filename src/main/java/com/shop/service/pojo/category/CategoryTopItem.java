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
    private  String desc;
    /** 商品已下单数量 */
    private Integer orderNum;
    /** 商品价格 */
    private Integer price;
    /** 商品折扣 */
    private Integer discount;
    /** 一级分类图片集[ 一级分类图片项 ] */
    @TableField(exist = false)
    private List<String> imageBanners;
    /** 二级分类集合[ 二级分类项 ] */
    @TableField(exist = false)
    private  List<CategoryChildItem> children;

}

