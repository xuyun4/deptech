package com.example.daptech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneMark {
    private Integer id;
    private String type;
    private String phone;
    private String mark; //单次申诉的信息

    private Long createTime;
    public Long userId;

}
