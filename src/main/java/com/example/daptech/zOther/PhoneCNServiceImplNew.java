/*
package com.example.daptech.service.impl;

import com.worldsverre.entity.MSG;
import com.worldsverre.entity.phonenumber.BlockedNumbersCN;
import com.worldsverre.mapper.PhoneCNMapper;
import com.worldsverre.service.PhoneCNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneCNServiceImplNew implements PhoneCNService {

    @Autowired
    private PhoneCNMapper phoneCNMapper;


    @Override
    public MSG api(String phone) {
        MSG msg = new MSG();
        List<BlockedNumbersCN> list = phoneCNMapper.api(phone);
        if (list != null && !list.isEmpty()) {
            msg.setLabel((short)1);
            BlockedNumbersCN temp = list.get(0);
            msg.setType(temp.getType());
        } else {
            msg.setLabel((short)0);
        }
        return msg;
    }
}
*/
