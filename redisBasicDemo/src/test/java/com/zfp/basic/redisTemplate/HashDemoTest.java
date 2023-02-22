package com.zfp.basic.redisTemplate;

import com.zfp.basic.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import javax.annotation.Resource;

import java.util.Map;

@SpringBootTest
class HashDemoTest {


    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 测试当我们的查询为空的时候我们得到的Map对象是不是null
     */
    @Test
    public void testReturnToGetHash(){
        Map one = redisTemplate.opsForHash().entries("one");
        System.out.println(one);
        //很显然map集合得到的不是null而是一个 {}对应map里面的Api是 isEmpty()
        Object one1 = redisTemplate.opsForValue().get("one");
        System.out.println(one1);

    }

}