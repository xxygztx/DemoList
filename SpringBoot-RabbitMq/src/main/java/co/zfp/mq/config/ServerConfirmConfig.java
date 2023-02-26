package co.zfp.mq.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

//confirm模式
@Component
public class ServerConfirmConfig implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if(ack){
            System.out.println("成功投递到broker");
        }else{
            System.out.println("投递失败");
        }
    }
}
