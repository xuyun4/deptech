package com.example.daptech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jacoco.agent.rt.internal_43f5073.Agent;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneUs {

    private Integer id;

    private String phone;  //格式可以为纯数字,也可以为304-658-7567的格式
    /*拦截类型,
    Harassment Call
    Agent Harassment
    Scam Call
    Telemarketing Call
    Suspected Sales Call
    Illegal Call */
    private String type;
    private Integer number;

    private Integer status;  //是否为虚拟号,0为真实号,1为虚拟号
    private Long  createTime;
    private Long  updateTime;
    private String location;
    private String information;  //需要提取详细信息,算法待定

}
