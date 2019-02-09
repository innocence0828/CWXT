package com.wh.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.concurrent.CountDownLatch;

public class Send implements Runnable{
	private final CountDownLatch countDownLatch ;
	//队列名称  
    private final static String QUEUE_NAME = "recvqueue";
    public Send(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}
    public static void main(String[] argv) throws  Exception
	{
    	CountDownLatch countDownLatch = new CountDownLatch(1);
		for(int i=2;i>0;i--){
			new Thread(new Send(countDownLatch)).start();
		}
		countDownLatch.countDown();
     }
    
 
	public void run() {
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** 
         * 创建连接连接到MabbitMQ 
         */  
			try {
				//打开连接和创建频道，与发送端一样
				Connection connection = ConnextUtils.getConnection();
				Channel channel = connection.createChannel();
            	//指定一个队列
				channel.queueDeclare(QUEUE_NAME, true, false, false, null);
					//发送的消息
				String message = "liyunwen";
				//往队列中发出一条消息
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
				//关闭频道和连接
				channel.close();
				connection.close();
		} catch (Exception e) {
		}
       
	}  
}
