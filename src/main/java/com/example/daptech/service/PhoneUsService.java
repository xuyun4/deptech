package com.example.daptech.service;

import com.example.daptech.entity.vo.PhoneCnVo;
import com.example.daptech.entity.vo.PhoneUsVo;
import com.example.daptech.response.Result;

public interface PhoneUsService {
    /**
     * 查询手机信息
     * @param phone
     * @return
     */
    Result<PhoneUsVo> selectByPhoneUs(String phone);


}
