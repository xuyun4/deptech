package com.example.daptech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.daptech.entity.Blacklist;
import com.example.daptech.entity.vo.BlacklistVo;
import com.example.daptech.response.Result;
import com.example.daptech.entity.dto.BlacklistDto;

import java.util.List;

public interface BlacklistService extends IService<Blacklist> {

    Result<List<BlacklistDto>> updateBlacklist(String token, List<BlacklistDto> blacklistDtoList);

    Result<List<BlacklistVo>> getBlacklist(String token);
}
