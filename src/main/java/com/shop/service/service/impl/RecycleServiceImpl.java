package com.shop.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.service.mapper.RecycleMapper;
import com.shop.service.pojo.recycle.Recycle;
import com.shop.service.service.RecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecycleServiceImpl extends ServiceImpl<RecycleMapper, Recycle> implements RecycleService {
    @Autowired
    private RecycleMapper recycleMapper;
}
