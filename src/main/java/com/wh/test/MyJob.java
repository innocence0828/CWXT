package com.wh.test;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob{
 
    public void execute() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        //每调度一次打印时间
        System.out.println("作业调度："+date);

 
    }
 
}
