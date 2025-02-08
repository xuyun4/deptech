package com.example.daptech.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//骚扰电话统计的返回类
public class ReportVo {
    private String timeList;
    private String numberList;
}
