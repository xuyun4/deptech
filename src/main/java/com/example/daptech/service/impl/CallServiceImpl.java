package com.example.daptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daptech.entity.Call;
import com.example.daptech.entity.dto.CallDto;
import com.example.daptech.entity.vo.CallVo;
import com.example.daptech.mapper.CallMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.CallService;
import com.example.daptech.util.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallServiceImpl extends ServiceImpl<CallMapper, Call> implements CallService {

    @Autowired
    private CallMapper callMapper;

    @Override
    public Result updateCall(String token, List<CallDto> callDtoList) {
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Call> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        callMapper.delete(queryWrapper);
        List<Call> callList = new ArrayList<>();
        for (CallDto callDto : callDtoList) {
            Call call = new Call();
            BeanUtils.copyProperties(callDto, call);
            call.setUserId(userId);
            callList.add(call);
        }
        saveBatch(callList);
        return Result.success();
    }

    @Override
    public Result<List<CallVo>> getCall(String token) {
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Call> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Call> callList = callMapper.selectList(queryWrapper);
        List<CallVo> callVoList = new ArrayList<>();
        for (Call call : callList) {
            CallVo callVo = new CallVo();
            BeanUtils.copyProperties(call, callVo);
            callVoList.add(callVo);
        }
        return Result.success(callVoList);
    }
}
