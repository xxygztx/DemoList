package com.zfp.aop.reflect;

public class MyClass {

    public void test(String s){
        System.out.println(s);
    }

    public int testReturn(String param,int code){
        System.out.println(param);
        return code;
    }
}
