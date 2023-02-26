package com.zfp.aop.agent.jdkproxy;

import com.zfp.aop.agent.staticproxy.PhoneOrderService;
import com.zfp.aop.agent.staticproxy.PhonePhoneOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceProxyTest {

    @Test
    public void testJdkProxy(){
        PhoneOrderService phonePhoneOrderService = new PhonePhoneOrderServiceImpl();
        OrderServiceProxy orderServiceProxy = new OrderServiceProxy();
        orderServiceProxy.setTarget(phonePhoneOrderService);
        PhoneOrderService proxyTarget =(PhoneOrderService) orderServiceProxy.createProxyTarget();
        proxyTarget.createPhoneOrder();
    }

    @Test
    public  void testJdkProxyInClass(){
        PhonePhoneOrderServiceImpl phonePhoneOrderService = new PhonePhoneOrderServiceImpl();
        OrderServiceProxy orderServiceProxy = new OrderServiceProxy();
        orderServiceProxy.setTarget(phonePhoneOrderService);
        //jdk基于接口的动态代理技术，这里如果使用实现类的强制转换就会报错。
        PhoneOrderService proxyTarget =(PhonePhoneOrderServiceImpl) orderServiceProxy.createProxyTarget();
        proxyTarget.createPhoneOrder();
    }

}