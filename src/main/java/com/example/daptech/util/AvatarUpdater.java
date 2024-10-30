package com.example.deptech.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.IAcsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class AvatarUpdater {

    @Value("${aliyun.endpoint}")
    private String endpoint;
    @Value("${aliyun.bucketName}")
    private String bucketName ;

    @Autowired
    private OSSClient ossClient;

    public String uploadAvatar(MultipartFile file) throws IOException {
        // 生成唯一文件名
        String fileName = "avatars/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        // 上传文件到 OSS
        ossClient.putObject(bucketName, fileName, file.getInputStream());
        // 返回文件的 URL
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }

}
