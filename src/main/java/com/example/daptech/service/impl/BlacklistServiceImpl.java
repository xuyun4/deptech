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
        //删除原本数据
        blacklistMapper.delete(queryWrapper);
        //插入新的数据
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
    public Result<List<BlacklistVo>> getBlacklist(String token) {
        //查询数据
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
        //返回前端
        return Result.success(blacklistVoList);
    }
}
