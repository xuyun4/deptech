package com.example.daptech.mapper;

import com.example.daptech.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24333
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-10-24 10:59:28
* @Entity com.example.deptech.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




