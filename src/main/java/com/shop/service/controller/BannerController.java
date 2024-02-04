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
}
