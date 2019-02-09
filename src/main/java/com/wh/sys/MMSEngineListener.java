package com.wh.sys;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class MMSEngineListener  implements ServletContextListener{
	 
	 
	 /**  
	  * 在Web应用启动时初始化任务  
	  */  
	 public void contextInitialized(ServletContextEvent event) {   

	 }   
	 
	 /**  
	  * 在Web应用结束时停止任务  ServletContextEvent
	  */  
	 public void contextDestroyed(ServletContextEvent event) {  
		 ServletContext servletContext = event.getServletContext();
		Config.RealPath=servletContext.getRealPath("/").replace("\\", "/");
		 
	 }   	
	 
}
