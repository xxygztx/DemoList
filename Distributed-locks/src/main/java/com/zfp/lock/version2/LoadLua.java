package com.zfp.lock.version2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class LoadLua {

    @Bean
    public RedisScript redisScript(){
        DefaultRedisScript<Boolean> booleanDefaultRedisScript = new DefaultRedisScript<>();
        booleanDefaultRedisScript.setResultType(Boolean.class);
        booleanDefaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("delete.lua")));
        return booleanDefaultRedisScript;
    }
}
