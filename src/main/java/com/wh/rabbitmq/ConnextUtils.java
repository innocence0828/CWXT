package com.wh.rabbitmq;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wh.sys.PropertyUtil;

/**
 * 用于工具类创建
 */
public class ConnextUtils {
    private static final String HOST = PropertyUtil.getProperty("rabbit.host");
    //数据库密码
    private static final String PORT = PropertyUtil.getProperty("rabbit.port");
    //驱动信息
    private static final String USERNAME = PropertyUtil.getProperty("rabbit.username");
    //数据库地址
    private static final String PASSWORD = PropertyUtil.getProperty("rabbit.password");
    private static final String VIRTUALHOST = PropertyUtil.getProperty("rabbit.virtual-host");
    public static Connection getConnection() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST==null?"47.98.182.205":HOST);//设置server地址
        connectionFactory.setPort(PORT==null?5672:Integer.parseInt(PORT));
        connectionFactory.setUsername(USERNAME==null?"test":USERNAME);
        connectionFactory.setPassword(PASSWORD==null?"test":PASSWORD);
        connectionFactory.setVirtualHost(VIRTUALHOST==null?"/test":VIRTUALHOST);
        return connectionFactory.newConnection();//创建一个新的连接


    }
}
