package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.request.findBackPasswordRequest;
import com.example.daptech.request.loginByPhoneNumRequest;
import com.example.daptech.request.loginByVerifyCodeRequest;
import com.example.daptech.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "用户控制器")
public class UserController {

    private final UserService userService;

    //通过账号密码登录
    @PostMapping("/loginByPhoneNum")
    public Result loginByPhoneNum(@Valid @RequestBody loginByPhoneNumRequest request) {
        return userService.loginByPhoneNum(request);
    }

    //通过验证码登录
    @PostMapping("/loginByVerifyCode")
    public Result loginByVerifyCode(@Valid @RequestBody loginByVerifyCodeRequest request) {
        return userService.loginByVerifyCode(request);
    }

    //找回密码
    @PostMapping("/findBackAcct")
    public Result login(@Valid @RequestBody findBackPasswordRequest request) {
        return userService.findBackPassword(request);
    }

    //退出登录
    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization")String token) {
        return userService.logout(token);
    }
}
