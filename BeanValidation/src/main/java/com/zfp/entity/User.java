package com.zfp.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    @NotEmpty(message = "用户名必须填写")
    private String username;

    @NotEmpty(message ="密码必须填写" )
    @Length(min=6,max =14,message ="密码必须是6-18位字符" )
    private String password;

    private String phone;

    private String code;
}
