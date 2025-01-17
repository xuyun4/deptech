package com.example.daptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daptech.entity.Call;
import com.example.daptech.entity.dto.CallDto;
import com.example.daptech.entity.vo.CallVo;
import com.example.daptech.entity.vo.ReportVo;
import com.example.daptech.mapper.CallMapper;
import com.example.daptech.response.Result;
import com.example.daptech.service.CallService;
import com.example.daptech.util.ArrayUtil;
import com.example.daptech.util.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //逻辑同黑名单
        Long userId = JwtHelper.getIdFromToken(token);
/*        QueryWrapper<Call> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        callMapper.delete(queryWrapper);*/
        callMapper.deleteCall(userId);
        List<Call> callList = new ArrayList<>();
        for (CallDto callDto : callDtoList) {
            Call call = new Call();
            BeanUtils.copyProperties(callDto, call);
            call.setUserId(userId);
            callList.add(call);
        }
        /*saveBatch(callList);*/
        callMapper.addCall(callList);
        return Result.success();
    }

    @Override
    public Result<List<CallVo>> getCall(String token) {
        //逻辑同黑名单
        Long userId = JwtHelper.getIdFromToken(token);
/*        QueryWrapper<Call> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Call> callList = callMapper.selectList(queryWrapper);*/
        List<Call> callList=callMapper.selectCall(userId);
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

        //获取起始日期和结束日期
        LocalDate end = LocalDate.now();
        LocalDate begin = end.minusDays(6);
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        //添加所有日期到dateList
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //查询每天的骚扰电话数量
        List<Integer> numberList = new ArrayList<>();
        LocalDateTime beginTime;
        LocalDateTime endTime;
        Map map = new HashMap();
        for (LocalDate date : dateList) {
            beginTime = LocalDateTime.of(date, LocalTime.MIN);
            endTime = LocalDateTime.of(date, LocalTime.MAX);
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("noType", "普通来电");
            Integer number = callMapper.getNumberByMap(map);
            number = number == null ? 0 : number;
            numberList.add(number);
        }

        //包装返回
        return Result.success
                (ReportVo
                        .builder()
                        .timeList(StringUtils.join(dateList, ","))
                        .numberList(StringUtils.join(numberList, ","))
                        .build()
                );
    }

    @Override
    public Result<ReportVo> getMonthReport(String token) {
        //逻辑同近一周查询
        Long userId = JwtHelper.getIdFromToken(token);
        LocalDate today = LocalDate.now();
        LocalDate begin = today.withDayOfMonth(1);
        LocalDate end;
        List<String> timeList = new ArrayList<>();
        LocalDateTime beginTime;
        LocalDateTime endTime;
        Map map = new HashMap();
        List<Integer> numberList = new ArrayList<>();

        Integer flag = 1;
        while (!begin.isAfter(today)) {
            timeList.add(ArrayUtil.Weeks[flag]);
            flag = flag + 1;
            end = begin.plusDays(6);
            beginTime = LocalDateTime.of(begin, LocalTime.MIN);
            endTime = LocalDateTime.of(end, LocalTime.MAX);
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("noType", "普通来电");
            Integer number = callMapper.getNumberByMap(map);
            number = number == null ? 0 : number;
            numberList.add(number);
            begin = end.plusDays(1);
        }

        return Result.success
                (ReportVo
                        .builder()
                        .timeList(StringUtils.join(timeList, ","))
                        .numberList(StringUtils.join(numberList, ","))
                        .build()
                );
    }

    @Override
    public Result<ReportVo> getYearReport(String token) {
        //逻辑同近一周查询
        Long userId = JwtHelper.getIdFromToken(token);
        LocalDate today = LocalDate.now();
        int currentYear = LocalDate.now().getYear();
        LocalDate begin = LocalDate.of(currentYear, 1, 1);
        LocalDate end;
        List<String> timeList = new ArrayList<>();
        LocalDateTime beginTime;
        LocalDateTime endTime;
        Map map = new HashMap();
        List<Integer> numberList = new ArrayList<>();

        Integer flag = 1;
        while (!begin.isAfter(today)) {
            timeList.add(ArrayUtil.Months[flag]);
            end = begin.with(TemporalAdjusters.lastDayOfMonth());
            beginTime = LocalDateTime.of(begin, LocalTime.MIN);
            endTime = LocalDateTime.of(end, LocalTime.MAX);
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("noType", "普通来电");
            Integer number = callMapper.getNumberByMap(map);
            number = number == null ? 0 : number;
            numberList.add(number);
            begin = end.plusDays(1);
        }

        return Result.success
                (ReportVo
                        .builder()
                        .timeList(StringUtils.join(timeList, ","))
                        .numberList(StringUtils.join(numberList, ","))
                        .build()
                );
    }
}
