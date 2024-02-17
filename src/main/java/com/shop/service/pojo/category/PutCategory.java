package com.shop.service.pojo.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutCategory extends AddCategory{
    //分类id
    private Integer id;
}
