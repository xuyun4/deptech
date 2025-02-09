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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneUsServiceImpl implements PhoneUsService {

    private final PhoneUsMapper phoneUsMapper;
    private final PhoneMarkMapper phoneMarkMapper;
    private final PendingPhoneMapper pendingPhoneMapper;

    /**
     * 根据手机号查询手机信息
     * @param phone
     * @return
     */
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

    @Override
    public Result<List<PhoneUsVo>> selectByPhoneNumbers(List<String> phoneNumbers) {
        // 获取数据库存在的号码信息
        List<PhoneUs> phoneUss = phoneUsMapper.selectByPhoneNumbers(phoneNumbers);

        // 用于快速查找数据库中存在的电话号码
        Set<String> existingPhones = phoneUss.stream()
                .map(PhoneUs::getPhone)
                .collect(Collectors.toSet());

        // 获取最大号码值用于计算风险值
        Integer maxNumber = phoneUsMapper.getMaxNumber();

        // 存储最终结果
        List<PhoneUsVo> phoneUsVos = new ArrayList<>();

        for (String phoneNumber : phoneNumbers) {
            PhoneUsVo phoneUsVo = new PhoneUsVo();

            // 设置电话号码
            phoneUsVo.setPhone(phoneNumber);

            // 查找是否存在该电话号码的信息
            Optional<PhoneUs> optionalPhoneUs = phoneUss.stream()
                    .filter(phoneUs -> phoneUs.getPhone().equals(phoneNumber))
                    .findFirst();

            if (optionalPhoneUs.isPresent()) {
                // 如果存在，复制属性并计算风险值
                PhoneUs phoneUs = optionalPhoneUs.get();
                BeanUtils.copyProperties(phoneUs, phoneUsVo);
                double riskValue = calculateRiskValue(phoneUs.getNumber(), maxNumber);
                phoneUsVo.setValue(riskValue);
            } else {
                // 如果不存在，保持其他属性为null
                phoneUsVo.setValue(null); // 确保风险值为null
                // 假设PhoneCnVo有其他需要手动设置为null的属性，请在这里添加相应的设置
            }

            phoneUsVos.add(phoneUsVo);
        }

        return phoneUsVos.isEmpty() ? Result.error("手机号不存在") : Result.success(phoneUsVos);
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
    /**
     * 使用对数平滑公式计算风险值
     * @param number
     * @param maxNumber
     * @return
     */
    public  double calculateRiskValue(int number, int maxNumber) {
        if (maxNumber == 0) {
            return 0;
        }
        // 使用对数平滑公式计算风险值
        return (Math.log(number + 1) / Math.log(maxNumber + 1)) * 100;
    }

}
