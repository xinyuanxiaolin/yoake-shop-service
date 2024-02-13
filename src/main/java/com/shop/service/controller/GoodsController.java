package com.shop.service.controller;

import com.shop.service.pojo.Result;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.goods.GoodsDetail;
import com.shop.service.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//商品和分类相关接口
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //首页-前台分类
    @GetMapping("/home/category/mutli")
    public Result getHomeCategory(){
        List<CategoryTopItem> categoryTopItemList = goodsService.getCategoryTop();
        return Result.success(categoryTopItemList);
    }

    //分类-分类列表
    @GetMapping("/category/top")
    public  Result getCategoryList(){
        //多级分类返回
        List<CategoryTopItem> categoryTopItem = goodsService.getHomeCategory();
        return  Result.success(categoryTopItem);
    }

    //商品详情
    @GetMapping("/goods")
    public Result getGoodsById(@RequestParam Integer id){
        GoodsDetail goodsDetail = goodsService.getGoodsDetailById(id);
        return Result.success(goodsDetail);
    }

}
