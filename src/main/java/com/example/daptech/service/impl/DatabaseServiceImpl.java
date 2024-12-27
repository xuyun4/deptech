package com.example.daptech.service.impl;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.mapper.DatabaseMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DatabaseServiceImpl implements DatabaseService {
    private final DatabaseMapper databaseMapper;

    @Override
    public Result<List<PhoneCn>> getDatabase() {
        List<PhoneCn> phoneCnList=databaseMapper.getDatabase();
        return Result.success(phoneCnList);
    }
}
