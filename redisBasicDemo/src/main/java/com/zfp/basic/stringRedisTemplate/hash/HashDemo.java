package com.zfp.basic.stringRedisTemplate.hash;

import cn.hutool.core.bean.BeanUtil;
import com.zfp.basic.entity.Student;
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
//            System.out.println(i.getKey().getClass().getTypeName());
//            System.out.println(i.getValue().getClass().getTypeName());
        }
        T student = ForeachObject.mapToObject(entries, target);
        return student;
    }
    //使用hutool工具类中的方法,还是会保存跟我写的工具报错的原因一致，可以去看源码
    public<T> T gets(String key,Class<T> target) throws InstantiationException, IllegalAccessException {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        T student = BeanUtil.fillBeanWithMap(entries,target.newInstance(),false);
        return student;
    }
}
