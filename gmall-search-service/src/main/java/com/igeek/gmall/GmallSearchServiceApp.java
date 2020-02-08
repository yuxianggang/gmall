package com.igeek.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 余祥刚
 * @create 2020-02-06 13:22
 */
@SpringBootApplication
@EnableDubbo
public class GmallSearchServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallSearchServiceApp.class,args);
    }
}
