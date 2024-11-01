package com.example.daptech.service;

import com.example.daptech.response.Result;

public interface PhoneAppealService {
    public Result submitAppeal(String phoneNumber,Long userId);
    public void autoReviewAppeals();

    Result getAppeal(Long userId);


}
