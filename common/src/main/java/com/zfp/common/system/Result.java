package com.zfp.common.system;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String message;
    private T Data;

    public static<T> Result success(){
        Result result =new Result();
        result.setCode(200);
        result.setMessage("成功");
        return  result;
    }

    public static<T> Result success(T data){
        Result result =new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return  result;
    }
    public static<T> Result fail(String message){
        Result result = new Result();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    public static <T> Result success(ResultEnum resultEnum,T data){
        Result result =new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(data);
        return result;
    }



}


