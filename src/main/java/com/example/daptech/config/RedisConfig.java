package com.example.daptech.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {

    // 默认 Redis 连接工厂，连接到数据库 0
    @Primary
    @Bean(name = "defaultLettuceConnectionFactory")
    public LettuceConnectionFactory defaultLettuceConnectionFactory(
            @Qualifier("defaultRedisConfig") RedisStandaloneConfiguration defaultRedisConfig,
            GenericObjectPoolConfig defaultPoolConfig) {
        LettuceClientConfiguration clientConfig =
                LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(100))
                        .poolConfig(defaultPoolConfig).build();
        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    }

    // 默认 RedisTemplate，连接到数据库 0
    @Primary
    @Bean(name = "defaultRedisTemplate")
    public RedisTemplate<String, String> defaultRedisTemplate(
            @Qualifier("defaultLettuceConnectionFactory") LettuceConnectionFactory defaultLettuceConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultLettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    // 自定义 Redis 连接工厂，连接到数据库 1，用于存储验证码
    @Bean(name = "smsLettuceConnectionFactory")
    public LettuceConnectionFactory smsLettuceConnectionFactory(
            @Qualifier("smsRedisStandaloneConfig") RedisStandaloneConfiguration smsRedisStandaloneConfig,
            GenericObjectPoolConfig smsPoolConfig) {
        LettuceClientConfiguration clientConfig =
                LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(100))
                        .poolConfig(smsPoolConfig).build();
        return new LettuceConnectionFactory(smsRedisStandaloneConfig, clientConfig);
    }

    // 自定义 RedisTemplate，连接到数据库 1
    @Bean(name = "smsRedisTemplate")
    public RedisTemplate<String, String> smsRedisTemplate(
            @Qualifier("smsLettuceConnectionFactory") LettuceConnectionFactory smsLettuceConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(smsLettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 默认 Redis 配置
     */
    @Primary
    @Configuration
    public static class DefaultRedisConfig {
        @Value("${spring.redis.host:127.0.0.1}")
        private String host;
        @Value("${spring.redis.port:6379}")
        private Integer port;
        @Value("${spring.redis.password:}")
        private String password;
        @Value("${spring.redis.database:0}")
        private Integer database;

        @Value("${spring.redis.lettuce.pool.max-wait:-1}")
        private Long maxWait;

        @Primary
        @Bean(name = "defaultPoolConfig")
        public GenericObjectPoolConfig defaultPoolConfig() {
            GenericObjectPoolConfig config = new GenericObjectPoolConfig();
            config.setMaxWaitMillis(maxWait);
            return config;
        }

        @Primary
        @Bean(name = "defaultRedisConfig")
        public RedisStandaloneConfiguration defaultRedisConfig() {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
            config.setHostName(host);
            config.setPort(port);
            config.setPassword(RedisPassword.of(password));
            config.setDatabase(database);
            return config;
        }
    }

    /**
     * SMS Redis 配置
     */
    @Configuration
    public static class SmsRedisConfig {
        @Value("${spring.redis2.host:127.0.0.1}")
        private String host;
        @Value("${spring.redis2.port:6379}")
        private Integer port;
        @Value("${spring.redis2.password:}")
        private String password;
        @Value("${spring.redis2.database:1}")
        private Integer database;

        @Value("${spring.redis2.lettuce.pool.max-wait:-1}")
        private Long maxWait;

        @Bean(name = "smsPoolConfig")
        public GenericObjectPoolConfig smsPoolConfig() {
            GenericObjectPoolConfig config = new GenericObjectPoolConfig();
            config.setMaxWaitMillis(maxWait);
            return config;
        }

        @Bean(name = "smsRedisStandaloneConfig")
        public RedisStandaloneConfiguration smsRedisStandaloneConfig() {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
            config.setHostName(host);
            config.setPort(port);
            config.setPassword(RedisPassword.of(password));
            config.setDatabase(database);
            return config;
        }
    }
}
