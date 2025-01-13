package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.service.DatabaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/database")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "数据库控制器")
public class DatabaseController {

    private final DatabaseService databaseService;

    //返回数据库表到前端 cn
    @GetMapping("/getCn")
    @Operation(summary = "Cn离线数据库下载")
    public Result getCnDatabase(@RequestHeader("Authorization") String token, HttpServletResponse response) {
        databaseService.getCnDatabase(response);
        return Result.success();
    }

    //返回数据库表到前端 us
    @GetMapping("/getUs")
    @Operation(summary = "Us离线数据库下载")
    public Result getUsDatabase(@RequestHeader("Authorization") String token, HttpServletResponse response) {
        databaseService.getUsDatabase(response);
        return Result.success();
    }
}
