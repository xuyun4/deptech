package com.example.daptech.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneUs {
    @ExcelProperty("id")
    private Integer id;
    @ExcelProperty("号码")
    private String phone;  //格式可以为纯数字,也可以为304-658-7567的格式
    /*拦截类型,
    Harassment Call
    Agent Harassment
    Scam Call
    Telemarketing Call
    Suspected Sales Call
    Illegal Call */
    @ExcelProperty("类型")
    private String type;
    @ExcelProperty("次数")
    private Integer number;
    @ExcelProperty("状态")
    private Integer status;  //是否为虚拟号,0为真实号,1为虚拟号
    @ExcelProperty("创建时间")
    private Long  createTime;
    @ExcelProperty("更新时间")
    private Long  updateTime;
    @ExcelProperty("位置")
    private String location;
    @ExcelProperty("信息")
    private String information;  //需要提取详细信息,算法待定

}
