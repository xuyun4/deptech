package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DatabaseMapper {

    @Select("select * from phone_cn")
    List<PhoneCn> getDatabase();
}
