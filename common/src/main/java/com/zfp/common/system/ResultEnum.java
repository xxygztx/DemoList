package com.zfp.common.system;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(200,"成功"),
    PARAMETER_ERROR(400,"传参不合法")
    ;
    private int code;
    private String message;
    private ResultEnum(int code,String message){
        this.code=code;
        this.message=message;
    }


}
