package com.example.daptech.util;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtHelper {

    //token有效时常一个月
    private static long tokenExpiration = 24L * 60 * 60 * 1000 * 30;
    // 使用 Keys.secretKeyFor 生成符合HS512算法的安全密钥
    //private static final Key tokenSignKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // 自定义的密钥字符串 (长度推荐至少为 256 位，以确保安全)
    @Value("${jwt.secret.key}")
    private String tokenKey;

    private static String tokenSignKey;

    @Autowired
    private TokenBlackListService tokenBlackListServiceInstance;
    //黑名单服务
    private static TokenBlackListService tokenBlackListService;

    //确保在对象完全创建和初始化之后才执行初始化
    @PostConstruct
    public void init() {
        tokenBlackListService = this.tokenBlackListServiceInstance;
        tokenSignKey = this.tokenKey;
    }

    // 一个公共的静态 getter 方法
    public static String getTokenSignKey() {
        return tokenSignKey;
    }

    //创建token
    public static String createToken(Long id, String phonenumber,String nickname,Integer status) {
        String token = Jwts.builder()
                .setSubject("User")//设置jwt主题为User
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))//token有效时常
                .claim("id",id)//添加字段
                .claim("phonenumber",phonenumber)
                .claim("nickname",nickname)
                .claim("status",status)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)// 使用 HS512 签名算法和密钥 `tokenSignKey` 签名 JWT)
                .compact();
        token = "Bearer " + token;
        return token;
    }

    //校验token(已经过期返回true，否则返回false)
    public static boolean verifyToken(String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        //如果token在黑名单中则失效
        if(tokenBlackListService.isTokenBlacklisted(token)){
            return true;
        }
        try {
            Jws<Claims> parseToken = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .build()
                    .parseClaimsJws(token);
            //校验是否过期
            return !parseToken.getBody().getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }catch (Exception e) {
            //其他解析异常
            return true;
        }
    }

    //解析token
    public static Claims parseToken(String token) {
        token = token.substring(7);
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
        token = token.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Long.class);
    }

    //从token中获取phonenumber
    public static String getPhonenumberFromToken(String token) {
        token = token.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("phonenumber", String.class);
    }

    //从token中获取NickName
    public static String getNickNameFromToken(String token) {
        token = token.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("nickname", String.class);
    }

    //从token中获取status
    public static Integer getStatusFromToken(String token) {
        token = token.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("status", Integer.class);
    }

}