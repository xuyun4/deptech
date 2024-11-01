package com.example.daptech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneMark {
    public Integer id;
    public String type;
    public String phone;
    public String mark; //单次申诉的信息

}
