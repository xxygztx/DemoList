package com.zfp.aop.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MarkGirlFriend {

    String value() default "刘亦菲";
}
