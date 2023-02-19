package com.zfp.combat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.zfp")
@MapperScan("com.zfp.combat.mapper")
@SpringBootApplication
public class RedisComBatApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisComBatApplication.class,args);
    }
}
