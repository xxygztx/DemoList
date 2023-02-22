package com.zfp.combat.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MyRedissonTest {

    @Resource
    private MyRedisson myRedisson;

    @Test
    public void test(){
        myRedisson.test();
    }


}