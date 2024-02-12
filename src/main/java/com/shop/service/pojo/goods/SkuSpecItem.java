package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 规格信息 */
public class SkuSpecItem {
    /** 规格名称 */
    private String name;
    /** 可选值名称 */
    private String valueName;
}
