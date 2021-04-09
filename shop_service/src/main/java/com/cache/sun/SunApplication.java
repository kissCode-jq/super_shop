package com.cache.sun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

//Spring Boot 缓存注解
@EnableCaching
@MapperScan("com.cache.sun.mapper")
@SpringBootApplication
public class SunApplication {


    public static void main(String[] args) {


        SpringApplication.run(SunApplication.class, args);

    }

}
