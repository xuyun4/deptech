package com.example.deptech.mapper;

import com.example.deptech.entity.PendingPhone;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

}
