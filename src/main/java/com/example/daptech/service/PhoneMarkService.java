package com.example.daptech.service;

import com.example.daptech.response.Result;

public interface PhoneMarkService {
    Result insertMark(String phone, String type, String mark, Long userId);


    void updatePhoneMark(String phoneNumber);
}
