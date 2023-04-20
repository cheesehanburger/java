package com.hanburger.pubsub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_Topic2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2. 设置参数
        factory.setHost("127.0.0.1");//ip  默认值 localhost
        factory.setPort(5672); //端口  默认值 5672
        factory.setVirtualHost("/hanburger");//虚拟机 默认值/
        factory.setUsername("hanburger");//用户名 默认 guest
        factory.setPassword("hanburger");//密码 默认值 guest
        //3. 创建连接 Connection
        Connection connection = factory.newConnection();
        //4. 创建Channel
        Channel channel = connection.createChannel();

        String queueName1 = "test_topic_queues1";
        String queueName2 = "test_topic_queues2";

        //6.接受消息
        /*
         参数:
         queue – 队列名称
         autoAck – 是否自动确认
         callback – 回调对象
         */
        Consumer consumer = new DefaultConsumer(channel) {
            /*
              回调方法，收到消息后会自动执行改方法
              consumerTag – 标识
              envelope – 获取消息中的信息，如交换机，路由key等
              properties – 配置信息
              body – 数据
            */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body: " + new String(body));
                System.out.println("将日志信息打印到控制台....");
            }
        };
        channel.basicConsume(queueName2,true,consumer);

    //    注意；消费者无需关闭资源
    }
}
