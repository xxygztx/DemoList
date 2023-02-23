package com.zfp.lock.version2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class RedisLockTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringRedisTemplate(){
        RedisLock ll = new RedisLock("ll", stringRedisTemplate);
        boolean b = ll.tryLock(2, TimeUnit.SECONDS);
        log.info(b+"");
    }

}