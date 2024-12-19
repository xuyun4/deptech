package com.example.daptech.service;

import com.example.daptech.entity.UserInfo;
import com.example.daptech.request.FindBackPasswordRequest;
import com.example.daptech.request.LoginByPhoneNumRequest;
import com.example.daptech.request.LoginByVerifyCodeRequest;
import com.example.daptech.response.Result;
import com.example.daptech.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author 24333
* @description 针对表【user】的数据库操作Service
* @createDate 2024-10-24 10:59:28
*/
public interface UserService extends IService<User> {
    //用户账号密码登录或注册
    Result<String> loginByPhoneNum(@RequestBody LoginByPhoneNumRequest request);
    //用户验证码登录
    Result<String> loginByVerifyCode(@RequestBody LoginByVerifyCodeRequest request);
    //用户找回密码
    Result<String> findBackPassword(@RequestBody FindBackPasswordRequest request);
    //用户退出登录
    Result logout(@RequestHeader(value = "Authorization", required = true)String jwtToken);
    //用户修改昵称
    Result changeNickname(@RequestHeader(value = "Authorization", required = true)String jwtToken,String nickname);
    //用户上传头像
    Result updateAvatar(@RequestHeader(value = "Authorization", required = true)String jwtToken, MultipartFile file) throws IOException;
    //用户发送验证码
    Result sendSms(String phonenumber);
    //获取用户信息
    Result<UserInfo> getInfo(@RequestHeader(value = "Authorization", required = true)String jwtToken);
}
