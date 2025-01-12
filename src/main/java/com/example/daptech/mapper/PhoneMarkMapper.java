package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneMark;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhoneMarkMapper {

    /**
     * 插入用户标记
     * @param phone
     * @param type
     * @param mark
     * @param createTime
     * @param userId
     */
    @Insert("INSERT INTO phone_mark (phone, type, mark, create_time,user_id) VALUES (#{phone}, #{type}, #{mark}, #{createTime},#{userId})")
    void insertMark(String phone, String type, String mark, long createTime,Long userId);

    /**
     * 删除用户标记
     * @param phone
     * @param creatTime
     */
    @Delete("DELETE FROM phone_mark WHERE phone = #{phone} AND create_time < #{createTime}")
    void deleteMark(String phone, long creatTime);

    /**
     * 查询目标手机号的所有被标记信息
     * @param phone
     * @return
     */
    @Select("SELECT * FROM phone_mark WHERE phone = #{phone} ")
    List<PhoneMark> selectMark(String phone);

    /**
     * 查询目标手机号和类型的所有被标记信息
     * @param phone
     * @param type
     * @return
     */
    @Select("SELECT * FROM phone_mark WHERE phone = #{phone} AND type = #{type} ")
    List<PhoneMark> selectMarkByPhoneAndType(String phone,String type);

    /**
     * 查询用户最后一条标记信息
     * @param userId
     * @return
     */
    @Select("SELECT * FROM phone_mark WHERE user_id = #{userId} ORDER BY create_time DESC LIMIT 1")
    PhoneMark selectLastByUserId(Long userId);

    /**
     * 查询时间段内被标记的数量
     * @param phoneNumber
     * @param createTime
     * @return
     */

    @Select("SELECT COUNT(*) FROM phone_mark WHERE phone = #{phoneNumber} AND create_time > #{createTime}")
    long countByPhoneNumberAndCreatedAtAfter(String phoneNumber, long createTime);


    /**
     * 查询目标手机号被标记的数量
     * @param phone
     * @return
     */
    @Select("SELECT COUNT(*) FROM phone_mark WHERE phone = #{phone}")
    Integer getCountByPhone(String phone);
}
