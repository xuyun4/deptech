package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneMarkService;
import com.example.daptech.util.JwtHelper;
import io.swagger.v3.oas.annotations.Operation;
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

    /**
     * 更新手机号的标记信息
     * @param phoneNumber 手机号
     * @param type 标记类型
     * @param mark 标记内容
     * @param token 用户token
     * @return Result
     */
    @PutMapping("/markByPhoneNumber")  //更新手机号的标记信息
    @Operation(summary = "更新号码的标记信息")
    public Result update(String phoneNumber, String type, String mark,
                         @RequestHeader("Authorization")String token) {

            Long userId = JwtHelper.getIdFromToken(token);

            return phoneMarkService.insertMark(phoneNumber,type,mark, userId);

    }

}
