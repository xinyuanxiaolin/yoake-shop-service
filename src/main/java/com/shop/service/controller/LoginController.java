package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.pojo.Result;
import com.shop.service.pojo.Admin;
import com.shop.service.pojo.User;
import com.shop.service.service.UserService;
import com.shop.service.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    //    后台管理登录
    @PostMapping("/bms")
    public Result login(@RequestBody Admin admin){
        log.info("员工登录:{},",admin);
        if(admin.getAccount().equals("admin")&&admin.getPassword().equals("123456")){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id","admin");
            String jwt = JwtUtils.GetJWT(claims);
            return Result.success(jwt);
        }
        return  Result.error("用户名或者密码错误!");

    }

    //H5端和APP端用户账号密码登录
    @PostMapping("/other")
    public Result loginByOther(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount())
                        .eq("password",user.getPassword());

        Result jwt = getResult(queryWrapper);
        if (jwt != null) return jwt;
        return Result.error("用户名或密码错误");
    }
    //小程序端模拟微信登录
    @PostMapping("/wxMin/simple")
    public Result loginBywxMin(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount());
        Result jwt = getResult(queryWrapper);
        if (jwt != null) return jwt;
        //手机号不存在,注册手机号
        user.setCreateTime(LocalDateTime.now());
        if(userService.save(user)){
            queryWrapper.eq("account",user.getAccount());
            Result jwt1 = getResult(queryWrapper);
            if (jwt1 != null) return jwt1;
        }
        return Result.error("出错了,请联系管理员");


    }

    private Result getResult(QueryWrapper<User> queryWrapper) {
        User e = userService.getOne(queryWrapper);
        if(e!=null){
            //            生成令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("account",e.getAccount());
            String jwt = JwtUtils.GetJWT(claims);
            return Result.success(jwt);
        }
        return null;
    }

}
