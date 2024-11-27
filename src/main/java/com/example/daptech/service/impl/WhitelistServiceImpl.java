package com.example.daptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daptech.entity.Whitelist;
import com.example.daptech.entity.dto.WhitelistDto;
import com.example.daptech.entity.vo.WhitelistVo;
import com.example.daptech.mapper.WhitelistMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.WhitelistService;
import com.example.daptech.util.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WhitelistServiceImpl extends ServiceImpl<WhitelistMapper, Whitelist> implements WhitelistService {
    @Autowired
    private WhitelistMapper whitelistMapper;

    @Override
    public Result updateWhitelist(String token, List<WhitelistDto> whitelistDtoList) {
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Whitelist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        whitelistMapper.delete(queryWrapper);
        List<Whitelist> whitelistList = new ArrayList<>();
        for (WhitelistDto whitelistDto : whitelistDtoList) {
            Whitelist whitelist = new Whitelist();
            BeanUtils.copyProperties(whitelistDto, whitelist);
            whitelist.setUserId(userId);
            whitelistList.add(whitelist);
        }
        saveBatch(whitelistList);
        return Result.success();
    }

    @Override
    public Result<List<WhitelistVo>> getWhitelist(String token) {
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Whitelist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Whitelist> whitelistList = whitelistMapper.selectList(queryWrapper);
        List<WhitelistVo> whitelistVoList = new ArrayList<>();
        for (Whitelist whitelist : whitelistList) {
            WhitelistVo whitelistVo = new WhitelistVo();
            BeanUtils.copyProperties(whitelist, whitelistVo);
            whitelistVoList.add(whitelistVo);
        }
        return Result.success(whitelistVoList);
    }
}
