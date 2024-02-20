package com.shop.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.service.common.JwtToken;
import com.shop.service.mapper.*;
import com.shop.service.pojo.Address;
import com.shop.service.pojo.Cart;


import com.shop.service.pojo.category.CategoryTopItem;
import com.shop.service.pojo.order.GoodsList;
import com.shop.service.pojo.order.OrderCreateParams;
import com.shop.service.pojo.order.OrderProducts;
import com.shop.service.pojo.order.Orders;
import com.shop.service.pojo.order.orderPre.OrderPreGoods;
import com.shop.service.pojo.order.orderPre.OrderPreResult;
import com.shop.service.pojo.order.orderPre.Summary;
import com.shop.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private JwtToken jwtToken;

    //实现填写订单-获取直接购买订单
    @Override
    public OrderPreResult getPreNow(Integer goodsId, Integer count, Integer addressId) {
        //封装商品信息goods
        OrderPreResult res = new OrderPreResult();
        //获取商品基本信息
        List<Integer> ids =new ArrayList<>();
        ids.add(goodsId);
        List<OrderPreGoods> goodsItem = goodsMapper.getOrderPreGoods(ids);
//        log.info("物品:{}",goodsItem);
        //把数量拼接到goodsItem里面
        goodsItem.stream().map(v->{
            v.setCount(count);
            return v;
        }).collect(Collectors.toList());
        //封装购买货物结果
        res.setGoods(goodsItem);
        //接下来计算总价
        Double totalPrice = goodsItem.get(0).getNowPrice()*goodsItem.get(0).getCount();
        Double totalPayPrice = totalPrice+5;
        Summary summary =new Summary(totalPrice,5,totalPayPrice);
        //封装总价计算结果
        res.setSummary(summary);
        //接下来根据前端传来addressId获取用户的地址
        Address address= addressMapper.selectById(addressId);
        List<Address> addresses =new ArrayList<>();
        addresses.add(address);
        //封装地址
        res.setUserAddresses(addresses);

        return res;
    }

    //填写订单-获取购物车内预付订单
    @Override
    public OrderPreResult getPre() {
        //封装商品信息goods
        OrderPreResult res = new OrderPreResult();
        //首先就是先通过Jwt解析到的userId来获取购物车内用户已选择的商品
        Integer userId = jwtToken.getUserIdByToken();
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("selected",1);
        List<Cart> carts =cartMapper.selectList(queryWrapper);
        //去封装一个OrderPreGoods的List
        List<OrderPreGoods> goodsList = carts.stream().map(v->{
            return new OrderPreGoods(v.getGoodsId(),v.getCount(),v.getName(),v.getNowPrice(),v.getPicture(),v.getPrice());
        }).collect(Collectors.toList());
        res.setGoods(goodsList);
        //接下获取Summary
        Double totalPrice = goodsList.stream().reduce(
                (double) 0,
                (data,goods)->data+goods.getCount()* goods.getNowPrice(),
                Double::sum
        );
        Double totalPayPrice = totalPrice+5;
        Summary summary =new Summary(totalPrice,5,totalPayPrice);
        //封装summary
        res.setSummary(summary);
        //直接获取用户的所有地址返回给前端
        List<Address> address = addressMapper.selectList(new QueryWrapper<Address>().eq("user_id",userId));
        res.setUserAddresses(address);
        return res;
    }

    //提交订单
    @Override
    public Orders postOrder(OrderCreateParams data) {
        //新建一个Orders对象
        Orders orders = new Orders(null,jwtToken.getUserIdByToken(),1,null,null,
                null, LocalDateTime.now(),5,null,null, data.getBuyerMessage(), null,null,null);

        //接下去去利用addressId查询到相关地址信息和联系人相关信息
        Address address= addressMapper.selectById(data.getAddressId());
        //设置相关信息到orders订单中
        orders.setReceiverContact(address.getReceiver());
        orders.setReceiverMobile(address.getContact());
        orders.setReceiverAddress(address.getFullLocation()+" "+address.getAddress());
        //保存到订单数据库
        orderMapper.insert(orders);
        //保存商品与订单数据库的关联
        log.info("{}",data.getGoods());
        List<GoodsList> goodsList = data.getGoods();
        for (GoodsList list : goodsList) {
            //把每个商品的id和数量保存到订单和商品的关联表中
            orderMapper.addOrderProducts(list.getCount(), list.getGoodsId(), orders.getId());
        }
        if(data.getPayType()==2){
            //在购物车提交订单后其实要清空购物车
            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",jwtToken.getUserIdByToken()).eq("selected",1);
            cartMapper.delete(queryWrapper);
        }


        return orders;
    }

    //获得订单详情
    @Override
    public Orders getOrderById(Integer id) {
        //先获取到基本信息
        Orders order = orderMapper.selectById(id);
        order.setTotalMoney(0.0);
        order.setTotalNum(0);
        //开始写入商品列表,总价,加上运费后应付金额
        //获取下商品Id及他的数量
        List<OrderProducts> orderProducts = orderMapper.getOrderProducts(order.getId());
        //商品列表
        List<CategoryTopItem> categoryTopItem = new ArrayList<>();
        orderProducts.forEach(v->{
            //获取到商品的详情信息

            CategoryTopItem data = goodsMapper.selectById(v.getGoodsId());
            //由于订单和商品相关联删除会报错,所以只能商品删除了,订单内相关的商品也要删除了
            if(data!=null){
                categoryTopItem.add(data);
                //然后在获取总价格
                order.setTotalMoney(order.getTotalMoney()+data.getNowPrice()*v.getQuantity());
                //设置总数量
                order.setTotalNum(order.getTotalNum()+v.getQuantity());
            }


        });
        //设置商品列表
        order.setGoods(categoryTopItem);
        //接下来加上运费后应付金额
        order.setPayMoney(order.getTotalMoney()+ order.getPostFee());
        return order;
    }

    //取消订单
    @Override
    public void cancelOrder(Integer id, String cancelReason) {
        //当订单状态为待付款(1)时取消订单
        UpdateWrapper<Orders> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",id).eq("order_state",1).set("order_state",6)
                .set(cancelReason!=null,"cancel_reason",cancelReason);
        orderMapper.update(null,updateWrapper);
    }

    //删除订单
    @Override
    public void deleteOrder(List<String> ids) {
        //删除订单表的数据
        orderMapper.deleteBatchIds(ids);
        //删除订单商品关联表的数据
        orderMapper.deleteOrderProducts(ids);
    }



    //改变订单相关状态
    @Override
    public void changeOrderState(String id, int i) {
        UpdateWrapper<Orders> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("order_state",i);
        orderMapper.update(null,updateWrapper);
    }


}
