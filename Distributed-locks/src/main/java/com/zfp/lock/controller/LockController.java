package com.zfp.lock.controller;

import com.zfp.lock.version2.TestRedisLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LockController {

    @Resource
    private TestRedisLock testRedisLock;

    @GetMapping("/test/redisLock")
    public int testRedisLock(){
        testRedisLock.eats();
        return testRedisLock.getCount();
    }

    @GetMapping("/test/redissonLock")
    public int testRedissonLock() throws InterruptedException {
        testRedisLock.eatsByRedisson();
        return testRedisLock.getCount();
    }

    @GetMapping("/test/redisLua")
    public int testRedisLua() throws InterruptedException {
        testRedisLock.eatsByLua();
        return testRedisLock.getCount();
    }
}
