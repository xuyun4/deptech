package com.example.daptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daptech.entity.Call;
import com.example.daptech.entity.dto.CallDto;
import com.example.daptech.entity.vo.CallVo;
import com.example.daptech.entity.vo.LocationVo;
import com.example.daptech.entity.vo.ReportVo;
import com.example.daptech.entity.vo.TypeVo;
import com.example.daptech.mapper.CallMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.CallService;
import com.example.daptech.util.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CallServiceImpl extends ServiceImpl<CallMapper, Call> implements CallService {

    @Autowired
    private CallMapper callMapper;

    @Override
    public Result updateCall(String token, List<CallDto> callDtoList) {
        Long userId = JwtHelper.getIdFromToken(token);
        QueryWrapper<Call> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        callMapper.delete(queryWrapper);
        List<Call> callList = new ArrayList<>();
        for (CallDto callDto : callDtoList) {
            Call call = new Call();
            BeanUtils.copyProperties(callDto, call);
            call.setUserId(userId);
            callList.add(call);
        }
        saveBatch(callList);
        return Result.success();
    }

    @Override
    public Result<List<CallVo>> getCall(String token) {
        Long userId = JwtHelper.getIdFromToken(token);
        QueryWrapper<Call> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Call> callList = callMapper.selectList(queryWrapper);
        List<CallVo> callVoList = new ArrayList<>();
        for (Call call : callList) {
            CallVo callVo = new CallVo();
            BeanUtils.copyProperties(call, callVo);
            callVoList.add(callVo);
        }
        return Result.success(callVoList);
    }

    @Override
    public Result<ReportVo> getWeekReport(String token) {
        Long userId = JwtHelper.getIdFromToken(token);

        LocalDate end = LocalDate.now();
        LocalDate begin = end.minusDays(6);
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        LocalDateTime beginTime=LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime=LocalDateTime.of(end,LocalTime.MAX);
        Map map=new HashMap();
        map.put("userId",userId);
        map.put("begin",beginTime);
        map.put("end",endTime);
        map.put("noType","普通来电");
        List<LocationVo> locationVoList=callMapper.getLocationByMap(map);
        List<TypeVo> typeVoList=callMapper.getTypeByMap(map);

        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        List<Integer> numberList = new ArrayList<>();
        for (LocalDate date:dateList) {
            beginTime=LocalDateTime.of(date, LocalTime.MIN);
            endTime=LocalDateTime.of(date,LocalTime.MAX);
            map.put("begin",beginTime);
            map.put("end",endTime);
            map.put("noType","普通来电");
            Integer number=callMapper.getNumberByMap(map);
            number=number==null? 0:number;
            numberList.add(number);
        }



        return Result.success
                (ReportVo
                .builder()
                .timeList(StringUtils.join(dateList,","))
                .numberList(StringUtils.join(numberList,","))
                .locationVoList(locationVoList)
                .typeVoList(typeVoList)
                .build()
                );
    }

    @Override
    public Result<ReportVo> getMonthReport(String token) {
        Long userId = JwtHelper.getIdFromToken(token);
        LocalDate today = LocalDate.now();
        LocalDate begin = today.withDayOfMonth(1);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDateTime beginTime=LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime=LocalDateTime.of(today,LocalTime.MAX);
        Map map=new HashMap();
        map.put("userId",userId);
        map.put("begin",beginTime);
        map.put("end",endTime);
        map.put("noType","普通来电");
        List<LocationVo> locationVoList=callMapper.getLocationByMap(map);
        List<TypeVo> typeVoList=callMapper.getTypeByMap(map);
        LocalDate end;
        List<Integer> numberList = new ArrayList<>();

        while (!begin.isAfter(today)) {
            dateList.add(begin);
            end = begin.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            beginTime=LocalDateTime.of(begin, LocalTime.MIN);
            endTime=LocalDateTime.of(end,LocalTime.MAX);
            map.put("begin",beginTime);
            map.put("end",endTime);
            map.put("noType","普通来电");
            Integer number=callMapper.getNumberByMap(map);
            number=number==null? 0:number;
            numberList.add(number);
            begin=end.plusDays(1);
        }

        return Result.success
                (ReportVo
                        .builder()
                        .timeList(StringUtils.join(dateList,","))
                        .numberList(StringUtils.join(numberList,","))
                        .locationVoList(locationVoList)
                        .typeVoList(typeVoList)
                        .build()
                );
    }

    @Override
    public Result<ReportVo> getYearReport(String token) {
        Long userId = JwtHelper.getIdFromToken(token);
        LocalDate today = LocalDate.now();
        int currentYear = LocalDate.now().getYear();
        LocalDate begin = LocalDate.of(currentYear, 1, 1);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDateTime beginTime=LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime=LocalDateTime.of(today,LocalTime.MAX);
        Map map=new HashMap();
        map.put("userId",userId);
        map.put("begin",beginTime);
        map.put("end",endTime);
        map.put("noType","普通来电");
        List<LocationVo> locationVoList=callMapper.getLocationByMap(map);
        List<TypeVo> typeVoList=callMapper.getTypeByMap(map);
        LocalDate end;
        List<Integer> numberList = new ArrayList<>();

        while (!begin.isAfter(today)) {
            dateList.add(begin);
            end = begin.with(TemporalAdjusters.lastDayOfMonth());
            beginTime=LocalDateTime.of(begin, LocalTime.MIN);
            endTime=LocalDateTime.of(end,LocalTime.MAX);
            map.put("begin",beginTime);
            map.put("end",endTime);
            map.put("noType","普通来电");
            Integer number=callMapper.getNumberByMap(map);
            number=number==null? 0:number;
            numberList.add(number);
            begin=end.plusDays(1);
        }

        return Result.success
                (ReportVo
                        .builder()
                        .timeList(StringUtils.join(dateList,","))
                        .numberList(StringUtils.join(numberList,","))
                        .locationVoList(locationVoList)
                        .typeVoList(typeVoList)
                        .build()
                );
    }
}
