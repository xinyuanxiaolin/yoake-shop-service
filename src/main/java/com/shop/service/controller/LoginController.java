package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.pojo.LoginResult;
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
        User e = userService.getOne(queryWrapper);
        if(e!=null){
            String  jwt = getToken(e.getId(), e.getAccount());
            return Result.success(new LoginResult(e.getId(),e.getAvatar(),e.getAccount(),e.getNickname(),jwt));
        }
        return Result.error("用户名或密码错误");

    }
    //小程序端模拟微信登录
    @PostMapping("/wxMin/simple")
    public Result loginBywxMin(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount());
        User e = userService.getOne(queryWrapper);
        if(e!=null){
            String  jwt = getToken(e.getId(),e.getAccount());
            return Result.success(new LoginResult(e.getId(),e.getAvatar(),e.getAccount(),e.getNickname(),jwt));
        }
        //手机号不存在,注册手机号
        user.setCreateTime(LocalDateTime.now());
        if(userService.save(user)){
            String  jwt1 = getToken(user.getId(),user.getAccount());
            return Result.success(new LoginResult(user.getId(),user.getAvatar(),user.getAccount(),user.getNickname(),jwt1));
        }
        return Result.error("出错了,请联系管理员");


    }

    private String getToken(Long id, String account) {

            //            生成令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",id);
            claims.put("account",account);
            String jwt = JwtUtils.GetJWT(claims);
            return jwt;
    }

}
