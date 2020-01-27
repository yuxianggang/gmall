package com.igeek.gmall.manager;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 余祥刚
 * @create 2020-01-27 11:17
 */
@SpringBootApplication
@EnableDubbo
public class GmallManagerWebApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallManagerWebApp.class,args);
    }
}
