package com.zfp.controller;

import com.zfp.common.system.Result;
import com.zfp.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @GetMapping("/get/User")
    public Result getUser( @Valid User user){
        return Result.success(user);
    }


}
