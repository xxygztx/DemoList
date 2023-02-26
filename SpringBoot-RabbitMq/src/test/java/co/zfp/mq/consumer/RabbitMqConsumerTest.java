package co.zfp.mq.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RabbitMqConsumerTest {

    @Autowired
    private RabbitMqConsumer rabbitMqConsumer;


}