package com.zfp.lock.version1;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 这个是最简单的lock主要运用的是 redis中的 setnx setex命令，当key存在时，我们不能
 */
public class LockUtil {

    private StringRedisTemplate stringRedisTemplate;

    public LockUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public  boolean tryLock(String key , String value, long time, TimeUnit timeUnit){
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
        if(aBoolean==null || aBoolean==false ){
            return false;
        }
        return true;
    }

    public void unLock(String key){
        stringRedisTemplate.delete(key);
    }

}
