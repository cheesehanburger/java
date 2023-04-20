package com.hanburger.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.List;

public class CuratorWatcherTest {

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


    /*
    ZooKeeper 允许用户在指定节点上注册一些Watcher，并且在一些特定事件触发的时候，
    ZooKeeper 服务端会将事件通知到感兴趣的客户端上去，该机制是 ZooKeeper 实现分布式协调服务的重要特性
     */

    /**
     * NodeCache:给某个特定的节点注册监听器
     */

    @Test
    public void testNodeCache() throws Exception {
        //    1 创建NodeCache的对象
        NodeCache nodeCache = new NodeCache(client, "/app1");
        //    2 注册监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点发生变化了");
                //    获取修改节点后的数据
                byte[] newData = nodeCache.getCurrentData().getData();
                System.out.println(new String(newData));
            }
        });
        //    3 开启监听 如果设置为true则加载缓冲数据
        nodeCache.start(true);

        //保证监听方法不会取消
        while (true) ;
    }

    /**
     * pathChildrenCache:给某个节点的所有子节点（并不会监听到本节点）
     */


    @Test
    public void testNodeCache2() throws Exception {
        //    1 创建NodeCache的对象
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/app2", true);
        //    2 注册监听
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("子节点变化了");
                System.out.println(event);
                //    监听子节点的数据变更，并且拿到变更后的数据
                // 1 获取事件类型
                PathChildrenCacheEvent.Type type = event.getType();
                // 2 判断事件类型是不是update
                if (type == PathChildrenCacheEvent.Type.CHILD_UPDATED) {
                    byte[] newData = event.getData().getData();
                    System.out.println("子节点的内容发生了更改");
                    System.out.println(newData);
                }
            }
        });
        //    3 开启监听 如果设置为true则加载缓冲数据
        pathChildrenCache.start(true);

        //保证监听方法不会取消
        while (true) ;
    }

    /**
     * TreeCache:给某个节点本身和所有子节点
     */

    @Test
    public void testNodeCache3() throws Exception {
        //    1 创建NodeCache的对象
        TreeCache treeCache = new TreeCache(client,"/app4");
        //    2 注册监听
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("节点或子节点发生变化");
                System.out.println(event);
                TreeCacheEvent.Type type = event.getType();
                if (type == TreeCacheEvent.Type.NODE_UPDATED) {
                    System.out.println("数据发生变化");
                    byte[] newData =  event.getData().getData();
                    System.out.println(new String(newData));
                }
            }
        });

        //    3 开启监听
        treeCache.start();
        //保证监听方法不会取消
        while (true) ;
    }

    @After
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
