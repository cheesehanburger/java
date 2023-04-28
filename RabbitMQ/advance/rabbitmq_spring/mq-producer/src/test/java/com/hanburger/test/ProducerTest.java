package com.hanburger.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     *  确认模式：消息从 producer 到 exchange 则会返回一个 confirmCallback 。
     *   1 开启确认模式     publisher-confirms="true"
     *   2 在rabbitTemplate定义ConfirmCallback
     *
     * */
    @Test
    public void testComfirm() {
        //定义回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData 相关配置信息
             * @param ack   exchange交换机 是否成功收到了消息。true 成功，false代表失败
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    System.out.println("成功收到消息" + cause);
                } else {
                    System.out.println("消息失败了" + cause);
                }
            }

        });
        // 发送消息
        rabbitTemplate.convertAndSend("test_exchange_confirm", "test_queue_confirm", "hello");
    }

    /*回退模式： 当消息发送给Exchange后，Exchange路由到Queue失败是 才会执行 ReturnCallBack
    1. 开启回退模式:publisher-returns="true"
    2. 设置ReturnCallBack
    3. 设置Exchange处理消息的模式:
            如果消息没有路由到Queue，则丢弃消息（默认）
            如果消息没有路由到Queue，返回给消息发送方ReturnCallBack
    */
    @Test
    public void testReturn() {
        //设置交换机处理失败消息的模式
        rabbitTemplate.setMandatory(true);   //不设置处理模式将会自动丢弃
        //定义回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {

            /**
             *
             * @param message   消息对象
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange  交换机
             * @param routingKey 路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("return 执行了");
                System.out.println(message);
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routingKey);
            }
        });
        // 发送消息
        rabbitTemplate.convertAndSend("test_exchange_confirm", "test_queue_confirm1", "hello");
    }

    @Test
    public void testSend() {
        for (int i = 0; i < 10; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend("test_exchange_confirm", "test_queue_confirm", "hello");
        }
    }

    /**
     * TTL:过期时间
     * 1. 队列统一过期
     * <p>
     * 2. 消息单独过期
     * <p>
     * <p>
     * 如果设置了消息的过期时间，也设置了队列的过期时间，它以时间短的为准。
     * 队列过期后，会将队列所有消息全部移除。
     * 消息过期后，只有消息在队列顶端，才会判断其是否过期(移除掉)
     */
    @Test
    public void testTTL() {
        //消息的后处理对象,可以设置一些消息的参数信息
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000"); //消息的过期时间
                return message;
            }
        };
        //消息单独过期
        //rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl.hehe", "message ttl....",messagePostProcessor);

        for (int i = 0; i < 10; i++) {
            if(i == 5){
                //消息单独过期（由于不在队顶，所以最终并不会单独过期，而是和队列一同过期）
                rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl.hehe", "message ttl....",messagePostProcessor);
            }else{
                //不过期的消息
                rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl.hehe", "message ttl....");

            }

        }
    }

    //测试死信
    @Test
    public void testDLX() {
        //测试过期导致的死信
        //rabbitTemplate.convertAndSend("test_exchange_dlx", "test.tlx.hello", "我是一条消息，我会成为死信吗");
        //测试超出队列导致的死信
        //for (int i = 0; i < 20; i++) {
        //    rabbitTemplate.convertAndSend("test_exchange_dlx", "test.tlx.hello", "我是一条消息，我会成为死信吗");
        //}

        //测试消息拒收导致的死信
        rabbitTemplate.convertAndSend("test_exchange_dlx", "test.tlx.hello", "我是一条消息，我会成为死信吗");
    }

    @Test
    public void testDelay() throws InterruptedException {
        rabbitTemplate.convertAndSend("order_exchange","order.msg","订单信息：id=1,time=2019年8月17日16:41:47");
        //2.打印倒计时10秒
        for (int i = 10; i > 0 ; i--) {
            System.out.println(i+"...");
            Thread.sleep(1000);
        }
    }
}
