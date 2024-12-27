package com.example.daptech.service;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.response.Result;

import java.util.List;

public interface DatabaseService{

    Result<List<PhoneCn>> getDatabase();
}
