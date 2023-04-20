package com.hanburger.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String QUEUE_NAME = "boot_queue";

//    1. 交换机
    @Bean("bootExchange")
    public Exchange declareExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }
//    2. 队列
    @Bean("bootQueue")
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }


//    3. 队列和交换机绑定
    @Bean
    public Binding bindQueueExchange(@Qualifier("bootExchange") Exchange exchange, @Qualifier("bootQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
