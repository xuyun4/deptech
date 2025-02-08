package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneCn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhoneCnMapper {

    /**
     * 插入手机信息
     * @param phone 手机号
     * @param type 手机类型
     * @param number 手机号码
     * @param status 手机状态
     * @param createTime 创建时间
     * @param updateTime 更新时间
     * @param location 手机位置
     */
    @Insert("INSERT INTO phone_cn(phone, type, number, status, create_time, update_time, location) VALUES (#{phone}, #{type}, #{number}, #{status}, #{createTime}, #{updateTime}, #{location})")
    void insertPhoneCn(String phone, String type,Integer number,Integer status,long createTime,long updateTime,String location);

    /**
     * 删除手机信息
     * @param phone 手机号
     */
    @Delete("DELETE FROM phone_cn WHERE phone = #{phone}")
    void deletePhoneCn(String phone);

    /**
     * 更新手机信息
     * @param phone 手机号
     * @param type 手机类型
     * @param number 手机号码
     * @param updateTime 更新时间
     */
    @Update("UPDATE phone_cn SET type = #{type}, number = #{number} AND update_time = #{updateTime} WHERE phone = #{phone}")
    void updatePhoneType(String phone, String type,Integer number,long updateTime);

    /**
     * 更新手机信息
     * @param phone 手机号
     * @param info 手机信息
     */
    @Update("UPDATE phone_cn SET information = #{info} WHERE phone = #{phone}")
    void updatePhoneInfo(String phone, String info);

    /**
     * 查询手机信息
     * @param phone 手机号
     * @return 手机信息
     */
    @Select("SELECT * FROM phone_cn WHERE phone = #{phone}")
    PhoneCn selectByPhoneCn(String phone);

    /**
     * 批量查询手机信息
     * @param phoneNumbers 手机号列表
     * @return 手机信息列表
     */
    List<PhoneCn> selectByPhoneNumbers(List<String> phoneNumbers);

    /**
     * 查询最大的手机号码
     * @return 最大的手机号码
     */
    @Select("SELECT MAX(number) FROM phone_cn")
    Integer getMaxNumber();
}
