package com.zfp.basic.redisTemplate;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service(value = "redisHashDemo")
public class HashDemo {

    @Resource
    private RedisTemplate redisTemplate;

    public<T> T getHash(String key,Class<T> target){
        Map entries = redisTemplate.opsForHash().entries(key);
        T t=null;
        try {
             t = BeanUtil.fillBeanWithMap(entries, target.newInstance(), false);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
