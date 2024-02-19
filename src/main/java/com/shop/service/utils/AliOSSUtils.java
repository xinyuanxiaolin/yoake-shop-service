package com.shop.service.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
/*阿里云OSS工具类,用于图片上传*/
public class AliOSSUtils {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // STS临时访问密钥AccessKey ID和AccessKey Secret。
    private String accessKeyId = "LTAI5tKSGeLGo5MX62ZEtKrR";
    private String accessKeySecret = "KZJvBpqLPdiYqMj96RoDC1qD8OiB6B";
    // 使用代码嵌入的STS临时访问密钥和安全令牌配置访问凭证。
    CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
    // 填写Bucket名称，例如examplebucket。
    private String bucketName = "web-yoake";

    /**
     * 实现上传图片到阿里OSS
     * */
    public String upload(MultipartFile file) throws IOException{
        //获取上传的文件输入流
        InputStream inputStream =file.getInputStream();
        //避免文件重复
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传到OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
        PutObjectResult result = ossClient.putObject(putObjectRequest);

        //封装文件访问路径回显
        String url = endpoint.split("//")[0] + "//" + bucketName + "." +endpoint.split("//")[1]+"/"+fileName;
        //关闭ossClient
        ossClient.shutdown();
        return url;
    }



}
