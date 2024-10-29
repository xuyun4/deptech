package com.example.daptech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingPhone {
    private Integer id;
    private String phone;
    private String type;

    private Integer times; //被标记次数
}
