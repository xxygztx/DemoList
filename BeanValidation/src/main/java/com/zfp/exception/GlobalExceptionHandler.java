package com.zfp.exception;

import com.zfp.common.system.Result;
import com.zfp.common.system.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public Result validationExceptionHandler(BindException e){
        log.info("getMessage:"+e.getMessage());
        log.info("getObjectName"+e.getObjectName());
        log.info("getBindingResult"+e.getBindingResult());
        return Result.fail(ResultEnum.PARAMETER_ERROR.getCode(),
                e.getObjectName()+ResultEnum.PARAMETER_ERROR.getMessage());
    }
}
