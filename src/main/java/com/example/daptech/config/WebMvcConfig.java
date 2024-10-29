package com.example.daptech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/swagger-ui.html","/doc.html","/swagger-ui/**", "/v3/api-docs/**",
                        "/swagger-resources/**", "/webjars/**", "/swagger-ui/index.html#");
    }
}
