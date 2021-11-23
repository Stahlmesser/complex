package com.complex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.complex.mapper")
public class ComplexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexApplication.class, args);
    }

}
