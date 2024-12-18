package com.example.daptech.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blacklist")
public class Blacklist {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phoneNumber;
    private Long userId;
}
