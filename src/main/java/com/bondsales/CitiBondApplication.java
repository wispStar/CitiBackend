package com.bondsales;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bondsales.mapper")
public class CitiBondApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitiBondApplication.class, args);
    }

}
