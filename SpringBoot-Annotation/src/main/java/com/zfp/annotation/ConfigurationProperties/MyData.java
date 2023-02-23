package com.zfp.annotation.ConfigurationProperties;

import com.sun.prism.Texture;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Data
@Import(UsageFirst.class)
public class MyData {

    private String name;
    private String password;

    @Bean
    public MyData setMyData(UsageFirst usageFirst){
        this.name= usageFirst.getName();
        this.password=usageFirst.getPassword();
        return this;
    }
}
