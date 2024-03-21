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
            return item.getParentId() == parent_id && item.getRemoved() == 0;
        }).collect(Collectors.toList());
        return  collect;
    }

    //获取一级列表
    @Override
    public List<CategoryTopItem> getCategoryTop(){
        return goodsMapper.selectList(new QueryWrapper<CategoryTopItem>().eq("parent_id",0).last("LIMIT 10"));
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
                            GoodsItem goodsItem = new GoodsItem(goods.getId(),L2.getId(),goods.getDesc(),goods.getDiscount()
                                    ,goods.getName(), goods.getStock(), goods.getPicture(),goods.getPrice(),goods.getNowPrice());
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
    /** 分类模块*/

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


    /** 商品管理模块*/

    //发布商品
    @Override
    public void publishGoods(GoodsPublishAndEdit data) {
        //首先吧基本商品信息存到分类商品表中
        CategoryTopItem goods = new CategoryTopItem(null, data.getName(), data.getCategoryList().get(1),data.getMainPictures().get(0),
                data.getDesc(), data.getStock(), data.getPrice(), data.getPrice(), null,3,null,null,0);
        goodsMapper.insert(goods);
        //然后把商品主图列表和海报图列表存到对应的goods_pictures表中
        goodsMapper.putPictures(goods.getId(),data.getMainPictures(),1);
        //商品海报可为空
        if(data.getPictures().size()!=0){
            goodsMapper.putPictures(goods.getId(),data.getPictures(),2);
        }
        //然后商品的属性描述放到goods_properties表中
        goodsMapper.putProperties(goods.getId(),data.getProperties());
    }

    //获取所有商品详情
    @Override
    public AdminGoodsDetailList goodsDetail(Integer pageNum,Integer pageSize,String searchText) {
        //拿到三级列表全部数据
        List<AdminGoodsDetail> data = goodsMapper.getLevel3Detail(pageNum-1,pageSize,searchText);
        Long total = goodsMapper.getLevel3DetailTotal(searchText);
        data.forEach(v->{
            //分类获取他的二级分类名字
            v.setCategory(goodsMapper.selectById(v.getParentId()).getName());
        });

        return new AdminGoodsDetailList(total,pageNum,(int)Math.ceil((double)total / pageSize),pageSize,data);
    }

    //通过id获取基本商品信息
    @Override
    public GoodsPublishAndEdit getGoodsById(Integer parentId, Integer id) {
        //获取基本三级商品信息
        CategoryTopItem categoryTopItem = goodsMapper.selectById(id);
        GoodsPublishAndEdit data = new GoodsPublishAndEdit(id,null,null,null, categoryTopItem.getName(),
                categoryTopItem.getStock(), categoryTopItem.getPrice(), categoryTopItem.getDesc(), null);
        //先去找二级分类的父节点id
        //获取二级分类,然后提取出一级分类的id
        CategoryTopItem two = goodsMapper.selectById(parentId);
        //放入分类
        List<Integer> list =new ArrayList<>();
        list.add(two.getParentId());
        list.add(parentId);
        data.setCategoryList(list);
        //找主图和海报图集通过商品id
        data.setMainPictures(goodsMapper.getMainPicturesById(id));
        data.setPictures(goodsMapper.getDetailsPictures(id));
        //找商品对应的属性
        data.setProperties(goodsMapper.getPropertiesById(id));

        return data;
    }

    //修改商品
    @Override
    public void putGoods(GoodsPublishAndEdit data) {
        //先通过id修改基本的
        UpdateWrapper<CategoryTopItem> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",data.getId()).set("name",data.getName()).set("parent_id",data.getCategoryList().get(1))
                        .set("picture",data.getMainPictures().get(0)).set("`desc`",data.getDesc()).set("price",data.getPrice())
                        .set("now_price",data.getPrice()).set("stock",data.getStock());

        goodsMapper.update(null,updateWrapper);
        //接着修改主图合集和海报合集,通过删除原来的,增加新的来达成
        goodsMapper.deletePictures(data.getId());
        goodsMapper.putPictures(data.getId(), data.getMainPictures(),1);
//        海报图不是必填
        if(data.getPictures().size()!=0){
            goodsMapper.putPictures(data.getId(), data.getPictures(),2);
        }
        //修改属性,通过删除原来的,增加新的来达成
        goodsMapper.deleteProperties(data.getId());
        goodsMapper.putProperties(data.getId(), data.getProperties());

    }

    //删除单个商品或者批量删除商品
    @Override
    public void deleteGoodsByIds(List<Integer> ids) {
        goodsMapper.deleteBatchIds(ids);

    }

    //用户的模糊搜索商品
    @Override
    public AdminGoodsDetailList goodsDetailByUser(Integer pageNum, Integer pageSize, String searchText) {
        //拿到三级列表全部数据
        List<AdminGoodsDetail> data = goodsMapper.getLevel3DetailByUser(pageNum-1,pageSize,searchText);
        Long total = goodsMapper.getLevel3DetailTotalByUser(searchText);
        return new AdminGoodsDetailList(total,pageNum,(int)Math.ceil((double)total / pageSize),pageSize,data);
    }

    //下架和上架商品
    @Override
    public void putRemoveGood(Integer id,Integer state) {
        CategoryTopItem categoryTopItem = new CategoryTopItem();
        categoryTopItem.setId(id);
        categoryTopItem.setRemoved(state);
        goodsMapper.updateById(categoryTopItem);
    }


}
