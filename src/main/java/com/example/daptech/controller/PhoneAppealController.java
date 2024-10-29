package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneAppealService;
import com.example.daptech.util.JwtHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/phoneAppeal")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "号码申诉控制器")
public class PhoneAppealController {

    private final PhoneAppealService phoneAppealService;

    @DeleteMapping("/appealByPhoneNumber")
    public Result appealPhone(String phone,
                              @RequestHeader("Authorization")String token){

        if(!JwtHelper.verifyToken(token)) {
            phoneAppealService.submitAppeal(phone);

            return Result.success("申诉提交成功,请等待系统审核");
        }else{
            return Result.error("登录信息缺失");
        }

    }
}
