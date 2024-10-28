package com.example.deptech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneAppeal {
    private Integer id;
    private String phone;
    private long createTime;
    private Integer status; // 0:待处理 1:申诉成功 2:申诉失败
}
