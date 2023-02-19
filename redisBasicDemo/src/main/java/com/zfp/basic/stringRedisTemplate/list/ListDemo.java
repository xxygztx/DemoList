package com.zfp.basic.stringRedisTemplate.list;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ListDemo {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


}
