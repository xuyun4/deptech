package com.example.daptech.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneUsVo {
    private Integer id;
    private String phone;
    private String type;
    private Integer number;
    private Integer status;  //是否为虚拟号
    private Long  createTime;
    private Long  updateTime;
    private String location;
    private String information;  //需要提取详细信息,算法待定

    private Double value; //号码风险值
}
