package com.shop.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.common.JwtToken;
import com.shop.service.mapper.GoodsMapper;
import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.recycle.Recycle;
import com.shop.service.pojo.Result;
import com.shop.service.pojo.recycle.RecycleResult;
import com.shop.service.service.impl.RecycleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/recycle")
//二手回收相关
public class RecycleController {

    @Autowired
    private RecycleServiceImpl recycleService;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private JwtToken jwtToken;
    //用户提交估价接口
    @PostMapping
    public Result postEvaluate(@RequestBody Recycle data){
        //新增二手手机回收订单转为待处理（1）
        data.setState(1);
        data.setCreateTime(LocalDateTime.now());
        //根据商品id直接查询商品把商品名和商品图片地址放进订单中（实际使用不可取,这里是为了节省查询效率,方便直接使用分页查询）
        CategoryTopItem goodData =  goodsMapper.selectById(data.getGoodsId());
        data.setGoodsName(goodData.getName());
        data.setGoodsPicture(goodData.getPicture());
        data.setUserId(jwtToken.getUserIdByToken());
        recycleService.save(data);
        return Result.success();
    }
    //用户查看二手回收订单详情
    @GetMapping("/user")
    public Result getRecycleByUser(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam Integer state){
        Page<Recycle> pageRecycle =new Page<>(page,pageSize);
        QueryWrapper<Recycle> queryWrapper =new QueryWrapper<>();
        //0为搜索全部二手回收情况
        if(state==0){
            queryWrapper.eq("user_id",jwtToken.getUserIdByToken());
        }else{
            queryWrapper.eq("state",state).eq("user_id",jwtToken.getUserIdByToken());
        }
        // 添加排序条件，按照订单创建时间倒序排序
        queryWrapper.orderByDesc("create_time");
        Page<Recycle> recycleList =  recycleService.page(pageRecycle,queryWrapper);
        RecycleResult data = new RecycleResult(recycleList.getRecords(),recycleList.getCurrent(),recycleList.getPages(),
                recycleList.getSize(),recycleList.getTotal());
        return Result.success(data);
    }
    //用户取消二手回收订单
    @PutMapping("/{id}")
    public Result putRecycle(@PathVariable Integer id){
        Recycle data = new Recycle();
        data.setId(id);
        //将回收订单状态改为已取消（4）
        data.setState(4);
        recycleService.updateById(data);
        return Result.success();
    }
    //用户删除二手回收订单（处于已完成和已取消）
    @DeleteMapping("/{id}")
    public Result delRecycle(@PathVariable Integer id){
        recycleService.removeById(id);
        return Result.success();
    }
    //用户确认同意回收的价格
    @PutMapping("/{id}/confirm")
    public Result confirmRecycle(@PathVariable Integer id){
        //将待客户确认（2）转为已完成（3）
        Recycle data = new Recycle();
        data.setId(id);
        data.setState(3);
        recycleService.updateById(data);
        return Result.success();
    }
    //管理员后台处理模块

    //管理员确认同意二手回收估价
    @PutMapping("/{id}/admin/confirm")
    public Result AdminConfirmRecycle(@PathVariable Integer id){
        //将待处理（1）转为待客户确认（2）
        Recycle data = new Recycle();
        data.setId(id);
        data.setState(2);
        recycleService.updateById(data);
        return Result.success();
    }
    //管理员改变二手回收价
    @PostMapping("/changeEvaluate")
    public Result changeEvaluate(@RequestBody Recycle recycle){
        //前端将改变的价格和id传过来
        recycleService.updateById(recycle);
        return Result.success();
    }
    //分页条件查询所有二手回收订单情况
    @GetMapping("/admin")
    public Result getRecycleByAdmin(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam Integer state,String searchText){
        //根据订单编号、联系人、联系电话、商品名模糊查询
        //分页查询获取数据
        Page<Recycle> recyclePage =new Page<>(page,pageSize);
        QueryWrapper<Recycle> queryWrapper =new QueryWrapper<>();
        //查询需判断两种情况,当orderState为0时应该查询用户相关的所有订单
        if(state!=0) {
            queryWrapper.eq("state", state);
        }
        //条件查询
        if(searchText.length()!=0){
            queryWrapper.and(wrapper -> wrapper.like("id", searchText).or().like("receiver_contact", searchText)
                    .or().like("receiver_mobile", searchText).or().like("goods_name", searchText));

        }
        // 添加排序条件，按照订单创建时间倒序排序
        queryWrapper.orderByDesc("create_time");
        Page<Recycle> recycleList = recycleService.page(recyclePage,queryWrapper);
        RecycleResult data = new RecycleResult(recycleList.getRecords(),recycleList.getCurrent(),recycleList.getPages(),
                recycleList.getSize(),recycleList.getTotal());
        return Result.success(data);
    }


}
