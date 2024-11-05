package com.example.daptech.controller;

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
    @Operation(summary = "后端更新来电记录")
    public Result updateCall(@RequestHeader("Authorization") String token, @Valid @RequestBody List<CallDto> callDtoList) {
        return callService.updateCall(token, callDtoList);
    }

    //查询来电记录,返回前端
    @GetMapping("/getCall")
    @Operation(summary = "本地更新来电记录")
    public Result getCall(@RequestHeader("Authorization") String token) {
        return callService.getCall(token);
    }
}
