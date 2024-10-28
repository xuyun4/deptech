package com.example.deptech.controller;

import com.example.deptech.response.Result;
import com.example.deptech.request.FindBackPasswordRequest;
import com.example.deptech.request.LoginByPhoneNumRequest;
import com.example.deptech.request.LoginByVerifyCodeRequest;
import com.example.deptech.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "用户控制器")
public class UserController {

    private final UserService userService;

    //通过账号密码登录
    @PostMapping("/loginByPhoneNum")
    public Result loginByPhoneNum(@Valid @RequestBody LoginByPhoneNumRequest request) {
        return userService.loginByPhoneNum(request);
    }

    //通过验证码登录
    @PostMapping("/loginByVerifyCode")
    public Result loginByVerifyCode(@Valid @RequestBody LoginByVerifyCodeRequest request) {
        return userService.loginByVerifyCode(request);
    }

    //找回密码
    @PostMapping("/findBackAcct")
    public Result findBackAcct(@Valid @RequestBody FindBackPasswordRequest request) {
        return userService.findBackPassword(request);
    }

    //退出登录
    @PostMapping("/logout")
    public Result logout(@RequestHeader(value = "Authorization", required = true)String token) {
        return userService.logout(token);
    }

    //用户修改昵称
    @PutMapping("/changeNickname")
    public Result changeNickname(@RequestHeader(value = "Authorization", required = true)String jwtToken, String nickname){
        return userService.changeNickname(jwtToken,nickname);
    }

    //用户上传头像url
    @PutMapping("/updateAvatar")
    public Result updateAvatar(@RequestHeader(value = "Authorization", required = true)String jwtToken, MultipartFile file){
        return userService.updateAvatar(jwtToken,file);
    }

    //用户发送验证码
    @PostMapping("/snedSms")
    public Result sendSms(String phonenumber){
        return userService.sendSms(phonenumber);
    }
}
