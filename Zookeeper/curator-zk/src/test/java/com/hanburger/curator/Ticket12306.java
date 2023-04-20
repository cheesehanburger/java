package com.hanburger.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

//模拟12306购票
public class Ticket12306 implements Runnable {

    private int tickets = 10;  //数据库中的票数

    private InterProcessMutex lock;


    public Ticket12306() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        lock = new InterProcessMutex(client, "/lock");   //参数1是当前的客户端对象，参数2是保存锁数据的地址
    }

    @Override
    public void run() {

        while (true) {
            //获取锁
            try {
                lock.acquire(3, TimeUnit.SECONDS);  //等待锁的时间
                if (tickets > 0) {
                    System.out.println(Thread.currentThread() + ":" + tickets);
                    //Thread.sleep(100);
                    tickets--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
