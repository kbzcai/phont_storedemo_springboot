package com.cyj.phone_storedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyj.phone_storedemo.mapper")
public class PhoneStoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneStoredemoApplication.class, args);
    }

}
