package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shop.service.pojo.Result;
import com.shop.service.pojo.User;
import com.shop.service.service.UserService;
import com.shop.service.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class UserController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;

    /*获取个人信息*/
    @GetMapping("/profile")
    public Result getProfileById(){
        //获取请求头token,解析用户id发送获取信息请求
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.ParseJWT(jwt);
        Integer userId = (Integer) claims.get("id");
        User user = userService.getById(userId);
        return Result.success(user);


    }

    /*修改个人信息*/
    @PutMapping("/profile")
    public Result putProfileById(@RequestBody User user){
        //获取请求头token,解析用户id发送获取信息请求
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.ParseJWT(jwt);
        Integer userId = (Integer) claims.get("id");
        user.setId(Long.valueOf(userId));
        userService.updateById(user);
        return Result.success();
    }
}
