package com.zfp.aop.reflect;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyClassTest {

    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<MyClass> myClassClass = MyClass.class;
        Method test = myClassClass.getMethod("test", String.class);
        Object lisi = test.invoke(new MyClass(), "lisi");
        System.out.println(lisi);
    }

    @Test
    public void testReturn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<MyClass> myClassClass = MyClass.class;
        Method testReturn = myClassClass.getMethod("testReturn", String.class, int.class);
        Object lisis = testReturn.invoke(new MyClass(), "lisis", 33);
        System.out.println(lisis);
    }

}