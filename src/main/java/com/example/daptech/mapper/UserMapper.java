package com.example.daptech.mapper;

import com.example.daptech.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* @author 24333
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-10-24 10:59:28
* @Entity com.example.daptech.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //修改昵称
    @Update("UPDATE user SET nickname = #{nickname} WHERE id = #{userId}")
    int updateNickname(Long userId, String nickname);

    //上传头像
    @Update("UPDATE user SET avatar_url = #{avatarUrl} WHERE id = #{userId}")
    int updateAvatarUrl(Long userId, String avatarUrl);

}




