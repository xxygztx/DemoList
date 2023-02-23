package com.zfp.combat.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class MyRedissonTest {

    @Resource
    private MyRedisson myRedisson;
    @Resource
    private RedissonClient redissonClient;

    @Test
    public void test(){
        myRedisson.test();
    }
    final String lock="my";

    @Test
    public void testLock() throws InterruptedException {
        RLock lock = redissonClient.getLock(this.lock);
        boolean b = lock.tryLock(2, TimeUnit.MINUTES);
        log.info(String.valueOf(b));

    }

}