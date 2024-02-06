package com.shop.service.common;

import com.shop.service.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
//自己封装一个获取jwt上用户id的方法
@Service
public class JwtToken {
    @Autowired
    private HttpServletRequest request;

    public Integer getUserIdByToken(){
        //获取请求头token,解析用户id发送获取信息请求
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.ParseJWT(jwt);
        return (Integer) claims.get("id");
    }
}
