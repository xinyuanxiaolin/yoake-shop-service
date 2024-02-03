package com.shop.service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "yoakelin";
    private static Long expire = 4320000L;

    /*
     * JWT令牌生成
     * */
    public static String GetJWT(Map<String,Object> claims){

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256,signKey) //设置签名算法
                .addClaims(claims) //设置自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+expire)) //设置有效期为一个小时
                .compact();
       return jwt;

    }

    /*
     * JWT令牌
     * */
    public static Claims ParseJWT(String JWT){
        Claims claims = Jwts.parser().setSigningKey(signKey).parseClaimsJws(JWT).getBody();
        return claims;

    }
}
