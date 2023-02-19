package com.zfp.basic.stringRedisTemplate.string;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class Demo {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public void set(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public String get(String key){
        String result = stringRedisTemplate.opsForValue().get(key);
        return result;
    }

    public Boolean del(String key){
        Boolean delete = stringRedisTemplate.delete(key);
        return delete;
    }

}
