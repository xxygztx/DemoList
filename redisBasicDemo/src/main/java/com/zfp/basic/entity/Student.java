package com.zfp.basic.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    private String name ;
    private int age ;
    private Long teacherId;
    private Long ClassId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
