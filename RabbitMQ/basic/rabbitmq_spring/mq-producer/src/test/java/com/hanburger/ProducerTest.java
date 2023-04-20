package com.hanburger;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {
//    1 注入RabbitTamplate
    @Autowired
    private RabbitTemplate rabbitTemplate;


    //发送基本消息
    @Test
    public void testHelloWorld() {
        rabbitTemplate.convertAndSend("spring_queue","hello spring !!!");
    }

    //发送Fanout消息
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","hello Spring fanout");
    }

    //发送Topic消息
    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("spring_topic_exchange","heima.hehe.haha","hello Spring topic");
    }
}
