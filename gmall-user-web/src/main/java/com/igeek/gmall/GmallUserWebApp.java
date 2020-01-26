package com.igeek.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 余祥刚
 * @create 2020-01-26 19:23
 */
@SpringBootApplication
@EnableDubbo
public class GmallUserWebApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserWebApp.class,args);
    }
}
