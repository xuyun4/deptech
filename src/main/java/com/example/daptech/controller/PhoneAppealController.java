package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneAppealService;
import com.example.daptech.util.JwtHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/phoneAppeal")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "号码申诉控制器")
public class PhoneAppealController {

    private final PhoneAppealService phoneAppealService;

    @DeleteMapping("/appealByPhoneNumber")
    @Operation(summary = "号码申诉")
    public Result appealPhone(String phone,
                              @RequestHeader("Authorization")String token){

            Long userId = JwtHelper.getIdFromToken(token);

            return phoneAppealService.submitAppeal(phone, userId); //需要从token中获取用户id


    }

    @GetMapping("/getAppeal") //申诉列表,包括审核结果,0表示等待系统审核,1表示审核通过,2表示审核不通过
    @Operation(summary = "获取号码申诉列表")
    public Result getAppeal(@RequestHeader("Authorization")String token){

            Long userId = JwtHelper.getIdFromToken(token);

            return phoneAppealService.getAppeal(userId); //需要从token中获取用户id

    }


}
