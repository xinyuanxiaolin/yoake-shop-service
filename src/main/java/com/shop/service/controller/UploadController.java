package com.shop.service.controller;

import com.shop.service.pojo.Result;
import com.shop.service.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
//    文件上传 本地存储
//    @PostMapping("/upload")
//    public Result upload(Integer id, Integer type, MultipartFile file) throws IOException {
//        //type:1为商品主图,2为商品海报,3为用户头像,4为主页banner,5为分类页banner
//        log.info("文件上传:{},{},{}",id,type,file);
//        //将文件存储在服务器磁盘目录当中
//
//        //获取原始文件名字
//        String originalFilename = file.getOriginalFilename();
//        //构建唯一的文件名 -uuid(通用唯一识别码)
//        int index = originalFilename.lastIndexOf(".");
//        String  extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString()+extname;
//        log.info("新文件名:{}",newFileName);
//        //储存
//        file.transferTo(new File("D:\\学习\\编程练习文件夹\\文件缓存\\"+newFileName));
//        String url = "D:\\学习\\编程练习文件夹\\文件缓存\\"+newFileName;
//        Map<String, String> data = new HashMap<>();
//        data.put("url", url);
//
//        return Result.success(data);
//    }
    //文件上传阿里云OSS
    @PostMapping("/upload")
    public Result upload( MultipartFile file) throws IOException {
        String url=  aliOSSUtils.upload(file);
        Map<String, String> data = new HashMap<>();
        data.put("url", url);
        return Result.success(data);
    }

}
