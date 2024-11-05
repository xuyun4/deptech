package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneCnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/phoneCn")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "号码信息控制器")
public class PhoneCnController {
    private final PhoneCnService phoneCnService;


    @GetMapping("/selectByPhoneNumber")  //根据手机号查询号码信息
    @Operation(summary = "查询号码信息")
    public Result selectByPhoneNumber(@RequestParam String phoneNumber) {

            return phoneCnService.selectByPhoneCn(phoneNumber);

    }



}


