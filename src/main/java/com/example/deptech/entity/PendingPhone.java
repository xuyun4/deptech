package com.example.deptech.entity;

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

    private Integer times;
}
