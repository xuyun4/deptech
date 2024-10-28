package com.example.deptech.controller;

import com.example.deptech.response.Result;
import com.example.deptech.service.PhoneAppealService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public Result appealPhone(String phone){

        return null;

    }
}
