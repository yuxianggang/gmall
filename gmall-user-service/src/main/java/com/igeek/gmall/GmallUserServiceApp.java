package com.igeek.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 余祥刚
 * @create 2020-01-26 18:23
 */
@SpringBootApplication
@MapperScan(basePackages = "com.igeek.gmall.mapper")
@EnableDubbo(scanBasePackages = "com.igeek.gmall.service.impl")
public class GmallUserServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserServiceApp.class,args);
    }
}
