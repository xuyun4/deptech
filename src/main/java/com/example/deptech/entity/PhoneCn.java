package com.example.deptech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneCn {
    private Integer id;
    private String phone;
    private String type;
    private Integer number;
    private Integer status;
    private Integer createTime;
    private Integer updateTime;
    private String location;
    private String information;

}
