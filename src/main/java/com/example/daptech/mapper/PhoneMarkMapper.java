package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneMark;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhoneMarkMapper {

    @Insert("INSERT INTO phone_mark (phone, type, mark, create_time) VALUES (#{phone}, #{type}, #{mark}, #{createTime})")
    void insertMark(String phone, String type, String mark, long createTime);

    @Delete("DELETE FROM phone_mark WHERE phone = #{phone} AND create_time < #{createTime}")
    void deleteMark(String phone, long creatTime);
    @Select("SELECT * FROM phone_mark WHERE phone = #{phone} ")
    List<PhoneMark> selectMark(String phone);

    @Select("SELECT COUNT(*) FROM phone_mark WHERE phone = #{phoneNumber} AND create_time > #{createTime}")
    long countByPhoneNumberAndCreatedAtAfter(String phoneNumber, long createTime);


}
