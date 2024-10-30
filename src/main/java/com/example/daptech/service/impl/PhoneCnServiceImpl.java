package com.example.daptech.service.impl;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.mapper.PhoneCnMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneCnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneCnServiceImpl implements PhoneCnService {
    private final PhoneCnMapper phoneCnMapper;
    @Override
    public String a(int id) {
        return phoneCnMapper.a(id);
    }

    @Override
    public void insertPhoneCn(String phone, String type, Integer number, Integer status, long createTime, long updateTime,String  location) {
        phoneCnMapper.insertPhoneCn(phone, type, number, status, createTime,updateTime,location);
    }

    @Override
    public Result selectByPhoneCn(String phone) {

        PhoneCn phoneCn = phoneCnMapper.selectByPhoneCn(phone);
        if(phoneCn == null){
            return Result.error("手机号不存在");
        }
        return Result.success(phoneCn);

    }

    @Override
    public void updatePhoneType(String phone, String type,Integer number,long updateTime) {
        phoneCnMapper.updatePhoneType(phone, type, number, updateTime);
    }
}
