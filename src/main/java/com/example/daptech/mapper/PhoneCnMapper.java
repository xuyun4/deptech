package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneCn;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PhoneCnMapper {

    @Insert("INSERT INTO phone_cn(phone, type, number, status, create_time, update_time, location) VALUES (#{phone}, #{type}, #{number}, #{status}, #{createTime}, #{updateTime}, #{location})")
    void insertPhoneCn(String phone, String type,Integer number,Integer status,long createTime,long updateTime,String location);

    @Delete("DELETE FROM phone_cn WHERE phone = #{phone}")
    void deletePhoneCn(String phone);
    @Update("UPDATE phone_cn SET type = #{type}, number = #{number} AND update_time = #{updateTime} WHERE phone = #{phone}")
    void updatePhoneType(String phone, String type,Integer number,long updateTime);
    @Update("UPDATE phone_cn SET information = #{info} WHERE phone = #{phone}")
    void updatePhoneInfo(String phone, String info);
    @Select("SELECT * FROM phone_cn WHERE phone = #{phone}")
    PhoneCn selectByPhoneCn(String phone);

}
