package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.entity.dto.WhitelistDto;
import com.example.daptech.service.WhitelistService;
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
@RequestMapping("/whitelist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "白名单控制器")
public class WhitelistController {

    private final WhitelistService whitelistService;

    //更新白名单表到后端
    @PutMapping("/updateWhitelist")
    @Operation(summary = "后端更新白名单")
    public Result updateWhitelist(@RequestHeader("Authorization")String token, @Valid @RequestBody List<WhitelistDto> whitelistDtoList) {
        return whitelistService.updateWhitelist(token,whitelistDtoList);
    }
    //查询白名单表,返回前端
    @GetMapping("/getWhitelist")
    @Operation(summary = "本地更新白名单")
    public Result getWhitelist(@RequestHeader("Authorization")String token) {
        return whitelistService.getWhitelist(token);
    }
}
