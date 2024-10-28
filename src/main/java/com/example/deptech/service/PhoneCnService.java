package com.example.deptech.service;

import com.example.deptech.entity.PhoneCn;

public interface PhoneCnService  {
    String a(int id);


    void insertPhoneCn(String phone, String type, Integer number, Integer status, long createTime, long updateTime, String location);

    PhoneCn selectByPhoneCn(String phone);

    void updatePhoneType(String phone, String type,Integer number,long updateTime);

}
