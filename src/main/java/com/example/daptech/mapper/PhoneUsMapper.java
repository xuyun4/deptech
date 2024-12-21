package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PhoneUsMapper {

    @Insert("INSERT INTO phone_us(phone, type, number, status, create_time, update_time, location) VALUES (#{phone}, #{type}, #{number}, #{status}, #{createTime}, #{updateTime}, #{location})")
    void insertPhoneUs(String phone, String type,Integer number,Integer status,long createTime,long updateTime,String location);

    @Delete("DELETE FROM phone_us WHERE phone = #{phone}")
    void deletePhoneUs(String phone);
    @Update("UPDATE phone_us SET type = #{type}, number = #{number} AND update_time = #{updateTime} WHERE phone = #{phone}")
    void updatePhoneType(String phone, String type,Integer number,long updateTime);
    @Update("UPDATE phone_us SET information = #{info} WHERE phone = #{phone}")
    void updatePhoneInfo(String phone, String info);
    @Select("SELECT * FROM phone_us WHERE phone = #{phone}")
    PhoneUs selectByPhoneUs(String phone);

}
