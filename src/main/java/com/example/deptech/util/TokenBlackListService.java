package com.example.deptech.util;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//token与黑名单相关的服务
public class TokenBlackListService {

    //将无效token加入黑名单
    public static void addTokenToBlacklist(String token) {
        // 解析 Token 获取它的到期时间
        Claims claims = JwtHelper.parseToken(token);
        Date expiration = claims.getExpiration();

        // 将 Token 放入 Redis，并设置它的过期时间等于 JWT Token 的过期时间
        //redisTemplate.opsForValue().set(token, "blacklisted", expiration.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    //检查token是否在黑名单中
    public static boolean isTokenBlacklisted(String token) {
        // 检查 Redis 中是否存在该 Token
        //return redisTemplate.hasKey(token);
        return true;
    }

}
