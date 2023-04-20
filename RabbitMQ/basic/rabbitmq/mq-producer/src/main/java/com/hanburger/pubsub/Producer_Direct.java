package com.hanburger.pubsub;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer_Direct {
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
        //5. 创建交换机exchange
         /*
        参数
        exchange – 交换机名称
        type – 交换机类型
                Fanout：广播，将消息交给所有绑定到交换机的队列
                Direct：定向，把消息交给符合指定routing key 的队列
                Topic：通配符，把消息交给符合routing pattern（路由模式）的队列
        durable – 持久化
        autoDelete – 自动删除
        internal – 一般设置为false
        arguments – 参数
         */
        String exchangeName = "test_direct";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true,false,false,null);
        //6. 创建队列
        String queueName1 = "test_direct_queues1";
        String queueName2 = "test_direct_queues2";
        channel.queueDeclare(queueName1, true, false, false, null);
        channel.queueDeclare(queueName2, true, false, false, null);
        //7. 绑定交换机和队列
        /*
        参数：
            1. queue：队列名称
            2. exchange：交换机名称
            3. routingKey：路由键，绑定规则
                 如果交换机的类型为fanout ，routingKey设置为""
         */

        //队列一的绑定 error
        channel.queueBind(queueName1,exchangeName,"error");
        //队列二的绑定 info error warning
        channel.queueBind(queueName2,exchangeName,"info");
        channel.queueBind(queueName2,exchangeName,"warning");
        channel.queueBind(queueName2,exchangeName,"error");
        //8. 发送消息
        String body = "日志：张三调用了findAll方法...日志级别：info";
        channel.basicPublish(exchangeName,"error",null,body.getBytes());
        //9. 释放资源
        channel.close();
        connection.close();
    }

}
