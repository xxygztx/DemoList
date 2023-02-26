package com.zfp.aop.agent.cglibproxy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CglibOrderServiceProxyTest {

    @Test
    void testCglib(){
        ShopOrderService shopOrderService = new ShopOrderService();
        CglibOrderServiceProxy cglibOrderServiceProxy = new CglibOrderServiceProxy();
        ShopOrderService proxyObject = (ShopOrderService) cglibOrderServiceProxy.createProxyObject(shopOrderService);
        proxyObject.create();
        proxyObject.query();
    }
}