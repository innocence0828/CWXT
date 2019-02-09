package com.wh.utils;

import java.sql.DriverManager;
import java.sql.Connection;
import com.wh.sys.PropertyUtil;

public class JDBCConnFacory {
////数据库用户名  
//private static final String USERNAME = "root";  
////数据库密码  
//private static final String PASSWORD = "liyunwen0506";  
////驱动信息   
//private static final String DRIVER = "com.mysql.jdbc.Driver";  
//////数据库地址  
//private static final String URL = "jdbc:mysql://47.95.215.33:3306/dbwhjt_test?autoReconnect=true";  
	private static final String USERNAME = PropertyUtil.getProperty("jdbc.username");
    //数据库密码  
    private static final String PASSWORD = PropertyUtil.getProperty("jdbc.password");
    //驱动信息   
    private static final String DRIVER = PropertyUtil.getProperty("driverClassName");
    //数据库地址  
    private static final String URL = PropertyUtil.getProperty("jdbc.url");
    public static Connection connection;
    private JDBCConnFacory(){

    }
    static{
        try {
        	Class.forName(DRIVER);
			System.out.println("数据库连接成功！");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
    
    
}

