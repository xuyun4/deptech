package com.example.deptech.service;

import com.example.deptech.entity.Result;
import com.example.deptech.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.deptech.request.findBackPasswordRequest;
import com.example.deptech.request.loginByPhoneNumRequest;
import com.example.deptech.request.loginByVerifyCodeRequest;
import org.springframework.web.bind.annotation.RequestBody;

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
}
