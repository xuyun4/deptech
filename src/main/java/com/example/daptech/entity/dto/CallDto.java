package com.example.daptech.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallDto {
    private String name;
    private String phoneNumber;
    private LocalDateTime time;
    private String type;
    private String location;
}
