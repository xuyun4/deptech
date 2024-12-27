package com.example.daptech.controller;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.response.Result;
import com.example.daptech.service.DatabaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/database")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "数据库控制器")
public class DatabaseController {

    private final DatabaseService databaseService;

    @GetMapping("/get")
    @Operation(summary = "离线数据库下载")
    public Result<List<PhoneCn>> getDatabase(@RequestHeader("Authorization")String token){
        return databaseService.getDatabase();
    }
}
