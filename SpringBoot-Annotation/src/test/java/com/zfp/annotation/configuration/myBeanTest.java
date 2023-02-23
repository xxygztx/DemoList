package com.zfp.annotation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class myBeanTest {

    @Resource
    private MyBean myBean;

    @Test
    public void getMyBean(){
        log.info(myBean.getName());
    }

}