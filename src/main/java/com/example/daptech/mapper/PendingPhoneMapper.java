package com.example.daptech.mapper;

import com.example.daptech.entity.PendingPhone;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PendingPhoneMapper {


    /**
     * 插入待确定手机号
     * @param phone 待验证手机号
     * @param type 验证类型
     * @param times 验证次数
     */
    @Insert("INSERT INTO pending_phone (phone,type,times) VALUES (#{phone},#{type},#{times})")
    void insertPendingPhone(String phone,String type,Integer times);

    /**
     * 更新待确定手机号的验证次数
     * @param phone 待验证手机号
     * @param type 验证类型
     * @param times 验证次数
     */
    @Update("UPDATE pending_phone SET times = #{times} WHERE phone = #{phone} AND type = #{type}")
    void updatePendingPhone(String phone,String type,Integer times);

    /**
     * 查询待确定手机号
     * @param phone 待验证手机号
     * @return 待验证手机号列表
     */
    @Select("SELECT * FROM pending_phone WHERE phone = #{phone}")
    List<PendingPhone> selectByPhone(String phone);

    /**
     * 查询待确定手机号
     * @param phone 待验证手机号
     * @param type 验证类型
     * @return 待验证手机号
     */
    @Select("SELECT * FROM pending_phone WHERE phone = #{phone} AND type = #{type}")
    PendingPhone selectByPhoneAndType(String phone,String type);

    /**
     * 删除待确定手机号
     * @param phoneNumber 待验证手机号
     * @param type 验证类型
     */
    @Delete("DELETE FROM pending_phone WHERE phone = #{phoneNumber} AND type = #{type}")
    void deletePendingPhone(String phoneNumber, String type);
}
