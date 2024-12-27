package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.InterceptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/intercept")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "拦截控制器")
public class InterceptController {

    private final InterceptService interceptService;

    @GetMapping("/judge")
    @Operation(summary = "判断是否拦截")
    public Result<String> getDatabase(@RequestHeader("Authorization")String token,String phone){
        return interceptService.judge(phone);
    }
}
