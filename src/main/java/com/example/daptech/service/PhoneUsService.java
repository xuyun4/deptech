package com.example.daptech.service;

import com.example.daptech.entity.vo.PhoneCnVo;
import com.example.daptech.entity.vo.PhoneUsVo;
import com.example.daptech.response.Result;

import java.util.List;

public interface PhoneUsService {
    /**
     * 查询手机信息
     * @param phone
     * @return
     */
    Result<PhoneUsVo> selectByPhoneUs(String phone);

    /**
     * 批量查询号码信息
     * @param phoneNumbers
     * @return
     */
    Result<List<PhoneUsVo>> selectByPhoneNumbers(List<String> phoneNumbers);
}
