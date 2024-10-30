package com.example.daptech.service;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.response.Result;

public interface PhoneCnService  {
    String a(int id);


    void insertPhoneCn(String phone, String type, Integer number, Integer status, long createTime, long updateTime, String location);

    Result selectByPhoneCn(String phone);

    void updatePhoneType(String phone, String type,Integer number,long updateTime);

}
