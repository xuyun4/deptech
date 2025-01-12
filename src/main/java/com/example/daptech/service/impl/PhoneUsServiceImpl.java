package com.example.daptech.service.impl;

import com.example.daptech.entity.PendingPhone;
import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import com.example.daptech.entity.vo.PhoneCnVo;
import com.example.daptech.entity.vo.PhoneUsVo;
import com.example.daptech.mapper.PendingPhoneMapper;
import com.example.daptech.mapper.PhoneCnMapper;
import com.example.daptech.mapper.PhoneMarkMapper;
import com.example.daptech.mapper.PhoneUsMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneCnService;
import com.example.daptech.service.PhoneUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneUsServiceImpl implements PhoneUsService {

    private final PhoneUsMapper phoneUsMapper;
    private final PhoneMarkMapper phoneMarkMapper;
    private final PendingPhoneMapper pendingPhoneMapper;

    @Override
    public Result<PhoneUsVo> selectByPhoneUs(String phone) {

        PhoneUs phoneUs = phoneUsMapper.selectByPhoneUs(phone);
        if(phoneUs == null){
            return Result.error("手机号不存在");
        }

        PhoneUsVo phoneUsVo = new PhoneUsVo();
        BeanUtils.copyProperties(phoneUs,phoneUsVo);

        Integer maxNumber = phoneUsMapper.getMaxNumber();
        double riskValue = calculateRiskValue(phoneUs.getNumber(), maxNumber);

        phoneUsVo.setValue(riskValue);
        return Result.success(phoneUsVo);

    }

/*    private Integer getValue(String phone){
        Integer count = phoneMarkMapper.getCountByPhone(phone);
        Integer value = 0;

        //若手机号在phone_cn表中
        PhoneUs phoneUs = phoneUsMapper.selectByPhoneUs(phone);
        if(phoneUs!= null){
            if(count==0){ //表中原始数据缺少标记信息,直接使用number字段替代标记次数
                value = phoneUs.getNumber();
            }else{
                value = count;
            }

            return value>100?100:value;

        }

        //若手机号在pending_phone表中
        List<PendingPhone> pendingPhone = pendingPhoneMapper.selectByPhone(phone);
        if (!pendingPhone.isEmpty()){
            return count;
        }

        //若手机号不在任何表中,则风险值为0
        return value;

    }*/
    public  double calculateRiskValue(int number, int maxNumber) {
        if (maxNumber == 0) {
            return 0;
        }
        // 使用对数平滑公式计算风险值
        return (Math.log(number + 1) / Math.log(maxNumber + 1)) * 100;
    }

}
