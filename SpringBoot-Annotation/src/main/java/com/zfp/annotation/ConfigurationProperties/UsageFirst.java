package com.zfp.annotation.ConfigurationProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "zfp.usage.first")
public class UsageFirst {
    private String name;
    private String password;



}
