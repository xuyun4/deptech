package com.example.deptech.service;

import com.example.deptech.entity.PendingPhone;

public interface PendingPhoneService {
    PendingPhone selectByPhone(String phone);
    PendingPhone selectByPhoneAndType(String phone,String type);
    void insertPendingPhone(String phone,String type,Integer times);

    void updatePendingPhone(String phone,String type,Integer times);
}
