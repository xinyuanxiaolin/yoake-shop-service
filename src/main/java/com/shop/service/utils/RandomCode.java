package com.shop.service.utils;

import org.springframework.stereotype.Component;

@Component
public class RandomCode {
    //生成6位随机数
    public String genCode(){
        int code = (int)((Math.random()*9+1)*100000);
        return code+"";
    }

    //测试
//    public static void main (String[] args){
//        int v =(int)((Math.random()*9+1)*100000);
//        System.out.println(v);
//    }
}
