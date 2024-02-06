package com.shop.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.service.mapper.GuessMapper;
import com.shop.service.pojo.Guess;
import com.shop.service.pojo.category.GoodsItem;
import com.shop.service.service.GuessService;
import org.springframework.stereotype.Service;

@Service
public class GuessServiceImpl extends ServiceImpl<GuessMapper, GoodsItem> implements GuessService {

}
