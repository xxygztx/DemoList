package com.zfp.aop.agent.jdkproxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@Slf4j
public class OrderServiceProxy implements InvocationHandler {
    //被代理的对象,目标对象
    private Object target;

    //可以将传参放在create方法和构造方法
    public void setTarget(Object target) {
        this.target = target;
    }

    //通过proxy获取动态代理的对象、

    /**
     * 该方法接收三个参数：ClassLoader对象、
     * 一个接口数组和
     * InvocationHandler对象。
     * 在调用该方法时，JDK会动态生成一个实现了指定接口的代理类，并返回一个代理对象。
     * @return
     */
    public Object createProxyTarget(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

    /**通过动态代理对象对方法进行增强
     * ，JDK会自动调用InvocationHandler中的invoke()方法，
     * 并将目标方法的调用转发给它。
     * 在invoke()方法中，可以通过反射机制调用目标方法，并在调用前后添加一些自定义的逻辑
     *
     *  @param proxy 代理对象
     *  @param method 要增强的方法（拦截的方法）
     *  @param args 方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("切面逻辑，执行目标代码每一个方法执行之前");
        //通过反射机制
        Object invoke = method.invoke(target, args);
        log.info("切面逻辑，执行目标代码灭一个方法执行之后");
        return invoke;
    }
}
