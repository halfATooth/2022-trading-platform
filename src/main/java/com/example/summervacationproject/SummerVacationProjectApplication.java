package com.example.summervacationproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.summervacationproject.mapper")
public class SummerVacationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummerVacationProjectApplication.class, args);
    }

}
