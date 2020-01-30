package com.igeek.gmall.manager.config;

import com.igeek.gmall.jedis.JedisUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author 余祥刚
 * @create 2020-01-30 12:22
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class JedisConfig {

    private String host;
    private Integer port;
    private Integer database;

    @Bean
    public JedisUtil getRedisUtil(){
        JedisUtil redisUtil=new JedisUtil();
        redisUtil.initPool(host,port,database);
        return redisUtil;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }
}
