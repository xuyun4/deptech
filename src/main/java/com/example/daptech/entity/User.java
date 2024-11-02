package com.example.daptech.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户手机号
     */
    private String phoneNumber;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户创建时间
     */
    private Date createdAt;

    /**
     * 用户修改时间
     */
    private Date updatedAt;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像url
     */
    private String avatarUrl;

    /**
     * 用户身份
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", status=" + status +
                '}';
    }
}