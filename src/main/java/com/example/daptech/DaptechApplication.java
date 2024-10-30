package com.example.daptech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DaptechApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaptechApplication.class, args);
    }

}
