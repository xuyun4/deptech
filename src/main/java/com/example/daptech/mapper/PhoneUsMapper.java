package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhoneUsMapper {

    /**
     * 插入手机信息
     * @param phone
     * @param type
     * @param number
     * @param status
     * @param createTime
     * @param updateTime
     * @param location
     */
    @Insert("INSERT INTO phone_us(phone, type, number, status, create_time, update_time, location) VALUES (#{phone}, #{type}, #{number}, #{status}, #{createTime}, #{updateTime}, #{location})")
    void insertPhoneUs(String phone, String type,Integer number,Integer status,long createTime,long updateTime,String location);

    /**
     * 删除手机信息
     * @param phone
     */
    @Delete("DELETE FROM phone_us WHERE phone = #{phone}")
    void deletePhoneUs(String phone);

    /**
     * 更新手机信息
     * @param phone
     * @param type
     * @param number
     * @param updateTime
     */
    @Update("UPDATE phone_us SET type = #{type}, number = #{number} AND update_time = #{updateTime} WHERE phone = #{phone}")
    void updatePhoneType(String phone, String type,Integer number,long updateTime);

    /**
     * 更新手机信息
     * @param phone
     * @param info
     */
    @Update("UPDATE phone_us SET information = #{info} WHERE phone = #{phone}")
    void updatePhoneInfo(String phone, String info);

    /**
     * 查询手机信息
     * @param phone
     * @return
     */
    @Select("SELECT * FROM phone_us WHERE phone = #{phone}")
    PhoneUs selectByPhoneUs(String phone);

    /**
     * 查询次数最大值
     * @return
     */
    @Select("SELECT MAX(number) FROM phone_us")
    Integer getMaxNumber();

    /**
     * 批量查询手机信息
     * @param phoneNumbers 手机号列表
     * @return 手机信息列表
     */
    List<PhoneUs> selectByPhoneNumbers(List<String> phoneNumbers);
}
