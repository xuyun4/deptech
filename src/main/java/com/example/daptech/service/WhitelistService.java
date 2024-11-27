package com.example.daptech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.daptech.entity.vo.WhitelistVo;
import com.example.daptech.response.Result;
import com.example.daptech.entity.Whitelist;
import com.example.daptech.entity.dto.WhitelistDto;

import java.util.List;

public interface WhitelistService extends IService<Whitelist> {

    Result updateWhitelist(String token, List<WhitelistDto> whitelistDtoList);

    Result<List<WhitelistVo>> getWhitelist(String token);
}