package com.shop.service.service;

import com.shop.service.pojo.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> getByType(String distributionSite);
}
