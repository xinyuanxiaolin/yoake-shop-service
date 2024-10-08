package com.shop.service.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.mapper.BannerMapper;
import com.shop.service.pojo.Banner;
import com.shop.service.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public List<Banner> getByType(String distributionSite) {
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",distributionSite);
        return bannerMapper.selectList(queryWrapper);

    }

    @Override
    public List<Banner> getBanner() {
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        return bannerMapper.selectList(queryWrapper);
    }

    @Override
    public void putBanner(Banner banner) {
        if(banner.getId()==null){
            //新增
            bannerMapper.insert(banner);
        }else {
            //修改
            bannerMapper.updateById(banner);
        }
    }

    @Override
    public void deleteBanner(List<Integer> ids) {
        bannerMapper.deleteBatchIds(ids);
    }

}
