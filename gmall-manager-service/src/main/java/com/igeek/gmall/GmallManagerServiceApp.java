package com.igeek.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 余祥刚
 * @create 2020-01-27 10:45
 */
@SpringBootApplication
@MapperScan(basePackages = "com.igeek.gmall.manager.mapper")
@EnableDubbo
@EnableTransactionManagement
public class GmallManagerServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallManagerServiceApp.class,args);
    }
}
