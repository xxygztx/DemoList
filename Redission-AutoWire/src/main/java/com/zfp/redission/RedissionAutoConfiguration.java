package com.zfp.redission;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(RedissionProperties.class)
public class RedissionAutoConfiguration {

    @Bean
    public RedissonClient MyRedissonClient(RedissionProperties redissionProperties){
        Config config = new Config();
        log.info(redissionProperties.getAddress());
        config.useSingleServer()
                .setAddress(redissionProperties.getAddress());
        return Redisson.create(config);
    }
}
