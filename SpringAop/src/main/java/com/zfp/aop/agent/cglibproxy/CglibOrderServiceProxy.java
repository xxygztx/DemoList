package com.zfp.aop.agent.cglibproxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
@Slf4j
public class CglibOrderServiceProxy implements MethodInterceptor {

    //Cglib代理的目标对象
    private Object target;

    public Object createProxyObject(Object obj){
        this.target=obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        //回调方法的参数为代理类对象this,最后增强目标类代用 intercept方法。
        enhancer.setCallback(this);
        //增强目标类的对象
        Object o = enhancer.create();
        //返回代理对象
        return o;
    }



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("切面逻辑，目标代码每一个方法执行之前");
        //如果这里使用invoke，我们的第一个参数一定是我们本类的成员变量，如果里面传入的不是，我们应该使用invokeSuper()方法
        Object invoke = method.invoke(target, objects);
        return invoke;
    }
}
