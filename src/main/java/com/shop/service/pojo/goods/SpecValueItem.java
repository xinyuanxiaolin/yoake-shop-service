package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecValueItem {
    /** 可选值备注 */
    private String  desc;
    /** 可选值名称 */
    private String name;
    /** 可选值图片链接 */
    private String picture;
}
