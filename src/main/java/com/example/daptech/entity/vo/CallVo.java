package com.example.daptech.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallVo {
    private String name;
    private String phoneNumber;
    private LocalDateTime time;
    private String type;
}
