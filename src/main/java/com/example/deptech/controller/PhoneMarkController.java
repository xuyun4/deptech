package com.example.deptech.controller;

import com.example.deptech.response.Result;
import com.example.deptech.service.PhoneMarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Result update(String phoneNumber, String type, String mark) {

        phoneMarkService.insertMark(phoneNumber,type,mark);
        phoneMarkService.updatePhoneMark(phoneNumber);
        return Result.success();
    }

}
