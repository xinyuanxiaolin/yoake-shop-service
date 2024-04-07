package com.shop.service.pojo.recycle;

import com.shop.service.pojo.User;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.order.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用于返回数据的实体类
public class RecycleResult {
    /** 二手回收列表*/
    private List<Recycle> list;
    /** 当前页码 */
    private Long page;
    /** 总页数 */
    private Long pages;
    /** 页尺寸 */
    private Long pageSize;
    /** 总记录数 */
    private Long counts;


}
