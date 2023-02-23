package com.zfp.lock.version2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Data
@Service
public class TestRedisLock {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //使用Redisson分布式锁
    @Resource
    private RedissonClient redissonClient;

    //我们有这么多烧饼
    private int count = 100000;


    //我们进行吃烧饼
    public void eat() {
        count--;
    }

    /**
     * 这里有一个十分重要的问题？
     * 当我们在外面给redisLock传参的时候，我们传进去的参数时空的。
     * 我们在方法里面的进行创建对象传参的时候并没有这个问题。
     */
    //RedisLock redisLock = new RedisLock("count:1",stringRedisTemplate);


    //我们一个饼只能一个人吃
    public void eats() {
//        log.info("进入eats方法");
        RedisLock redisLock = new RedisLock("count:1", stringRedisTemplate);
//        log.info("创建成功");
        boolean b = redisLock.tryLock(10, TimeUnit.SECONDS);
        log.info("获取锁：" + b);
        if (b == false) {
            try {
                Thread.sleep(2000);
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("减一");
        count--;
        redisLock.unLock();

    }

    public void eatsByLua() {
//        log.info("进入eats方法");
        RedisLock redisLock = new RedisLock("count:1", stringRedisTemplate);
//        log.info("创建成功");
        //其实就是运用cas原理
        try {
            Thread.sleep(10);
            while (!redisLock.tryLock(10, TimeUnit.SECONDS)) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        log.info("减一");
        count--;
        redisLock.unlock();

    }


    //我们一个饼只能一个人吃
    public void eatsByRedisson() throws InterruptedException {
//        log.info("进入eats方法");
        RLock lock = redissonClient.getLock("eat:count:1");
//        log.info("创建成功");
        while (!lock.tryLock(10, TimeUnit.SECONDS)) {
            try {

                /**
                 * 重点：十分重点，我们不能调用递归，
                 * 如果获取锁失败，我们一直递归调用，前端虽然只有十个用户，
                 * 但是我们的count--欣慰递归的原因，可能会成次方数增加。
                 */
                // eats(); //这个不能使用
                Thread.sleep(20);
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("减一");
        count--;
        lock.unlock();
    }

}
