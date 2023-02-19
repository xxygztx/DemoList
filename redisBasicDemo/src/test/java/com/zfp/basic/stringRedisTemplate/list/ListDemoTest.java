package com.zfp.basic.stringRedisTemplate.list;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import java.util.ArrayList;

@SpringBootTest
class ListDemoTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    final String key = "list:name:";
    @Test
    public void test(){
        String keys =key + 1;
        stringRedisTemplate.opsForList().leftPush(keys,"lisi");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("zhangsan");
        strings.add("wangwu");
        strings.add("heiha");
        stringRedisTemplate.opsForList().leftPushAll(keys,strings);
    }
    @Test
    public void tests(){
        String keys =key+1;
        String index = stringRedisTemplate.opsForList().index(keys, 0);
        System.out.println(index);
//        Long lisi = stringRedisTemplate.opsForList().lastIndexOf(keys, "lisi");
//        System.out.println(lisi);
    }



}