package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneCnService;
import com.example.daptech.util.JwtHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/phoneCn")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "联系人控制器")
public class PhoneCnController {

    private final PhoneCnService phoneCnService;


    @GetMapping("/selectByPhoneNumber")  //根据手机号查询联系人
    @Operation(summary = "查询联系人")
    public Result selectByPhoneNumber(@RequestParam String phoneNumber,
                                      @RequestHeader("Authorization")String token) {


        if(!JwtHelper.verifyToken(token)){
            return phoneCnService.selectByPhoneCn(phoneNumber);
        }else{
            return Result.error("登录信息缺失");
        }

    }



}


