package com.example.daptech.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("call")
public class Call {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phoneNumber;
    private LocalDateTime time;
    private Long userId;
    private Integer deleted;
}
