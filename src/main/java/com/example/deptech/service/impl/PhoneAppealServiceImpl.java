package com.example.deptech.service.impl;

import com.example.deptech.entity.PhoneAppeal;
import com.example.deptech.mapper.PhoneAppealMapper;
import com.example.deptech.mapper.PhoneMarkMapper;
import com.example.deptech.service.PhoneAppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneAppealServiceImpl implements PhoneAppealService {

    private final PhoneAppealMapper phoneAppealMapper;
    private final PhoneMarkMapper phoneMarkMapper;

    // 获取当前时间的 Instant 对象
    Instant now = Instant.now();

    // 获取 Unix 时间戳（秒）
    long unixTimestampSeconds = now.getEpochSecond();
    public void submitAppeal(String phoneNumber) {  //向phone_appeal表中插入一条记录
        PhoneAppeal appeal = new PhoneAppeal();
        appeal.setPhone(phoneNumber);
        appeal.setCreateTime(unixTimestampSeconds);
        appeal.setStatus(0);
        phoneAppealMapper.insertAppeal(appeal);
    }

/*    public void autoReviewAppeals() {  //自动审核号码申诉
        List<PhoneAppeal> pendingAppeals = phoneAppealMapper.findPendingAppeals();
        for (PhoneAppeal appeal : pendingAppeals) {
            if (isAppealApproved(appeal)) {
                approveAppeal(appeal);
            } else {
                rejectAppeal(appeal);
            }
        }
    }*/

    private boolean isAppealApproved(PhoneAppeal appeal) {
        long sevenDaysAgo = now.getEpochSecond() - 7 * 24 * 60 * 60 ;
        long markCount = phoneMarkMapper.countByPhoneNumberAndCreatedAtAfter(appeal.getPhone(), sevenDaysAgo);
        return markCount <= 5;
    }

/*    private void approveAppeal(PhoneAppeal appeal) {
        appeal.setStatus(1);
        appeal.setReviewedAt(new Date());
        phoneAppealMapper.updateAppealStatus(appeal);
        // 取消号码标记逻辑
        phoneMarkMapper.deactivateMark(appeal.getPhone());
    }

    private void rejectAppeal(PhoneAppeal appeal) {
        appeal.setStatus(2);
        appeal.setReviewedAt(new Date());
        phoneAppealMapper.updateAppealStatus(appeal);
    }*/

}
