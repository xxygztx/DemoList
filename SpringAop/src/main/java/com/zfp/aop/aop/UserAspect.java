package com.zfp.aop.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class UserAspect {

    @Pointcut("execution(* com.zfp.aop.service.UserService.getClass())")
    public void CutUser(){

    }

    @Before("CutUser()")
    public void BeforeGirlFriend(){
        System.out.println("女朋友的名字是迪丽热巴");
    }
}
