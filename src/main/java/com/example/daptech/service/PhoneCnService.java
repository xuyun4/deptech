package com.example.daptech.service;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.vo.PhoneCnVo;
import com.example.daptech.response.Result;

import java.util.List;

public interface PhoneCnService  {

    /**
     * 查询手机信息
     * @param phone
     * @return
     */
    Result<PhoneCnVo> selectByPhoneCn(String phone);


    /**
     * 批量查询号码信息
     * @param phoneNumbers
     * @return
     */
    Result<List<PhoneCnVo>> selectByPhoneNumbers(List<String> phoneNumbers);
}
