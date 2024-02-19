package com.shop.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//登录回显
public class LoginResult {
    /** 用户id*/
    private Long id;
    /** 用户头像地址*/
    private String avatar;
    /** 用户账号也是手机号*/
    private String  account;
    /** 用户昵称*/
    private String nickname;
    /** token*/
    private String token;


}
