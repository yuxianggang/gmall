package com.igeek.gmall.search;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 余祥刚
 * @create 2020-02-06 13:12
 */
@SpringBootApplication
@EnableDubbo
public class GmallSearchWebApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallSearchWebApp.class,args);
    }
}
