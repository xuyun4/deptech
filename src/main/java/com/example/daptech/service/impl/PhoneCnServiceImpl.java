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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneCnServiceImpl implements PhoneCnService {

    private final PhoneCnMapper phoneCnMapper;
    private final PhoneMarkMapper phoneMarkMapper;
    private final PendingPhoneMapper pendingPhoneMapper;

    /**
     * 根据手机号查询手机信息
     * @param phone
     * @return
     */
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

    /**
     * 批量查询号码信息
     * @param phoneNumbers
     * @return
     */
    @Override
    public Result<List<PhoneCnVo>> selectByPhoneNumbers(List<String> phoneNumbers) {
        // 获取数据库存在的号码信息
        List<PhoneCn> phoneCns = phoneCnMapper.selectByPhoneNumbers(phoneNumbers);

        // 用于快速查找数据库中存在的电话号码
        Set<String> existingPhones = phoneCns.stream()
                .map(PhoneCn::getPhone)
                .collect(Collectors.toSet());

        // 获取最大号码值用于计算风险值
        Integer maxNumber = phoneCnMapper.getMaxNumber();

        // 存储最终结果
        List<PhoneCnVo> phoneCnVos = new ArrayList<>();

        for (String phoneNumber : phoneNumbers) {
            PhoneCnVo phoneCnVo = new PhoneCnVo();

            // 设置电话号码
            phoneCnVo.setPhone(phoneNumber);

            // 查找是否存在该电话号码的信息
            Optional<PhoneCn> optionalPhoneCn = phoneCns.stream()
                    .filter(phoneCn -> phoneCn.getPhone().equals(phoneNumber))
                    .findFirst();

            if (optionalPhoneCn.isPresent()) {
                // 如果存在，复制属性并计算风险值
                PhoneCn phoneCn = optionalPhoneCn.get();
                BeanUtils.copyProperties(phoneCn, phoneCnVo);
                double riskValue = calculateRiskValue(phoneCn.getNumber(), maxNumber);
                phoneCnVo.setValue(riskValue);
            } else {
                // 如果不存在，保持其他属性为null
                phoneCnVo.setValue(null); // 确保风险值为null
                // 假设PhoneCnVo有其他需要手动设置为null的属性，请在这里添加相应的设置
            }

            phoneCnVos.add(phoneCnVo);
        }

        return phoneCnVos.isEmpty() ? Result.error("手机号不存在") : Result.success(phoneCnVos);
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
