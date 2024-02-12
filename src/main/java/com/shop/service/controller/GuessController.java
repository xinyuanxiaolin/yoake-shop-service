package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.pojo.Guess;
import com.shop.service.pojo.Result;
import com.shop.service.pojo.goods.GoodsItem;
import com.shop.service.service.GuessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class GuessController {

    @Autowired
    private GuessService guessService;

    //猜你喜欢数据分页查询
    @GetMapping("/home/goods/guessLike")
    public Result getGuessLike(@RequestParam(defaultValue ="1") Long page, @RequestParam(defaultValue = "10") Long pageSize){
        //分页查询数据封装
        IPage<GoodsItem> pagearea =new Page<>(page,pageSize);
        //随机返回数据且为三级列表的才是商品
        QueryWrapper<GoodsItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("list_level",3);
        IPage<GoodsItem> goodsList =guessService.page(pagearea,queryWrapper);

        Guess res =new Guess();
        //将数据库获取的数据封装为猜你喜欢数据格式返回给前端
        res.setCounts(goodsList.getTotal());
        res.setPages(goodsList.getPages());
        res.setPage(goodsList.getCurrent());
        res.setPageSize(goodsList.getSize());
        res.setItems(goodsList.getRecords());

        log.info("值:{}",res);
        return  Result.success(res);
    }
}
