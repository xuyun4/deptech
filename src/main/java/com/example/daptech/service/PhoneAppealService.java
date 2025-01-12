package com.example.daptech.service;

import com.example.daptech.entity.PhoneAppeal;
import com.example.daptech.response.Result;

import java.util.List;

public interface PhoneAppealService {
    /**
     * 提交手机申诉
     * @param phoneNumber
     * @param userId
     * @return
     */
    public Result submitAppeal(String phoneNumber,Long userId);

    /**
     * 自动审核申诉
     */
    public void autoReviewAppeals();

    /**
     * 获取用户申诉列表
     * @param userId
     * @return
     */
    Result<List<PhoneAppeal>> getAppeal(Long userId);


}
