package com.example.daptech.service.impl;

import com.example.daptech.entity.PendingPhone;
import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.vo.PhoneCnVo;
import com.example.daptech.mapper.PendingPhoneMapper;
import com.example.daptech.mapper.PhoneCnMapper;
import com.example.daptech.mapper.PhoneMarkMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneCnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneCnServiceImpl implements PhoneCnService {

    private final PhoneCnMapper phoneCnMapper;
    private final PhoneMarkMapper phoneMarkMapper;
    private final PendingPhoneMapper pendingPhoneMapper;

    @Override
    public Result<PhoneCnVo> selectByPhoneCn(String phone) {

        PhoneCn phoneCn = phoneCnMapper.selectByPhoneCn(phone);
        if(phoneCn == null){
            return Result.error("手机号不存在");
        }

        PhoneCnVo phoneCnVo = new PhoneCnVo();
        BeanUtils.copyProperties(phoneCn,phoneCnVo);


        Integer maxNumber = phoneCnMapper.getMaxNumber();
        double riskValue = calculateRiskValue(phoneCn.getNumber(), maxNumber);

        phoneCnVo.setValue(riskValue);
        return Result.success(phoneCnVo);

    }

/*    private Integer getValue(String phone){
        Integer count = phoneMarkMapper.getCountByPhone(phone); //获取标记次数
        Integer value = 0;

        //若手机号在phone_cn表中
        PhoneCn phoneCn = phoneCnMapper.selectByPhoneCn(phone);
        if(phoneCn!= null){

            if(count==0){ //表中原始数据缺少标记信息,直接使用number字段替代标记次数
                value = phoneCn.getNumber();
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
        return 0;

    }*/

    public  double calculateRiskValue(int number, int maxNumber) {
        if (maxNumber == 0) {
            return 0;
        }
        // 使用对数平滑公式计算风险值
        return (Math.log(number + 1) / Math.log(maxNumber + 1)) * 100;
    }

}
