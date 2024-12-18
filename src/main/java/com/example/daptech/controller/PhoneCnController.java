package com.example.daptech.controller;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.vo.PhoneCnVo;
import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneCnService;
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
@Tag(name = "号码信息控制器")
public class PhoneCnController {
    private final PhoneCnService phoneCnService;


    /**
     * 新增号码信息
     * @param token
     * @param phoneNumber
     * @return
     */
    @GetMapping("/selectByPhoneNumber")  //根据手机号查询号码信息
    @Operation(summary = "查询号码信息")
    public Result<PhoneCnVo> selectByPhoneNumber(@RequestHeader("Authorization")String token, @RequestParam String phoneNumber) {

            return phoneCnService.selectByPhoneCn(phoneNumber);

    }



}


