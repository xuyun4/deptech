package com.example.deptech.service.impl;

import com.example.deptech.entity.PhoneCn;
import com.example.deptech.mapper.PhoneCnMapper;
import com.example.deptech.service.PhoneCnService;
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
    public PhoneCn selectByPhoneCn(String phone) {

        return phoneCnMapper.selectByPhoneCn(phone);

    }

    @Override
    public void updatePhoneType(String phone, String type,Integer number,long updateTime) {
        phoneCnMapper.updatePhoneType(phone, type, number, updateTime);
    }
}
