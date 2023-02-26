package com.zfp.aop.aop;

import com.zfp.aop.annotation.MarkGirlFriend;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class MarkAspect {

    //切入点是注解
    @Pointcut("@annotation(com.zfp.aop.annotation.MarkName)")
    public  void cutMark(){

    }

    @Before("cutMark()")
    public void getName(){
        System.out.println("my name is lisi");
    }

    @Pointcut("@annotation(com.zfp.aop.annotation.MarkGirlFriend))")
    public void cutGirlFriend(){

    }
    @Before("cutGirlFriend()")
    public void setGirlName(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MarkGirlFriend annotation = method.getAnnotation(MarkGirlFriend.class);
        System.out.println(annotation.value());
    }

}
