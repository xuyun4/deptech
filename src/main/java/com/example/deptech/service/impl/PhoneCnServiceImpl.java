package com.example.deptech.service.impl;

import com.example.deptech.mapper.PhoneCnMapper;
import com.example.deptech.service.PhoneCnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneCnServiceImpl implements PhoneCnService {
    private final PhoneCnMapper phoneCnMapper;
    @Override
    public String a(int id) {
        return phoneCnMapper.a(id);
    }
}
