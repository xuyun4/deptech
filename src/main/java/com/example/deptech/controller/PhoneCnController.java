package com.example.deptech.controller;

import com.example.deptech.service.PhoneCnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/phoneCn")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "联系人控制器")
public class PhoneCnController {
    //demo
    private final PhoneCnService phoneCnService;

    @PostMapping("/add")
    @Operation(summary = "添加日记")
    public String a(int id) {
        return phoneCnService.a(id);
    }
}
