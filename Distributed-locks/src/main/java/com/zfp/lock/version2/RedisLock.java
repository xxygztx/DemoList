package com.zfp.lock.version2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 防止误删我们的锁，我们要判断只能删除自己的锁，但是这仍然是有漏洞的，
 * 在查询redis中的value和 删除是两个操作，无法保证原子性，在一些极端的情况下仍然会失效。
 */

/**
 * 自己实现的速度很慢，在并发量比较高的时候，用jmeter压测试过，获取到锁的成功率很低，
 * Redisson获取到锁的成功率很低。
 */
@Slf4j
public class RedisLock {

    private StringRedisTemplate stringRedisTemplate;
    private final String uuid= UUID.randomUUID()+"";;
    private final String key;


    /**
     * 创建一把锁，我们的的每一把锁都有唯一的，因为是一把锁，所以我们的key是一样的
     * @param stringRedisTemplate
     *
     */
    public RedisLock(String key,StringRedisTemplate stringRedisTemplate) {
//        log.info(stringRedisTemplate==null?"true":"false");
        this.stringRedisTemplate = stringRedisTemplate;
        this.key=key;
//        log.info(this.key);
    }

    public boolean tryLock( long time, TimeUnit timeUnit){
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(this.key, this.uuid , time, timeUnit);
        if(aBoolean==null || aBoolean==false){
            return false;
        }
        return true;
    }

    /**
     * 很显然这里经过查询再进行删除不能保证这一操作的原子性，还是会存在误删的情况。
     */
    public void unLock(){
        String value = stringRedisTemplate.opsForValue().get(this.key);
        if (value != this.uuid ) {
           return;
        }
        stringRedisTemplate.delete(this.key);
    }
    final DefaultRedisScript<Boolean> booleanDefaultRedisScript;
    //静态代码块直接加载
    {
        booleanDefaultRedisScript = new DefaultRedisScript<>();
        booleanDefaultRedisScript.setResultType(Boolean.class);
        booleanDefaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("delete.lua")));
    }

    /**
     * 使用lua脚本来保证操作的原子性，而且速度更快，但是我们一定要测试好，如果lua脚本出现问题，只能杀死整个进程。
     */
    public void unlock(){

        stringRedisTemplate.execute(booleanDefaultRedisScript, Arrays.asList(this.key), this.uuid);

    }
}
