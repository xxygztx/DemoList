package com.zfp.annotation.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MyBean {
    private String name="lisi";
    private int age =22;
}
