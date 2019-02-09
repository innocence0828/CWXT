package com.wh.sys;

import java.util.Properties;

public class Config {
	
	/**
	 * 系统物理路径
	 */
	public static String RealPath;
	
	private static Properties props=new Properties();
	
	public static synchronized void init(Properties properties){
		props = properties;
	}

	public static final String getProperty(String key){
		return props.getProperty(key);
	}
	
	public static void setProperty(String key,String value){
		props.setProperty(key, value);
	}
}
