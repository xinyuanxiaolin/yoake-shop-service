package com.shop.service.pojo;

import com.shop.service.pojo.order.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserList{
    /** 总记录数 */
    private Long total;
    /** 当前页码 */
    private Long pageNum;
    /** 总页数 */
    private Long pages;
    /** 页尺寸 */
    private Long pageSize;
    /**用户列表*/
    private List<User> list;
}
