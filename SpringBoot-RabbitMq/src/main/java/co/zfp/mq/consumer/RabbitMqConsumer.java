package co.zfp.mq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;



@Component
public class RabbitMqConsumer {

//    @RabbitListener(queues = "queOne")
//    public void receiveMessage(String message){
//        System.out.println(message);
//    }
//    这里只有一个consumer接受到消息。
    @RabbitListener(queues = "queOne")
    public void receiveMessage1(Channel channel,Message message) throws IOException {
        try{
            System.out.println("我是接受者2"+new String(message.getBody()));
            //告诉服务器收到这条消息 无需再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

    }

}
