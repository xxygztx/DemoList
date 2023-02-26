package co.zfp.mq.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RabbitMqProducerTest {

    @Autowired
    private RabbitMqProducer rabbitMqProducer;

    @Test
    void send() {
        rabbitMqProducer.send("发送消息：我的名字叫迪丽热巴");
    }

    @Test
    void sendNoQue(){
        rabbitMqProducer.sendNoQue();
    }
}