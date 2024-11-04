package com.example.daptech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.daptech.entity.Contact;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper extends BaseMapper<Contact> {
}
