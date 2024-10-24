package com.example.deptech.entity.phonenumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PendingPhone {
    private int id;
    private String type;
    private int times;
    private String phone;
}
