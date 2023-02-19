package com.zfp.basic.stringRedisTemplate.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class DemoTest {

    @Autowired
    private Demo demo;

    @Test
    public void testOne(){
        final String key = "string:name:";
        final String name=key+"lisi";
        demo.set(name,"张三");

        String dataName = demo.get(name);
        log.info(dataName);

        Boolean del = demo.del(name);
        log.info(del.toString());

        log.info(demo.get(name));
    }

    //单独测试del
    @Test
    public void del(){
        Boolean lll = demo.del("lll");
        log.info(lll.toString());
    }
}