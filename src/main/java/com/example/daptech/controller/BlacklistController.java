package com.example.daptech.controller;

import com.example.daptech.entity.dto.BlacklistDto;
import com.example.daptech.entity.vo.BlacklistVo;
import com.example.daptech.response.Result;
import com.example.daptech.service.BlacklistService;
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
@RequestMapping("/blacklist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "黑名单控制器")
public class BlacklistController {

    private final BlacklistService blacklistService;

    //前端传黑名单表到后端,完成数据库黑名单表的更新
    @PutMapping("/updateBlacklist")
    @Operation(summary = "后端数据库更新黑名单")
    public Result updateBlacklist(@RequestHeader("Authorization")String token, @Valid @RequestBody List<BlacklistDto> blacklistDtoList) {
        return blacklistService.updateBlacklist(token,blacklistDtoList);
    }
    //后端查询黑名单表返回前端
    @GetMapping("/getBlacklist")
    @Operation(summary = "本地更新黑名单")
    public Result<List<BlacklistVo>> getBlacklist(@RequestHeader("Authorization")String token) {
        return blacklistService.getBlacklist(token);
    }
}
