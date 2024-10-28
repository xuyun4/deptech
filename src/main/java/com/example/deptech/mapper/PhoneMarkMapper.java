package com.example.deptech.mapper;

import com.example.deptech.entity.PhoneMark;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhoneMarkMapper {

    @Insert("INSERT INTO phone_mark (phone, type, mark, create_time) VALUES (#{phone}, #{type}, #{mark}, #{createTime})")
    public void insertMark(String phone, String type, String mark,long createTime);

    @Select("SELECT * FROM phone_mark WHERE phone = #{phone} ")
    public List<PhoneMark> selectMark(String phone);

    @Select("SELECT COUNT(*) FROM phone_mark WHERE phone = #{phoneNumber} AND create_time > #{createTime}")
    long countByPhoneNumberAndCreatedAtAfter( String phoneNumber, long createTime);
}
