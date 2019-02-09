package com.wh.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
 
 
 
public class Demo implements Runnable{
	private final CountDownLatch countDownLatch ;
	RestTemplate restTemplate = new RestTemplate();
	public Demo(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}
	public void run() {
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//do
		ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8080/items/queryitem.action", String.class);
		System.out.println(result);
	}
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		for(int i=300;i>0;i--){
			new Thread(new Demo(countDownLatch)).start();
		}
		countDownLatch.countDown();
	}
}
