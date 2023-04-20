package com.hanburger.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CuratorCRUDTest {

    private CuratorFramework client;

    //建立连接
    @Before  //Before的作用就是，在任何的Test方法之前执行
    public void testConnect() {

        /** 使用curator操作zookeeper
         *
         connectString – 连接字符串 zkserver 地址和端口127.0.0.1:2181
         sessionTimeoutMs – 会话超时时间
         connectionTimeoutMs – 连接超时时间
         retryPolicy – 重试策略
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);
        //第一种方式
        //CuratorFramework curatorFramework =  CuratorFrameworkFactory
        //        .newClient("172.0.0.1:2181",60*1000,15*1000,retryPolicy);
        ////开启连接
        //curatorFramework.start();

        //    第二种方式
        client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(retryPolicy).namespace("hanburger")  //namespace名称空间的作用是，每次操作的时候不再需要添加根节点，默认的操作都会在这个节点中
                .build();
        client.start();
    }
    //todo ---------------------------------------------create---------------------------------------------------------------------
    /*创建节点
    1基本的创建
    2创建节点 带有参数
    3设置节点类型
    4创建多级节点 /app1/p1
    * */

    //创建节点
    @Test
    public void createTest() throws Exception {
        //  如果创建节点，没有没有指定数据，则默认将当前ip作为数据存储
        String path = client.create().forPath("/app1");
        System.out.println(path);
    }


    //创建有数据的节点
    @Test
    public void createTest2() throws Exception {
        String path = client.create().forPath("/app2","hello".getBytes());
        System.out.println(path);
    }

    //设置节点类型
    //默认：持久化节点
    @Test
    public void createTest3() throws Exception {
        String path = client.create().withMode(CreateMode.EPHEMERAL).forPath("/app3");  //使用withMode方法设置节点的值
        System.out.println(path);

        while (true);
    }

    //创建多级节点  app1/p1
    @Test
    public void createTest4() throws Exception {
        String path = client.create().creatingParentsIfNeeded().forPath("/app4/p1");  //使用creatingParentsIfNeeded方法。如果父节点不存在，则自动创建父节点
        System.out.println(path);
    }

    //todo -------------------------------------------------get-----------------------------------------------------------------
    /*查询节点
    1查询数据 get
    2查询子节点 ls /
    3查询节点状态信息 ls -s /
    * */

    //查询节点数据
    @Test
    public void getTest1() throws Exception {
        byte[] data = client.getData().forPath("/app1");  //getData可以获取节点内的数据
        System.out.println(new String(data));
    }

    //查询子节点
    @Test
    public void getTest2() throws Exception {
        List<String> paths = client.getChildren().forPath("/app4"); //getChildren可以获取
        System.out.println(paths);
    }

    //查询
    @Test
    public void getTest3() throws Exception {
        Stat status = new Stat();
        System.out.println("查询之前：" + status);
       client.getData().storingStatIn(status).forPath("/app2"); //会将状态内容封装到stat对象中去
        System.out.println("查询之后：" + status);
    }

    //todo -------------------------------------------------set-----------------------------------------------------------------

    /**
     * 修改数据
     * @throws Exception
     */
    //基本修改
    @Test
    public void setTest() throws Exception {
        client.setData().forPath("/app1","hello i am han".getBytes());
    }

    //乐观锁更改
    @Test
    public void setForVersionTest() throws Exception {
        Stat status = new Stat();
        client.getData().storingStatIn(status).forPath("/app1"); //会将状态内容封装到stat对象中去
        int version = status.getVersion();
        client.setData().withVersion(version).forPath("/app1","haha i am reset it".getBytes());   //version会自动更新
    }

    //todo -------------------------------------------------delete-----------------------------------------------------------------
    /**
     * 1.删除单个节点
     * 2.删除带有子节点的节点
     * 3.必须成功的删除
     * 4.回调
     */

    @Test
    public void deleteTest1() throws Exception {
        client.delete().forPath("/app1");
    }

    //删除带有子节点的节点
    @Test
    public void deleteTest2() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath("/app4");
    }

    //必定会删除，防止网络抖动，就是重试
    @Test
    public void deleteTest3() throws Exception {
        client.delete().guaranteed().forPath("/app4");  //guaranteed会不断的重试删除，直到删除节点为止
    }

    //回调
    @Test
    public void deleteTest4() throws Exception {
        client.delete().guaranteed().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent curatorEvent) throws Exception {
                System.out.println(client); // CuratorFramework参数对象就是client
                System.out.println(curatorEvent); //CuratorEvent参数对象是事件对象，存放事件信息
            }
        }).forPath("/app4");  //guaranteed会不断的重试删除，直到删除节点为止
    }

    @After
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
