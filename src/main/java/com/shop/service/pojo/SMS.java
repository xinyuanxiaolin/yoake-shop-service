package com.shop.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMS {
    /** 手机号*/
    private String phoneNum;
    /** 短信验证码*/
    private String sms;

}
