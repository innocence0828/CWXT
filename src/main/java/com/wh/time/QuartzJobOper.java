package com.wh.time;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wh.rabbitmq.ConnextUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuartzJobOper implements Job {
    //队列名称
    private final static String QUEUE_NAME = "recvqueue";

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println(new Date() + "李云文: job 1 doing something...");
        String pushId = jobExecutionContext.getTrigger().getJobKey().getName();
        String str = jobExecutionContext.getTrigger().getKey().getName();
        String cid = str.substring(0, 32);
        //移除定时任务
        QuartzManager.removeJob(pushId, str, str, str);
        inDate(pushId+"--"+cid);


    }
    //rabbitmq向服务发送请求
    private void inDate(String message)  {
        //打开连接和创建频道，与发送端一样
        Connection connection = null;
        try {
            connection = ConnextUtils.getConnection();
            Channel channel = connection.createChannel();
            //指定一个队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            //发送的消息
            //往队列中发出一条消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            //关闭频道和连接
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}