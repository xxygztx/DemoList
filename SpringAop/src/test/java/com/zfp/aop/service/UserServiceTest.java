package com.zfp.aop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void myName() {
        userService.myName();
    }

    @Test
    void markName(){
        userService.markName();
    }

    @Test
    void markGirlFriend(){
        userService.markGirlFriendName();
    }
}