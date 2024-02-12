package com.shop.service.pojo;

import com.shop.service.pojo.goods.GoodsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//猜你喜欢实体
public class Guess {
    /** 总条数 */
    private Long counts;
    /** 每页条数 */
    private Long pageSize;
    /** 当前页数 */
    private Long page;
    /** 总页数 */
    private Long pages;
    /** 列表数据 */
    private List<GoodsItem> items;
}
