package com.example.daptech.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//验证码发送
@Component
public class SmsSender {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId ;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret ;
    @Value("${aliyun.signName}")
    private String signName;
    @Value("${aliyun.templateCode}")
    private String templateCode ;

    private IAcsClient client;
    @Autowired
    private RedisTemplate<String, String> smsRedisTemplate; // 用于存储验证码

    @PostConstruct
    public void SmsSender() {
        // 创建阿里云短信客户端，不使用 teaconfig
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
        this.client = new DefaultAcsClient(profile);
    }

    // 生成并发送验证码
    public boolean sendVerificationCode(String phoneNumber) {
        String code = generateCode();
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            if ("OK".equals(response.getCode())) {
                smsRedisTemplate.opsForValue().set("SMS_CODE_" + phoneNumber, code, 90, TimeUnit.SECONDS); // 保存验证码
                return true;
            } else {
                System.out.println("发送验证码失败，错误信息: " + response.getMessage());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 验证验证码
    public boolean verifyCode(String phoneNumber, String inputCode) {
        String correctCode = smsRedisTemplate.opsForValue().get("SMS_CODE_" + phoneNumber);
        if (correctCode != null && correctCode.equals(inputCode)) {
            smsRedisTemplate.delete("SMS_CODE_" + phoneNumber); // 验证成功后删除验证码
            return true;
        }
        return false;
    }

    // 生成6位随机验证码
    private String generateCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}

