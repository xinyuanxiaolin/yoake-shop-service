package com.shop.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.mapper.GoodsMapper;
import com.shop.service.pojo.category.CategoryChildItem;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.goods.*;
import com.shop.service.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService  {

    @Autowired
    private GoodsMapper goodsMapper;

    //实现多级列表封装函数
    private List<CategoryTopItem> getParent_id(List<CategoryTopItem> selectList ,Integer parent_id) {
        List<CategoryTopItem> collect =  selectList.stream().filter(item->{
            return item.getParentId() == parent_id;
        }).collect(Collectors.toList());
        return  collect;
    }

    //获取一级列表
    @Override
    public List<CategoryTopItem> getCategoryTop(){
        return goodsMapper.selectList(new QueryWrapper<CategoryTopItem>().eq("parent_id",0));
    }

    //获取多级列表
    @Override
    public List<CategoryTopItem> getHomeCategory() {
        //性能优化:将多次数据库查询改为一次查询所有
        List<CategoryTopItem> categoryAllList = goodsMapper.selectList(null);

        //1.查出所有1级分类
        List<CategoryTopItem> categoryTopItemList = getParent_id(categoryAllList,0);
        //2.封装数据
        List<CategoryTopItem> parent_data =categoryTopItemList.stream().map(v->{
            //1.通过每一个一级分类,查到这个一级分类的二级分类

            List<CategoryTopItem> categoryEntities = getParent_id(categoryAllList,v.getId());
            //2.判空,拿到数据后要封装成二级分类的格式
            List<CategoryChildItem> categoryChildItems =null;
            if(categoryEntities != null){
                categoryChildItems = categoryEntities.stream().map(L2->{
                    CategoryChildItem categoryChildItem = new CategoryChildItem(L2.getId(),v.getId(),L2.getName(),L2.getPicture(),null);
                    //3.找到当前二级分类下的所有商品,也就是三级分类封装
                    List<CategoryTopItem> categoryGoodsItems = getParent_id(categoryAllList, L2.getId());
                    if(categoryGoodsItems !=null){
                        //封装成三级列表也就是商品格式列表
                        List<GoodsItem> goodsItemList =   categoryGoodsItems.stream().map(goods->{
                            GoodsItem goodsItem = new GoodsItem(goods.getId(),L2.getId(),goods.getDesc(),goods.getDiscount(),goods.getName(), goods.getOrderNum(), goods.getPicture(),goods.getPrice());
                            return  goodsItem;
                        }).collect(Collectors.toList());
                        categoryChildItem.setGoods(goodsItemList);
                    }
                    return  categoryChildItem;
                }).collect(Collectors.toList());
            }
            v.setChildren(categoryChildItems);
            return v;
        }).collect(Collectors.toList());;


        return parent_data;
    }

    //获取单个商品详情数据
    @Override
    public GoodsDetail getGoodsDetailById(Integer id) {
        //首先根据商品id获取下商品基本数据
        GoodsDetail goodsDetail = goodsMapper.getInfo(id);
        //接下来获取details,mainPictures,similarProducts详细数据
        //封装details
        Details details = new Details();
        details.setProperties(goodsMapper.getPropertiesById(id));
        details.setPictures(goodsMapper.getDetailsPictures(id));
        //封装mainPictures
        goodsDetail.setMainPictures(goodsMapper.getMainPicturesById(id));
        //接下来给goodsDetail剩下封装好的每个属性赋值
        goodsDetail.setDetails(details);


        log.info("商品值:{}",goodsDetail);
        return goodsDetail;
    }


}
