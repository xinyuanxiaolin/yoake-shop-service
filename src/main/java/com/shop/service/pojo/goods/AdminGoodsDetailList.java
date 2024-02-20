package com.shop.service.pojo.goods;

import com.shop.service.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminGoodsDetailList {
    /** 总记录数 */
    private Long total;
    /** 当前页码 */
    private Integer pageNum;
    /** 总页数 */
    private Integer pages;
    /** 页尺寸 */
    private Integer pageSize;
    /**商品列表*/
    private List<AdminGoodsDetail> list;


}
