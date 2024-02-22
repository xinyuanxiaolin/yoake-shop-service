package com.shop.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*用户修改密码*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Password {
    /** 原密码*/
    private String password;
    /** 新密码*/
    private String newPassword;

}
