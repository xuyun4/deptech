package com.example.deptech.controller;

import com.example.deptech.entity.Result;
import com.example.deptech.request.findBackPasswordRequest;
import com.example.deptech.request.loginByPhoneNumRequest;
import com.example.deptech.request.loginByVerifyCodeRequest;
import com.example.deptech.service.UserService;
import com.example.deptech.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "用户控制器")
public class UserController {

    private final UserService userService;

    //
    @PostMapping("/loginByPhoneNum")
    public Result loginByPhoneNum(@Valid @RequestBody loginByPhoneNumRequest request) {
        return userService.loginByPhoneNum(request);
    }

    @PostMapping("/loginByVerifyCode")
    public Result loginByVerifyCode(@Valid @RequestBody loginByVerifyCodeRequest request) {
        return userService.loginByVerifyCode(request);
    }

    @PostMapping("/findBackAcct")
    public Result login(@Valid @RequestBody findBackPasswordRequest request) {
        return userService.findBackPassword(request);
    }
}
