package com.shop.service.pojo.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategory {
    //上传的分类名字
    private String name;
    //上传的是几级分类
    private Integer level;
    //上传分类的图片
    private String  picture;
    //如果是二级列表则会传父类id
    private Integer parentId;
}
