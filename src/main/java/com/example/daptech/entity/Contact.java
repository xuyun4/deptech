package com.example.daptech.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//这是联系人/黑白名单人员的实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("contact")
public class Contact {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phoneNumber;
    private Long userId;
}
