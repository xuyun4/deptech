package com.example.daptech.controller;

import com.example.daptech.entity.UserInfo;
import com.example.daptech.response.Result;
import com.example.daptech.request.FindBackPasswordRequest;
import com.example.daptech.request.LoginByPhoneNumRequest;
import com.example.daptech.request.LoginByVerifyCodeRequest;
import com.example.daptech.service.UserService;
import com.example.daptech.util.JwtHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "用户控制器")
public class UserController {

    private final UserService userService;

    //通过账号密码登录
    @PostMapping("/loginByPhoneNum")
    @Operation(summary = "通过账号密码注册登录")
    public Result<String> loginByPhoneNum(@Valid @RequestBody LoginByPhoneNumRequest request) {
        return userService.loginByPhoneNum(request);
    }

    //通过验证码登录
    @PostMapping("/loginByVerifyCode")
    @Operation(summary = "通过验证码登录")
    public Result<String> loginByVerifyCode(@Valid @RequestBody LoginByVerifyCodeRequest request) {
        return userService.loginByVerifyCode(request);
    }

    //找回密码
    @PostMapping("/findBackAcct")
    @Operation(summary = "找回密码")
    public Result<String> findBackAcct(@Valid @RequestBody FindBackPasswordRequest request) {
        return userService.findBackPassword(request);
    }

    //退出登录
    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public Result logout(@RequestHeader(value = "Authorization", required = true)String token) {
        return userService.logout(token);
    }

    //用户修改昵称
    @PutMapping("/changeNickname")
    @Operation(summary = "用户修改昵称")
    public Result changeNickname(@RequestHeader(value = "Authorization", required = true)String jwtToken, String nickname){
        return userService.changeNickname(jwtToken,nickname);
    }

    //用户上传头像url
    @PutMapping("/updateAvatar")
    @Operation(summary = "用户上传头像url")
    public Result updateAvatar(@RequestHeader(value = "Authorization", required = true)String jwtToken, MultipartFile file) throws IOException {
        return userService.updateAvatar(jwtToken,file);
    }

    //用户发送验证码
    @PostMapping("/snedSms")
    @Operation(summary = "给用户发送验证码")
    public Result sendSms(String phonenumber){
        return userService.sendSms(phonenumber);
    }

    //根据token获取用户信息
    @GetMapping("/getInfo")
    @Operation(summary = "获取用户信息")
    public Result<UserInfo> getInfo(@RequestHeader(value = "Authorization", required = true)String token){
        return userService.getInfo(token);
    }
}
