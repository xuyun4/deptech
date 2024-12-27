package com.example.daptech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.daptech.entity.Call;
import com.example.daptech.entity.vo.CallVo;
import com.example.daptech.entity.vo.ReportVo;
import com.example.daptech.response.Result;
import com.example.daptech.entity.dto.CallDto;

import java.util.List;

public interface CallService extends IService<Call> {

    Result updateCall(String token, List<CallDto> callDtoList);

    Result<List<CallVo>> getCall(String token);

    Result<ReportVo> getWeekReport(String token);

    Result<ReportVo> getMonthReport(String token);

    Result<ReportVo> getYearReport(String token);
}
