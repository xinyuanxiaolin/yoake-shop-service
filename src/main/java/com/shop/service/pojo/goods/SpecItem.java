package com.shop.service.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/** 可选规格信息 */
public class SpecItem {
    /** 规格id */
    private String id;
    /** 规格名称 */
    private String  name;
    /** 可选值集合[ 可选值信息 ] */
    private List<SpecValueItem> values;
}
