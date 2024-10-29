package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneMarkService;
import com.example.daptech.util.JwtHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/phoneMark")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "号码标记控制器")
public class PhoneMarkController {
    private final PhoneMarkService phoneMarkService;
    @PutMapping("/markByPhoneNumber")  //更新手机号的标记信息
    public Result update(String phoneNumber, String type, String mark,
                         @RequestHeader("Authorization")String token) {

        if(!JwtHelper.verifyToken(token)){
        phoneMarkService.insertMark(phoneNumber,type,mark);
        phoneMarkService.updatePhoneMark(phoneNumber);
        return Result.success("标记成功");
        }else{
            return Result.error("登录信息缺失");
        }
    }

}
