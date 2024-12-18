package com.example.daptech.service;

import com.example.daptech.response.Result;

public interface PhoneMarkService {

    /**
     * 插入手机号码标记
     * @param phone
     * @param type
     * @param mark
     * @param userId
     * @return
     */
    Result insertMark(String phone, String type, String mark, Long userId);


    /**
     * 更新手机号码标记
     * @param phoneNumber
     */
    void updatePhoneMark(String phoneNumber);
}
