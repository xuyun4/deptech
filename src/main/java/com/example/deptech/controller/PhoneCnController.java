package com.example.deptech.controller;

import com.example.deptech.entity.PhoneCn;
import com.example.deptech.response.Result;
import com.example.deptech.service.PhoneCnService;
import com.example.deptech.service.PhoneMarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/phoneCn")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "联系人控制器")
public class PhoneCnController {
    //demo
    /*private final PhoneCnService phoneCnService;

    @PostMapping("/add")
    @Operation(summary = "添加日记")
    public String a(int id) {
        return phoneCnService.a(id);
    }*/

    private final PhoneCnService phoneCnService;


    @GetMapping("/selectByPhoneNumber")  //根据手机号查询联系人
    public Result selectByPhoneNumber(String phoneNumber) {

        PhoneCn phoneCn = phoneCnService.selectByPhoneCn(phoneNumber);


        return Result.success(phoneCn);
    }



}


