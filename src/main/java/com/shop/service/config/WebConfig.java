package com.shop.service.config;

import com.shop.service.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration //配置类  这边是用于要实现哪些拦截器
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //开放的接口
        List<String> openApi =new ArrayList<>();
        openApi.add("/login/**");
        openApi.add("/home/goods/guessLike");
        openApi.add("/home/banner");
        openApi.add("/home/category/mutli");
        openApi.add("/category/top");
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**")  //两个星号记得
                .excludePathPatterns(openApi);

    }
}
