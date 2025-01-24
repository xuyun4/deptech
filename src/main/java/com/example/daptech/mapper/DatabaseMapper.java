package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DatabaseMapper {

    @Select("select * from phone_cn")
    List<PhoneCn> getCnDatabase();

    @Select("select * from phone_us")
    List<PhoneUs> getUsDatabase();

}
