package com.shop.service.pojo.category;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shop.service.pojo.goods.GoodsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class CategoryChildItem {
    /** 二级分类id */
    private Integer  id;
    /** 二级分类父节点id */
    private Integer parentId;
    /** 二级分类名称 */
    private String name;
    /** 二级分类图片 */
    private String picture;
    /** 商品集合[ 商品项 ] */
    private List<GoodsItem> goods;
}
