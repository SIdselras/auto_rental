package com.zxy.rental;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
@SpringBootApplication
@MapperScan("com.zxy.rental.mapper")
public class AppServer {
    public static void main(String[] args) {
        SpringApplication.run(AppServer.class, args);
    }
}
