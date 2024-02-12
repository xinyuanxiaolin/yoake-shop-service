package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shop.service.common.JwtToken;
import com.shop.service.pojo.Address;
import com.shop.service.pojo.Result;
import com.shop.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/member/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private JwtToken jwtToken;
    //获取所有收货地址通过用户id
    @GetMapping
    public Result getAddressById(){

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",jwtToken.getUserIdByToken());
        List<Address> res = addressService.list(queryWrapper);
        return Result.success(res);

    }
    //添加我的收货地址通过用户id
    @PostMapping
    public Result addAddressByUserId(@RequestBody Address address){
        address.setUserId(jwtToken.getUserIdByToken());
        //此处做个判断,默认收货地址只能有一个
        if(address.getIsDefault() ==1){
            //其他全部置0
            UpdateWrapper<Address> updateWrapper =new UpdateWrapper<>();
            updateWrapper.set("is_default",0);
            addressService.update(updateWrapper);
        }
        addressService.save(address);
        return Result.success();
    }
    //获取收货地址详情通过地址id
    @GetMapping("/{id}")
    public Result getAddressDetail(@PathVariable Integer id ){
        Address res = addressService.getById(id);
        return  Result.success(res);
    }

    //更新收货地址通过地址id
    @PutMapping("/{id}")
    public Result putAddress(@PathVariable Integer id,@RequestBody Address address){
        UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        addressService.update(address,updateWrapper);
        return  Result.success();
    }

    //删除收货地址
    @DeleteMapping("/{id}")
    public Result deleteAddressById(@PathVariable Integer id){
        addressService.removeById(id);
        return Result.success();
    }




}
