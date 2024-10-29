package com.example.daptech.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String phoneNumber;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String verificationCode;

    /**
     * 
     */
    private Date codeExpiration;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    @TableField("updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        User other = (User) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//                && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
//                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
//                && (this.getVerificationCode() == null ? other.getVerificationCode() == null : this.getVerificationCode().equals(other.getVerificationCode()))
//                && (this.getCodeExpiration() == null ? other.getCodeExpiration() == null : this.getCodeExpiration().equals(other.getCodeExpiration()))
//                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
//                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
//        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
//        result = prime * result + ((getVerificationCode() == null) ? 0 : getVerificationCode().hashCode());
//        result = prime * result + ((getCodeExpiration() == null) ? 0 : getCodeExpiration().hashCode());
//        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
//        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
//        return result;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", password=").append(password);
        sb.append(", verificationCode=").append(verificationCode);
        sb.append(", codeExpiration=").append(codeExpiration);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}