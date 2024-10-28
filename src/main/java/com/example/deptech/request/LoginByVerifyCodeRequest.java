package com.example.deptech.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//通过验证码登录请求体
public class LoginByVerifyCodeRequest {

    @Schema(example = "11111111111", description = "手机号必须为11位数字")
    @Pattern(regexp = "^\\d{11}$", message = "手机号必须为11位数字")
    private String phoneNumber;

    @Schema(example = "123456", description = "验证码为6位数字")
    @Pattern(regexp = "^\\d{6}$", message = "验证码为6位数字")
    private String verifyCode;
}
