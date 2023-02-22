package com.zfp.redission;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("zfp.redisson")
@Data
public class RedissionProperties {

    private String address;
}
