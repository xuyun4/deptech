package com.example.daptech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.daptech.entity.Call;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CallMapper extends BaseMapper<Call> {
    Integer getNumberByMap(Map map);

    void deleteCall(Long userId);

    void addCall(List<Call> callList);

    List<Call> selectCall(Long userId);
}
