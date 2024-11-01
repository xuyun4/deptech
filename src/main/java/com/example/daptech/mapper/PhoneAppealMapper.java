package com.example.daptech.mapper;

import com.example.daptech.entity.PhoneAppeal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PhoneAppealMapper {

    @Insert("INSERT INTO phone_appeal (phone, create_time, status) VALUES (#{phone}, #{createTime}, #{status})")
    void insertAppeal(PhoneAppeal appeal);

    @Select("SELECT * FROM phone_appeal WHERE status = '0'")
    List<PhoneAppeal> findPendingAppeals();

    @Update("UPDATE phone_appeal SET status = #{status} WHERE id = #{id}")
    void updateAppealStatus(PhoneAppeal appeal);

}
