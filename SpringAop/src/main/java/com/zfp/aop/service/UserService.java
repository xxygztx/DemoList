package com.zfp.aop.service;

import com.zfp.aop.annotation.MarkGirlFriend;
import com.zfp.aop.annotation.MarkName;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void myName(){
        System.out.println("我的名字叫小芳");
    }


    @MarkName
    public void markName(){

    }
    @MarkGirlFriend(value = "古力娜扎")
    public void markGirlFriendName(){

    }
}
