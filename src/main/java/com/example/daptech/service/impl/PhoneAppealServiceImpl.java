package com.example.daptech.service.impl;

import com.example.daptech.entity.PhoneAppeal;
import com.example.daptech.mapper.PhoneAppealMapper;
import com.example.daptech.mapper.PhoneMarkMapper;
import com.example.daptech.service.PhoneAppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    public void submitAppeal(String phoneNumber) {  //向phone_appeal表中插入一条记录,提交申诉加入审核队列
        PhoneAppeal appeal = new PhoneAppeal();
        appeal.setPhone(phoneNumber);
        appeal.setCreateTime(unixTimestampSeconds);
        appeal.setStatus(0);
        phoneAppealMapper.insertAppeal(appeal);
    }

    public void autoReviewAppeals() {  //自动审核号码申诉
        List<PhoneAppeal> pendingAppeals = phoneAppealMapper.findPendingAppeals();
        for (PhoneAppeal appeal : pendingAppeals) {
            if (isAppealApproved(appeal)) { //标记不足五次
                if(appeal.getCreateTime() < (unixTimestampSeconds - 7 * 24 * 60 * 60)) {//如果超过七天,则成功,否则等待下一次审核
                    approveAppeal(appeal);
                    phoneMarkMapper.deleteMark(appeal.getPhone(), appeal.getCreateTime());
                }
            } else {
                rejectAppeal(appeal);
            }
        }
    }

    private boolean isAppealApproved(PhoneAppeal appeal) { //验证七天内是否有五次以上标记
        long sevenDaysAgo = now.getEpochSecond() - 7 * 24 * 60 * 60 ;

        long markCount = phoneMarkMapper.countByPhoneNumberAndCreatedAtAfter(appeal.getPhone(), sevenDaysAgo);
        return markCount <= 5;
    }

    private void approveAppeal(PhoneAppeal appeal) {
        appeal.setStatus(1); // 审核通过
        phoneAppealMapper.updateAppealStatus(appeal);

    }

    private void rejectAppeal(PhoneAppeal appeal) {
        appeal.setStatus(2); // 审核不通过
        phoneAppealMapper.updateAppealStatus(appeal);

    }

}
