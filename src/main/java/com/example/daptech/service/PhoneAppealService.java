package com.example.daptech.service;

import com.example.daptech.entity.PhoneAppeal;
import com.example.daptech.response.Result;

import java.util.List;

public interface PhoneAppealService {
    /**
     * Submit an appeal for a phone number
     * @param phoneNumber
     * @param userId
     * @return
     */
    public Result submitAppeal(String phoneNumber,Long userId);

    /**
     * Review an appeal for a phone number
     */
    public void autoReviewAppeals();

    /**
     * Get all appeals for a user
     * @param userId
     * @return
     */
    Result<List<PhoneAppeal>> getAppeal(Long userId);


}
