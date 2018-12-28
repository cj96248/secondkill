package com.imooc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.imooc.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Hello World!");
    }
}
