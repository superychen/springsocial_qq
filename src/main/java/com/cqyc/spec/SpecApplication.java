package com.cqyc.spec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@SpringBootApplication
@MapperScan("com.cqyc.spec.mapper")
public class SpecApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpecApplication.class);
    }
}
