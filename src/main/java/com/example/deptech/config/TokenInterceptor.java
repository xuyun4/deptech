package com.example.deptech.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class TokenInterceptor implements HandlerInterceptor {

    private final String tokenSignKey = "123456"; // 签名密钥

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从请求头中获取 token
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("token不存在或错误");
            return false;
        }

        try {
            // 解析 token
            Jws<Claims> parseToken = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .build()
                    .parseClaimsJws(token);

            // 可以在这里处理 claims，例如将用户信息存入 request attribute
            request.setAttribute("claims", parseToken.getBody());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("无效 token");
            return false;
        }

        return true; // 放行
    }

}
