package com.zfp.aop.annotation;

import com.zfp.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class getAnnotation {
    /**
     * 我们要获取一个类上的注解，直接通过反射获取这个类，在获取注解就可以
     * 如果我们要获取一个方法的注解，我们要先获取这个类的方法，再获取这个方法的注解
     */
    public void get(){
        Class<UserService> userServiceClass = UserService.class;
        Method[] declaredMethods = userServiceClass.getDeclaredMethods();
        for(Method method:declaredMethods){
            if(method.isAnnotationPresent(MarkGirlFriend.class)){
                MarkGirlFriend annotation = method.getAnnotation(MarkGirlFriend.class);
                String value = annotation.value();
                log.info(value);
            }
        }
    }
}
