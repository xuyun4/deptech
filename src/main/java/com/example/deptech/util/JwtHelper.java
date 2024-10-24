package com.example.deptech.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtHelper {

    //token有效时长一个月
    private static long tokenExpiration = 24L * 60 * 60 * 1000 * 30;
    // 使用 Keys.secretKeyFor 生成符合HS512算法的安全密钥
    private static final Key tokenSignKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //创建token
    public static String createToken(Long id, String phonenumber,String password) {
        String token = Jwts.builder()
                .setSubject("User")//设置jwt主题为User
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))//token有效时长
                .claim("id",id)//添加字段
                .claim("phonenumber",phonenumber)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)// 使用 HS512 签名算法和密钥 `tokenSignKey` 签名 JWT)
                .compact();
        token = "Bearer " + token;//符合规范Bearer {token} 的格式
        return token;
    }

    //校验token(已经过期返回true，否则返回false)
    public static boolean verifyToken(String token) {
        //如果token在黑名单中则失效
        if(TokenBlackListService.isTokenBlacklisted(token)){
            return true;
        }
        try {
            Jws<Claims> parseToken = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .build()
                    .parseClaimsJws(token);
            //校验是否过期
            return !parseToken.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }catch (Exception e) {
            //其他解析异常
            return true;
        }
    }

    //解析token
    public static Claims parseToken(String token) {
        try {
            Jws<Claims> parseToken = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .build()
                    .parseClaimsJws(token);
            return parseToken.getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        }
    }

    //从token中获取用户id
    public static Long getIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Long.class);
    }

    //从token中获取phonenumber
    public static String getPhonenumberFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("phonenumber", String.class);
    }

}
