package com.example.daptech.mapper;

import com.example.daptech.entity.PendingPhone;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PendingPhoneMapper {


    @Insert("INSERT INTO pending_phone (phone,type,times) VALUES (#{phone},#{type},#{times})")
    void insertPendingPhone(String phone,String type,Integer times);

    @Update("UPDATE pending_phone SET times = #{times} WHERE phone = #{phone} AND type = #{type}")
    void updatePendingPhone(String phone,String type,Integer times);

    @Select("SELECT * FROM pending_phone WHERE phone = #{phone}")
    PendingPhone selectByPhone(String phone);

    @Select("SELECT * FROM pending_phone WHERE phone = #{phone} AND type = #{type}")
    PendingPhone selectByPhoneAndType(String phone,String type);

    @Delete("DELETE FROM pending_phone WHERE phone = #{phoneNumber} AND type = #{type}")
    void deletePendingPhone(String phoneNumber, String type);
}
