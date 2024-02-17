package com.shop.service.controller;

import com.shop.service.pojo.Result;
import com.shop.service.pojo.category.AddCategory;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.category.PutCategory;
import com.shop.service.pojo.goods.GoodsDetail;
import com.shop.service.service.GoodsService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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


    /** 管理员模块*/
    //添加分类
    @PostMapping("/category")
    public Result addCategory(@RequestBody AddCategory data){
        goodsService.addCategory(data.getName(),data.getLevel(),data.getPicture(),data.getParentId());
        return Result.success();
    }

    //获得分类详情
    @GetMapping("/category")
    public Result getCategory(){
        List<CategoryTopItem> res = goodsService.getHomeCategory();
        return Result.success(res);
    }
    //删除一级分类
    @DeleteMapping("/category/one/{id}")
    public Result deleteOneCategory(@PathVariable Integer id){
        goodsService.deleteOneCategory(id);
        return Result.success();
    }
    //删除二级分类
    @DeleteMapping("/category/two/{id}")
    public Result deleteTwoCategory(@PathVariable Integer id){
        goodsService.deleteTwoCategory(id);
        return Result.success();
    }

    //修改分类名称
    @PutMapping("/category")
    public Result putCategory(@RequestBody PutCategory data){
        goodsService.putCategory(data);
        return Result.success();

    }

}
