package com.zfp.combat.config;


import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class MyRedisson {

    @Resource
    private RedissonClient redissonClient;

    public void test(){
        RMap<Object, Object> ll = redissonClient.getMap("ll");
        log.info(ll.toString());
    }




}
