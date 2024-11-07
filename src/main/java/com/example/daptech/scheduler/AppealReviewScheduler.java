package com.example.daptech.scheduler;

import com.example.daptech.service.PhoneAppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppealReviewScheduler {
    @Autowired
    private PhoneAppealService phoneAppealService;

    @Scheduled(fixedRate = 60000*60*24) // 每天执行一次
    public void reviewAppeals() {
        phoneAppealService.autoReviewAppeals();
    }
}
