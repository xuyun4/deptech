package com.example.daptech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.daptech.entity.Call;
import com.example.daptech.entity.vo.LocationVo;
import com.example.daptech.entity.vo.TypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CallMapper extends BaseMapper<Call> {
    Integer getNumberByMap(Map map);

    List<LocationVo> getLocationByMap(Map map);

    List<TypeVo> getTypeByMap(Map map);
}
