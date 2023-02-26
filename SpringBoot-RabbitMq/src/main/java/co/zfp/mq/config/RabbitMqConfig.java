package co.zfp.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitMqConfig {

    @Resource
    private ServerConfirmConfig serverConfirmConfig;
    @Resource
    private ServerReturnConfig serverReturnConfig;

    @Resource
    private CachingConnectionFactory cachingConnectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(serverConfirmConfig);
        rabbitTemplate.setReturnsCallback(serverReturnConfig);
        return rabbitTemplate;
    }

    @Bean
    public Queue queue1(){
        return new Queue("queOne");
    }
}
