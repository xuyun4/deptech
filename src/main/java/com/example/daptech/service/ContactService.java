package com.example.daptech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.daptech.entity.Contact;
import com.example.daptech.response.Result;
import com.example.daptech.entity.dto.ContactDto;

import java.util.List;

public interface ContactService extends IService<Contact> {

    Result updateContact(String token, List<ContactDto> contactDtoList);

    Result getContact(String token);
}
