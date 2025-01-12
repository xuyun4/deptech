package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneAppeal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PhoneAppealMapper {

    /**
     * 插入申诉信息
     * @param appeal
     */
    @Insert("INSERT INTO phone_appeal (phone, create_time, status,user_id) VALUES (#{phone}, #{createTime}, #{status}, #{userId})")
    void insertAppeal(PhoneAppeal appeal);

    /**
     * 查询待处理的申诉信息
     * @return
     */
    @Select("SELECT * FROM phone_appeal WHERE status = '0'")
    List<PhoneAppeal> findPendingAppeals();

    /**
     * 查询用户的所有申诉信息
     * @param userId
     * @return
     */
    @Select("SELECT * FROM phone_appeal WHERE user_id = #{userId}")
    List<PhoneAppeal> findAppeal(Long userId);

    /**
     * 查询用户的最新一条申诉信息
     * @param userId
     * @return
     */
    @Select("SELECT * FROM phone_appeal WHERE user_id = #{userId} ORDER BY create_time DESC LIMIT 1")
    PhoneAppeal findLastAppeal(Long userId);

    /**
     * 更新申诉信息状态
     * @param appeal
     */
    @Update("UPDATE phone_appeal SET status = #{status} WHERE id = #{id}")
    void updateAppealStatus(PhoneAppeal appeal);



}
