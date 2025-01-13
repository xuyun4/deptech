package com.example.daptech.controller;

import com.example.daptech.entity.vo.CallVo;
import com.example.daptech.entity.vo.ReportVo;
import com.example.daptech.response.Result;
import com.example.daptech.entity.dto.CallDto;
import com.example.daptech.service.CallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/call")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "来电记录控制器")
public class CallController {

    private final CallService callService;

    //更新来电记录到后端
    @PutMapping("/updateCall")
    @Operation(summary = "后端数据库更新来电记录")
    public Result updateCall(@RequestHeader("Authorization") String token, @Valid @RequestBody List<CallDto> callDtoList) {
        return callService.updateCall(token, callDtoList);
    }

    //查询来电记录,返回前端
    @GetMapping("/getCall")
    @Operation(summary = "本地更新来电记录")
    public Result<List<CallVo>> getCall(@RequestHeader("Authorization") String token) {
        return callService.getCall(token);
    }

    //返回近一周每天的骚扰电话数量,及一周内骚扰电话位置和类型的统计
    @GetMapping("/getWeekReport")
    @Operation(summary = "查询近一周骚扰电话信息")
    public Result<ReportVo> getWeekReport(@RequestHeader("Authorization") String token) {
        return callService.getWeekReport(token);
    }

    //返回近一月每周的骚扰电话数量,及一月内骚扰电话位置和类型的统计
    @GetMapping("/getMonthReport")
    @Operation(summary = "查询近一月骚扰电话")
    public Result<ReportVo> getMonthReport(@RequestHeader("Authorization") String token) {
        return callService.getMonthReport(token);
    }

    //返回近一年每月的骚扰电话数量,及一年内骚扰电话位置和类型的统计
    @GetMapping("/getYearReport")
    @Operation(summary = "查询近一年骚扰电话")
    public Result<ReportVo> getYearReport(@RequestHeader("Authorization") String token) {
        return callService.getYearReport(token);
    }
}
