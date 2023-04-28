package com.hanburger.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;


@Component
public class DlxListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //1.接受消息
            System.out.println(new String(message.getBody()));
            //2.处理业务逻辑
            System.out.println("处理业务逻辑");
            int i = 3/0;  //模拟处理业务中出现异常
            //3.手动签收
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            //4.拒绝签收
            /*
            第三个参数：requeue：重回队列。如果设置为true，则消息重新回到queue，broker会重新发送该消息给消费端
             */
            System.out.println("出现异常拒绝签收");
            channel.basicNack(deliveryTag,true,false);
        }
    }
}
