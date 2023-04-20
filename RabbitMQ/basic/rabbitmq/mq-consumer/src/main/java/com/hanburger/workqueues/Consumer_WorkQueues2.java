package com.hanburger.workqueues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_WorkQueues2 {
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
        //5. 创建队列Queue
        /*
        queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        参数：
            1. queue：队列名称
            2. durable:是否持久化，当mq重启之后，还在
            3. exclusive：
                * 是否独占。只能有一个消费者监听这队列
                * 当Connection关闭时，是否删除队列
                *
            4. autoDelete:是否自动删除。当没有Consumer时，自动删除掉
            5. arguments：参数。

         */
        //如果没有一个名字叫hello_world的队列，则会创建该队列，如果有则不会创建
        channel.queueDeclare("work_queues", true, false, false, null);
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
            }
        };
        channel.basicConsume("work_queues",true,consumer);

    //    注意；消费者无需关闭资源
    }
}