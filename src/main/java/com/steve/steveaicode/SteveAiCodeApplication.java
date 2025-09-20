package com.steve.steveaicode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.steve.steveaicode.mapper")
public class SteveAiCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteveAiCodeApplication.class, args);
    }

}
