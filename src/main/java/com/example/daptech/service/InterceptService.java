package com.example.daptech.service;

import com.example.daptech.response.Result;

public interface InterceptService {

    Result<String> judge(String phone);
}
