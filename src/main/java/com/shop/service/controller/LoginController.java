package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.pojo.*;
import com.shop.service.service.UserService;
import com.shop.service.utils.AliSendSMS;
import com.shop.service.utils.JwtUtils;
import com.shop.service.utils.RandomCode;
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
    @Autowired
    private AliSendSMS aliSendSMS;
    @Autowired
    private RandomCode randomCode;

    //注：由于未使用redis，短信验证码功能只能写在变量中，这样就导致只能一个用户登录使用，多用户会出问题
    private String userSMS;

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
    //发送短信验证码
    @PostMapping("/sms")
    public Result sendSMS(@RequestBody SMS sms) throws Exception {
        sms.setSms(randomCode.genCode());
        userSMS = sms.getSms();
        aliSendSMS.send(sms);
        return Result.success();
    }
    //短信验证码验证登录
    @PostMapping("/verifyCode")
    public Result verifyCode(@RequestBody SMS data){
        //先验证短信验证码有没有问题
        if(data.getSms().equals(userSMS)){
            //接下来验证有没有注册
            //先验证下手机号存不存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account",data.getPhoneNum());
            User e = userService.getOne(queryWrapper);
            if(e!=null){
                String  jwt = getToken(e.getId(),e.getAccount());
                return Result.success(new LoginResult(e.getId(),e.getAvatar(),e.getAccount(),e.getNickname(),jwt));
            }
            //手机号不存在,注册手机号
            User user =new User();
            user.setAccount(data.getPhoneNum());
            user.setCreateTime(LocalDateTime.now());
            if(userService.save(user)){
                String  jwt1 = getToken(user.getId(),user.getAccount());
                return Result.success(new LoginResult(user.getId(),user.getAvatar(),user.getAccount(),user.getNickname(),jwt1));
            }
        }
        return Result.error("短信验证码错误");
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
