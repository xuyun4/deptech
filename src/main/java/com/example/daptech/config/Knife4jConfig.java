package com.example.daptech.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableKnife4j
public class Knife4jConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                        //设置标题
                        .title("knife4j接口文档")
                        //设置简述
                        .description("daptech的接口文档,基于knife4j-openapi3-jakarta-spring-boot-starter")
                        //设置版本
                        .version("1.0")
                        //设置联系方式
                        .contact(new Contact().name("银").email("3512036490@qq.com")))
                //指定外部文档资源
                .externalDocs(new ExternalDocumentation().description("springboot基础框架").url("http://localhost:8080"));
    }

    //分组
    @Bean
    public GroupedOpenApi groupedOpenApi2() {
        return GroupedOpenApi.builder()
                //设置分组名称
                .group("项目管理")
                //分组url
                .pathsToMatch("/**")
                //该分组所在的包
                .packagesToScan("com.example.daptech.controller").build();
    }
}
