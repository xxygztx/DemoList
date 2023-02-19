package com.zfp.basic.stringRedisTemplate.set;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class SetDemo {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void  setSet(String key,String value){
        stringRedisTemplate.opsForSet().add(key,value);
    }

    public void getSet(String key){
        stringRedisTemplate.opsForSet().members(key);
    }
}
