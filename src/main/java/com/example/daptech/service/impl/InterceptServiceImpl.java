package com.example.daptech.service.impl;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import com.example.daptech.mapper.PhoneCnMapper;
import com.example.daptech.mapper.PhoneUsMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.InterceptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class InterceptServiceImpl implements InterceptService {
    private final PhoneCnMapper phoneCnMapper;
    private final PhoneUsMapper phoneUsMapper;

    @Override
    public Result<String> judgeCn(String phone) {
        PhoneCn phoneCn=phoneCnMapper.selectByPhoneCn(phone);
        if (phoneCn!=null){
            return Result.success("拦截");
        }
        return Result.success("不拦截");
    }

    @Override
    public Result<String> judgeUs(String phone) {
        PhoneUs phoneUs=phoneUsMapper.selectByPhoneUs(phone);
        if (phoneUs!=null){
            return Result.success("拦截");
        }
        return Result.success("不拦截");
    }
}
