package com.example.daptech.service;

import com.example.daptech.response.Result;

public interface InterceptService {

    Result<String> judgeCn(String phone);

    Result<String> judgeUs(String phone);

}
