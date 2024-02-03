package com.shop.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户登录账号类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String account;
    private String password;
}
