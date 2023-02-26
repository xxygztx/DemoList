package com.zfp.aop.agent.staticproxy;

public class PhoneOrderServiceProxy implements PhoneOrderService {

    private PhoneOrderService phoneOrderService;
    public PhoneOrderServiceProxy(PhoneOrderService orderService){
        this.phoneOrderService=orderService;
    }
    @Override
    public void createPhoneOrder() {
        System.out.println("创建手机订单");
    }
}
