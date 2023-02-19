package com.zfp.basic.stringRedisTemplate.hash;

import com.zfp.basic.untils.ForeachObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class HashDemo {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public<O> void set(String key ,O data){
        Map<String, String> studentMap = ForeachObject.objectToMap(data);
        stringRedisTemplate.opsForHash().putAll(key,studentMap);
    }

    public<T> T get(String key,Class<T> target){
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        for(Map.Entry<Object, Object> i:entries.entrySet()){
            System.out.println(i.getKey());
            System.out.println(i.getValue());
        }
        return null;
    }
}
