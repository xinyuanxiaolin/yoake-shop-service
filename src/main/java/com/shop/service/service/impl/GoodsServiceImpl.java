package com.shop.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shop.service.mapper.GoodsMapper;
import com.shop.service.pojo.category.CategoryChildItem;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.category.PutCategory;
import com.shop.service.pojo.goods.*;
import com.shop.service.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
                            GoodsItem goodsItem = new GoodsItem(goods.getId(),L2.getId(),goods.getDesc(),goods.getDiscount(),goods.getName(), goods.getStock(), goods.getPicture(),goods.getPrice(),goods.getNowPrice());
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


    /** 管理员模块*/
    //添加分类
    @Override
    public void addCategory(String name, Integer level, String picture,Integer parentId) {
        CategoryTopItem categoryTopItem =new CategoryTopItem();
        if (level==1){
            //证明是一级分类,父节点设置为0
            categoryTopItem.setParentId(0);
        }else if(level ==2 && parentId !=null){
            //证明是二级列表,父节点设置为传来的一级列表id
            categoryTopItem.setParentId(parentId);

        }
        categoryTopItem.setName(name);
        categoryTopItem.setPicture(picture);
        categoryTopItem.setListLevel(level);
        goodsMapper.insert(categoryTopItem);
    }

    //删除一级分类,即对应的二级分类和商品也全部删除
    @Override
    public void deleteOneCategory(Integer id) {
        //建立一个全部删除的ids
        List<Integer> deleteIds =new ArrayList<>();
        deleteIds.add(id);
        //先获取二级分类
        QueryWrapper<CategoryTopItem> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        List<CategoryTopItem> level2 = goodsMapper.selectList(queryWrapper);
        //获取三级商品列表ids
        level2.forEach(v->{
            deleteIds.add(v.getId());
           List<Integer> ids = goodsMapper.getLevel3Ids(v.getId());
            deleteIds.addAll(ids);
        });
        //删除所有相关联的id
        goodsMapper.deleteBatchIds(deleteIds);
    }

    //删除二级分类,其下对应的三级商品也同样删除
    @Override
    public void deleteTwoCategory(Integer id) {
        //建立一个全部删除的ids
        List<Integer> deleteIds =new ArrayList<>();
        //把二级分类id添加进去
        deleteIds.add(id);
        //获取三级商品列表ids
        List<Integer> ids = goodsMapper.getLevel3Ids(id);
        deleteIds.addAll(ids);
        //删除所有相关联的id
        goodsMapper.deleteBatchIds(deleteIds);


    }

    //修改分类id
    @Override
    public void putCategory(PutCategory data) {
        UpdateWrapper<CategoryTopItem> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",data.getId()).set("name",data.getName()).set("picture",data.getPicture());
        goodsMapper.update(null,updateWrapper);
    }


}
