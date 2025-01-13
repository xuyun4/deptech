package com.example.daptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daptech.entity.Contact;
import com.example.daptech.entity.dto.ContactDto;
import com.example.daptech.entity.vo.ContactVo;
import com.example.daptech.mapper.ContactMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.ContactService;
import com.example.daptech.util.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public Result updateContact(String token, List<ContactDto> contactDtoList) {
        //逻辑同黑名单
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Contact> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        contactMapper.delete(queryWrapper);
        List<Contact> contactList = new ArrayList<>();
        for (ContactDto contactDto : contactDtoList) {
            Contact contact = new Contact();
            BeanUtils.copyProperties(contactDto, contact);
            contact.setUserId(userId);
            contactList.add(contact);
        }
        saveBatch(contactList);
        return Result.success();
    }

    @Override
    public Result<List<ContactVo>> getContact(String token) {
        //逻辑同黑名单
        Long userId= JwtHelper.getIdFromToken(token);
        QueryWrapper<Contact> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Contact> contactList = contactMapper.selectList(queryWrapper);
        List<ContactVo> contactVoList = new ArrayList<>();
        for (Contact contact : contactList) {
            ContactVo contactVo = new ContactVo();
            BeanUtils.copyProperties(contact, contactVo);
            contactVoList.add(contactVo);
        }
        return Result.success(contactVoList);
    }
}
