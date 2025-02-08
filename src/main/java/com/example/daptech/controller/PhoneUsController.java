package com.example.daptech.controller;

import com.example.daptech.entity.vo.PhoneCnVo;
import com.example.daptech.entity.vo.PhoneUsVo;
import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneUsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/phoneUs")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "US号码信息控制器")
public class PhoneUsController {

    private final PhoneUsService phoneUsService;
    /**
     * 新增号码信息
     * @param token
     * @param phoneNumber
     * @return
     */
    @GetMapping("/selectByPhoneNumber")  //根据手机号查询号码信息
    @Operation(summary = "查询号码信息")
    public Result<PhoneUsVo> selectByPhoneNumber(@RequestHeader("Authorization")String token, @RequestParam String phoneNumber) {

        return phoneUsService.selectByPhoneUs(phoneNumber);

    }

    /**
     * 批量查询号码信息
     * @param token
     * @param phoneNumbers
     * @return
     */
    @GetMapping("/selectByPhoneNumbers")  //批量查询号码信息
    @Operation(summary = "批量查询号码信息,参数形如：phoneNumbers= 13812345678,13812345679 ")
    public Result<List<PhoneUsVo>> selectByPhoneNumbers(@RequestHeader("Authorization")String token, @RequestParam List<String> phoneNumbers) {
        return phoneUsService.selectByPhoneNumbers(phoneNumbers);
    }

}
