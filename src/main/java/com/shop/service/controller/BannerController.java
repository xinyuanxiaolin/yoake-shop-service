package com.shop.service.controller;

import com.shop.service.pojo.Banner;
import com.shop.service.pojo.Result;
import com.shop.service.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    //获取轮播图通过类型
    @GetMapping("/banner")
    public Result getBannerByType(@RequestParam String distributionSite ){
        List<Banner> res = bannerService.getByType(distributionSite);
        return Result.success(res);
    }

    /** 管理员模块 */
    //获取所有轮播图
    @GetMapping("/banner/all")
    public Result getBanner(){
        List<Banner> res = bannerService.getBanner();
        return Result.success(res);
    }
    //编辑轮播图和新增轮播图
    @PutMapping("/banner")
    public Result putBanner(@RequestBody Banner banner){
        bannerService.putBanner(banner);
        return Result.success();
    }
    //删除轮播图
    @DeleteMapping("/banner/{ids}")
    public Result deleteBanner(@PathVariable List<Integer> ids){
        bannerService.deleteBanner(ids);
        return Result.success();
    }


}
