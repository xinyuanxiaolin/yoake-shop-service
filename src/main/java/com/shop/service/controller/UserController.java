package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.common.JwtToken;
import com.shop.service.pojo.Password;
import com.shop.service.pojo.Result;
import com.shop.service.pojo.User;
import com.shop.service.pojo.UserList;
import com.shop.service.service.UserService;
import com.shop.service.utils.AliOSSUtils;
import com.shop.service.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
        return Result.success(user);
    }
    /*用户修改密码*/
    @PostMapping("/profile")
    public Result postPassword(@RequestBody Password data){
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("password",data.getPassword()).eq("id",jwtToken.getUserIdByToken());

         User res = userService.getOne(queryWrapper);
        //首先先确定原密码没错
        if(res!=null){
            //不等于空再进行新密码更改
            User user =new User();
            user.setPassword(data.getNewPassword());
            user.setId((long)jwtToken.getUserIdByToken());
            userService.updateById(user);
            return Result.success("修改成功!");
        }else{
            return Result.error("原密码错误!");
        }

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

    /** 管理员模块*/
    /*获取所有用户信息*/
    @GetMapping
    public Result getUsers(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,
    String searchText){
        Page<User> page =new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if(searchText.length()!=0){
            queryWrapper.like("account",searchText).or().like("nickname",searchText)
                    .or().like("profession",searchText).or().like("full_location",searchText);
        }
        Page<User> data = userService.page(page,queryWrapper);
        UserList res = new UserList(data.getTotal(),data.getCurrent(),data.getPages(),data.getSize(),data.getRecords());
        return Result.success(res);
    }

    /*修改和新增用户数据*/
    @PutMapping
    public Result putUsers(@RequestBody User user){
        if(user.getId()!=null){
            //证明是修改用户
            userService.updateById(user);
        }else{
            //证明是新增用户
            user.setCreateTime(LocalDateTime.now());
            userService.save(user);
        }
        return Result.success();
    }

    /*删除用户*/
    @DeleteMapping("/{ids}")
    public Result deleteUsers(@PathVariable List<Integer> ids){
        userService.removeBatchByIds(ids);
        return Result.success();
    }

}
