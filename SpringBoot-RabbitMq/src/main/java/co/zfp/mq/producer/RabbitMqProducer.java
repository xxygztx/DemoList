package co.zfp.mq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMqProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
//        rabbitTemplate.convertAndSend("","","我是发送者");
        rabbitTemplate.convertAndSend("","queOne",message);
    }

    public void sendNoQue(){
        rabbitTemplate.convertAndSend("","que","发送的没有队列接受");
    }
}
