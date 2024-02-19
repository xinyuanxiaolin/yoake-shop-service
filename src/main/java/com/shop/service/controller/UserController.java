package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shop.service.common.JwtToken;
import com.shop.service.pojo.Result;
import com.shop.service.pojo.User;
import com.shop.service.service.UserService;
import com.shop.service.utils.AliOSSUtils;
import com.shop.service.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class UserController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private JwtToken jwtToken;

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

    /*头像上传*/
    @PostMapping("/profile/avatar")
    public Result postAvatar(MultipartFile file) throws IOException {
        String avatar=  aliOSSUtils.upload(file);
        //保存url到user的头像那边
        UpdateWrapper<User> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",jwtToken.getUserIdByToken()).set("avatar",avatar);
        userService.update(updateWrapper);
        Map<String, String> data = new HashMap<>();
        data.put("avatar", avatar);
        return Result.success(data);

    }
}
