package com.zfp.lock.version2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TestRedisLockTest {

    @Resource
    private TestRedisLock testRedisLock;
    @Test
    public void eat() throws InterruptedException {
        //开启20个线程同时进行吃烧饼
        for(int i=0;i<20;i++){
            new Thread(() -> {
                //每一个人吃50个烧饼
                for (int j = 0; j < 5000; j++) {
                    testRedisLock.eat();
                }
            }).start();
        }

        Thread.sleep(1000);
        //循环结束后打印我们剩余的烧饼数量
        log.info("烧饼数量");
        log.info("======="+testRedisLock.getCount()+"========");
        //很显然结果是错的
        /**
         * 12:20:47.420 [main] INFO com.zfp.lock.version2.TestRedisLockTest - 烧饼数量
         * 12:20:47.422 [main] INFO com.zfp.lock.version2.TestRedisLockTest - =======70358========
         */
    }
    @Test
    public void tests() throws InterruptedException {
        //开启20个线程同时进行吃烧饼
        for(int i=0;i<20;i++){
            new Thread(() -> {
                //每一个人吃50个烧饼
                for (int j = 0; j < 20; j++) {
                    log.info("开始吃");
                    testRedisLock.eats();
                }
            }).start();
        }

        Thread.sleep(1000);
        //循环结束后打印我们剩余的烧饼数量
        log.info("烧饼数量");
        log.info("======="+testRedisLock.getCount()+"========");
    }

    @Test
    public void oneEat(){
        testRedisLock.eats();
    }



}