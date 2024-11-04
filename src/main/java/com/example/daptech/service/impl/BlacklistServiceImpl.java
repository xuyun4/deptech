package com.example.daptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daptech.entity.Blacklist;
import com.example.daptech.entity.dto.BlacklistDto;
import com.example.daptech.entity.vo.BlacklistVo;
import com.example.daptech.mapper.BlacklistMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.BlacklistService;
import com.example.daptech.util.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {
    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public Result updateBlacklist(String token, List<BlacklistDto> blacklistDtoList) {
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Blacklist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        blacklistMapper.delete(queryWrapper);
        List<Blacklist> blacklistList = new ArrayList<>();
        for (BlacklistDto blacklistDto : blacklistDtoList) {
            Blacklist blacklist = new Blacklist();
            BeanUtils.copyProperties(blacklistDto, blacklist);
            blacklist.setUserId(userId);
            blacklistList.add(blacklist);
        }
        saveBatch(blacklistList);
        return Result.success();
    }

    @Override
    public Result getBlacklist(String token) {
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Blacklist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Blacklist> blacklistList = blacklistMapper.selectList(queryWrapper);
        List<BlacklistVo> blacklistVoList = new ArrayList<>();
        for (Blacklist blacklist : blacklistList) {
            BlacklistVo blacklistVo = new BlacklistVo();
            BeanUtils.copyProperties(blacklist, blacklistVo);
            blacklistVoList.add(blacklistVo);
        }
        return Result.success(blacklistVoList);
    }
}
