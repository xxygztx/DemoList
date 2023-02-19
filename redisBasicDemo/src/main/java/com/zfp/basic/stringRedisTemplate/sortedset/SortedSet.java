package com.zfp.basic.stringRedisTemplate.sortedset;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SortedSet {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setSortedSet(String key,String value){
        stringRedisTemplate.opsForZSet().add(key,value,2);

    }


}
