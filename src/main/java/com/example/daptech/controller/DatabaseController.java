package com.example.daptech.controller;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import com.example.daptech.response.PageResult;
import com.example.daptech.response.Result;
import com.example.daptech.service.DatabaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/database")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "数据库控制器")
public class DatabaseController {

    private final DatabaseService databaseService;

    //存储数据库表 cn
    @GetMapping("/storeCn")
    @Operation(summary = "Cn离线数据库存储")
    public void storeCnDatabase(@RequestHeader("Authorization") String token/*, HttpServletResponse response*/) {
        databaseService.storeCnDatabase(/*response*/);
    }

    //获取数据库表 Cn
    @GetMapping("/getCn")
    @Operation(summary = "Cn离线数据库获取")
    public Result<PageResult<PhoneCn>> getCnDatabase(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "100") int pageSize) {
        PageResult<PhoneCn> phoneCnPageResult=databaseService.getCnDatabase(pageNum,pageSize);
        return Result.success(phoneCnPageResult);
    }

    //存储数据库表 us
    @GetMapping("/storeUs")
    @Operation(summary = "Us离线数据库存储")
    public void storeUsDatabase(@RequestHeader("Authorization") String token/*, HttpServletResponse response*/) {
        databaseService.storeUsDatabase(/*response*/);
    }

    //获取数据库表 Us
    @GetMapping("/getUs")
    @Operation(summary = "Us离线数据库获取")
    public Result<PageResult<PhoneUs>> getUsDatabase(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "100") int pageSize) {
        PageResult<PhoneUs> phoneUsPageResult=databaseService.getUsDatabase(pageNum,pageSize);
        return Result.success(phoneUsPageResult);
    }
}
