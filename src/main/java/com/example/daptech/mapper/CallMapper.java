package com.example.daptech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.daptech.entity.Call;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CallMapper extends BaseMapper<Call> {
    Integer getNumberByMap(Map map);

}
