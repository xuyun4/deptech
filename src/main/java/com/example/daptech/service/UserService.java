package com.example.daptech.service;


import com.example.daptech.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.daptech.request.findBackPasswordRequest;
import com.example.daptech.request.loginByPhoneNumRequest;
import com.example.daptech.request.loginByVerifyCodeRequest;
import com.example.daptech.response.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
* @author 24333
* @description 针对表【user】的数据库操作Service
* @createDate 2024-10-24 10:59:28
*/
public interface UserService extends IService<User> {
    //用户账号密码登录或注册
    Result loginByPhoneNum(@RequestBody loginByPhoneNumRequest request);
    //用户验证码登录
    Result loginByVerifyCode(@RequestBody loginByVerifyCodeRequest request);
    //用户找回密码
    Result findBackPassword(@RequestBody findBackPasswordRequest request);
    //用户退出登录
    Result logout(@RequestHeader("Authorization")String jwtToken);
}
