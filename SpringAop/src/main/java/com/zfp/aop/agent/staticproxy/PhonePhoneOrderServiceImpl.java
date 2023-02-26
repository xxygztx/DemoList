package com.zfp.aop.agent.staticproxy;



public class PhonePhoneOrderServiceImpl implements PhoneOrderService {
    @Override
    public void createPhoneOrder() {
        System.out.println("创建手机订单");
    }
}
