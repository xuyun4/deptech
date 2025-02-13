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
        PhoneCn phoneCn = phoneCnMapper.selectByPhoneCn(phone);
        if (phoneCn == null) {
            return Result.success("不拦截");
        }

        Integer maxNumber = phoneCnMapper.getMaxNumber();
        double riskValue = calculateRiskValue(phoneCn.getNumber(), maxNumber);

        if(riskValue>=30){
            return Result.success("拦截");
        }

        return Result.success("不拦截");
    }

    @Override
    public Result<String> judgeUs(String phone) {
        PhoneUs phoneUs = phoneUsMapper.selectByPhoneUs(phone);
        if (phoneUs == null) {
            return Result.success("不拦截");
        }

        Integer maxNumber = phoneUsMapper.getMaxNumber();
        double riskValue = calculateRiskValue(phoneUs.getNumber(), maxNumber);

        if(riskValue>=30){
            return Result.success("拦截");
        }

        return Result.success("不拦截");
    }

    public double calculateRiskValue(int number, int maxNumber) {
        if (maxNumber == 0) {
            return 0;
        }
        // 使用对数平滑公式计算风险值
        return (Math.log(number + 1) / Math.log(maxNumber + 1)) * 100;
    }

}

