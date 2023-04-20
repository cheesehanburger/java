package com.hanburger.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class TopicListenerWell implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //message.getBody()获取的是消息
        System.out.println(new String(message.getBody()));
    }
}
