package com.example.deptech.service.impl;

import com.example.deptech.entity.PendingPhone;
import com.example.deptech.mapper.PendingPhoneMapper;
import com.example.deptech.service.PendingPhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PendingPhoneServiceImpl implements PendingPhoneService {

    private final PendingPhoneMapper pendingPhoneMapper;
    @Override
    public PendingPhone selectByPhone(String phone) {

        return pendingPhoneMapper.selectByPhone(phone);
    }

    @Override
    public PendingPhone selectByPhoneAndType(String phone, String type) {

        return pendingPhoneMapper.selectByPhoneAndType(phone, type);
    }

    @Override
    public void insertPendingPhone(String phone, String type, Integer times) {

        pendingPhoneMapper.insertPendingPhone(phone, type, times);
    }

    @Override
    public void updatePendingPhone(String phone, String type, Integer times) {
        pendingPhoneMapper.updatePendingPhone(phone, type, times);
    }
}
