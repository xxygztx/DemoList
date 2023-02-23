package com.zfp.annotation.controller;

import com.zfp.annotation.ConfigurationProperties.MyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class UsageController {

    @Resource
    private MyData myData;

    @GetMapping("/usage/first")
    public String usageFirst(){
        log.info(myData.getName());
        log.info(myData.getPassword());
        return myData.getName()+"____"+myData.getPassword();
    }

}
