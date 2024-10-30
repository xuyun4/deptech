package com.example.daptech.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//用户通过账号密码登录请求体
public class LoginByPhoneNumRequest {

    @Schema(example = "11111111111", description = "手机号必须为11位数字")
    @Pattern(regexp = "^\\d{11}$", message = "手机号必须为11位数字")
    private String phoneNumber;

    @Schema(example = "123456abc", description = "密码必须包含字母和数字，长度为6到15个字符")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$", message = "密码必须包含字母和数字，长度为6到15个字符")
    private String password;
}
