package com.example.daptech.service.impl;

import com.example.daptech.entity.PendingPhone;
import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneMark;
import com.example.daptech.mapper.PendingPhoneMapper;
import com.example.daptech.mapper.PhoneCnMapper;
import com.example.daptech.mapper.PhoneMarkMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.PhoneMarkService;
import com.example.daptech.util.AiUtil;
import com.example.daptech.util.VirtualAndLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneMarkServiceImpl implements PhoneMarkService {
    private final PhoneMarkMapper phoneMarkMapper;
    private final PhoneCnMapper phoneCnMapper;
    private final PendingPhoneMapper pendingPhoneMapper;

    public boolean canMarkPhoneNumber(Long userId) { //判断是否可以标记手机号,可以为true,否则为false
        PhoneMark lastMark = phoneMarkMapper.selectLastByUserId(userId);
        if (lastMark==null) {
            return  true;
        }else{
            Instant now = Instant.now();

            // 获取 Unix 时间戳（秒）
            long unixTimestampSeconds = now.getEpochSecond();

            long lastTime = lastMark.getCreateTime();

            return (unixTimestampSeconds - lastTime) >= 60; //间隔超过一分钟才可以标记
        }

    }

    @Override //增加标记数据
    public Result insertMark(String phone, String type, String mark, Long userId) {
        if(canMarkPhoneNumber(userId)) {

            Instant now = Instant.now();

            // 获取 Unix 时间戳（秒）
            long unixTimestampSeconds = now.getEpochSecond();
            phoneMarkMapper.insertMark(phone, type, mark, unixTimestampSeconds, userId);
            updatePhoneMark(phone); //更新标记信息
            updatePhoneInfo(phone); //更新该手机号的被标记详细信息
            return Result.success("标记成功");
        }
        return Result.error("标记操作过于频繁,请一分钟后再试");
    }

    /*用户新增标记,手机号信息可能发生变化,可能类型发生变化,
     需要再次从phone_mark中获取新类型的标记,并整合更新到新类型对应的information */
    private void updatePhoneInfo(String phone) {
        PhoneCn phoneCn = phoneCnMapper.selectByPhoneCn(phone);
        String type = phoneCn.getType();
        List<PhoneMark> phoneMarks = phoneMarkMapper.selectMarkByPhoneAndType(phone, type);
        List<String> marks = new ArrayList<>();
        for (PhoneMark phoneMark : phoneMarks) {
            marks.add(phoneMark.getMark());
        }
        String info = AiUtil.summarize(marks);
        phoneCnMapper.updatePhoneInfo(phone, info);
    }

    @Override //插入一条新数据之后,遍历寻找标记次数最多的类型,并将其更新为该手机号的类型
    public void updatePhoneMark(String phoneNumber) {

        List<PhoneMark> phoneMarks = phoneMarkMapper.selectMark(phoneNumber);

        String[] types = {"骚扰电话", "中介骚扰", "诈骗电话", "推销电话", "疑似推销", "违法电话"};
        int[] counts = new int[6];
        for (int i = 0; i < types.length; i++) {
            counts[i] = 0;
        }

        for (PhoneMark phoneMark : phoneMarks) {

            if (phoneMark.getType().equals(types[0])) {
                counts[0]++;
            }else if (phoneMark.getType().equals(types[1])) {
                counts[1]++;
            } else if (phoneMark.getType().equals(types[2])) {
                counts[2]++;
            } else if (phoneMark.getType().equals(types[3])) {
                counts[3]++;
            }else if (phoneMark.getType().equals(types[4])) {
                counts[4]++;
            }else if (phoneMark.getType().equals(types[5])) {
                counts[5]++;
            }

        }

        int max = counts[0];
        int index = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
                index = i;
            }
        }

        PhoneCn phoneCn = phoneCnMapper.selectByPhoneCn(phoneNumber);

        // 获取当前时间的 Instant 对象
        Instant now = Instant.now();

        // 获取 Unix 时间戳（秒）
        long unixTimestampSeconds = now.getEpochSecond();

        if(phoneCn==null) { //如果phone_cn表中不存在该手机号,则考虑暂存到pending_phone表

            PendingPhone pendingPhone = pendingPhoneMapper.selectByPhoneAndType(phoneNumber, types[index]);

            if(pendingPhone==null){ //如果pending_phone表中不存在该手机号与该类型的记录,则插入该手机号类型数据
                pendingPhoneMapper.insertPendingPhone(phoneNumber, types[index],1);

            }else{ //如果pending_phone表中存在该手机号及类型标记,则更新该手机号的标记次数+1
                pendingPhoneMapper.updatePendingPhone(phoneNumber, types[index],pendingPhone.getTimes()+1);
                if(pendingPhone.getTimes()+1>=10){ //如果被标记次数超过10次,则加入phone_cn表,并删除pending_phone表中该记录
                    String location = VirtualAndLocation.getLocation(phoneNumber); //获取归属地以及是否为虚拟号
                    Integer virtual = VirtualAndLocation.isVirtual(phoneNumber)? 1 : 0;
                    phoneCnMapper.insertPhoneCn(phoneNumber, types[index], pendingPhone.getTimes()+1, virtual,unixTimestampSeconds,unixTimestampSeconds,location);
                    pendingPhoneMapper.deletePendingPhone(phoneNumber, types[index]);
                }
            }

        }else{//phone_cn表中存在该手机号,则更新该手机号的类型
            phoneCnMapper.updatePhoneType(phoneNumber,types[index],phoneCn.getNumber()+1,unixTimestampSeconds);
        }
    }

}
