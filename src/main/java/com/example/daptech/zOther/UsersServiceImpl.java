/*
package com.example.daptech.service.impl;

import com.worldsverre.entity.phonenumber.PendingPhone;
import com.worldsverre.mapper.PhoneCNMapper;
import com.worldsverre.mapper.PhoneUSAMapper;
import com.worldsverre.mapper.UsersMapper;
import com.worldsverre.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private PhoneCNMapper phoneCNMapper;
    @Autowired
    private PhoneUSAMapper usaMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public void updateByUsers(String phone) {
        String typeCN = "多个用户标记";
        String typeUSA = "The number is marked as spam by multiple users";
        PendingPhone pendingPhone = new PendingPhone();

        if(usersMapper.getByPhone(phone) == null){
            pendingPhone.setType(typeCN);
            pendingPhone.setTimes(1);
            usersMapper.add(pendingPhone);
        }else{
            pendingPhone = usersMapper.getByPhone(phone);
            int t = pendingPhone.getTimes();
            t+=1;
            pendingPhone.setTimes(t);
            usersMapper.update(pendingPhone);
        }
    }

    @Override
    public PendingPhone getByPhone(String phone) {
        return usersMapper.getByPhone(phone);
    }


}
*/
