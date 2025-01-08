package com.example.daptech.interceptor;


import com.example.daptech.util.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private final String tokenSignKey = JwtHelper.getTokenSignKey(); // 签名密钥

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从请求头中获取 token
        String token = request.getHeader("Authorization");
        log.info("jwt校验:{}", token);
        log.info("{}",request.getRequestURL());
        //验证token是否合法或存在
        if (token == null || token.isEmpty() || JwtHelper.verifyToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("token不存在或错误");
            return false;
        }

        try {
            // 解析 token
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
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
