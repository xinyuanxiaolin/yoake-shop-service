package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 属性信息 */
public class DetailsPropertyItem {
    /** 属性名称 */
    private String name;
    /** 属性值 */
    private String value;

}
