package com.example.deptech.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pendingPhone")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "待定号码控制器")
public class PendingPhoneController {

}
