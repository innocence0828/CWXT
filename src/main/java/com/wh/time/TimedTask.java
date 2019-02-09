package com.wh.time;

import com.wh.dao.BaseTermQueryDao;
import com.wh.service.app.PushService;
import com.wh.service.app.TimedTaskService;
import com.wh.service.app.UserService;
import com.wh.utils.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的定时器
 * @author hj
 */
@Component
public class TimedTask {
	@SuppressWarnings("unused")
	@Autowired
	private UserService userService;
	@Autowired
	private TimedTaskService taskService;
	
	@Autowired
	private BaseTermQueryDao userDao;
	@Autowired
	private PushService pushService;

	/** 
     * 定时计算。每天凌晨3点 执行一次 
     */  
    @Scheduled(cron = "0 0 3 * * ?") 
	public void show(){
    	try {
			//删除用户对应bookFlows表
			taskService.copyDeleteBookFlows();
			//插入有信息
	    	taskService.copyAddAllData();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
	}

    /** 
     * 心跳更新。启动时执行一次，之后每隔6H执行一次 
     */  
    @Scheduled(fixedRate = 1000*21600) 
	public void print(){
    	
    	System.out.println("6小时");
    	try {
    		DbUtils.queryForMap("select 1", null);    
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("end---6小时");
	}


	/**
	 * 每天凌晨4点 清理所有数据库数据的图片
	 */
	@Scheduled(cron = "0 0 4 * * ?")
	public void cleanPhoto(){
		try {
			//删除用户对应bookFlows表
			taskService.cleanPhoto();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}

	}
    
    /** 
     * 心跳更新。启动时执行一次，之后每隔6H执行一次 
     */  
//    @Scheduled(fixedRate = 1000*200) 
//    public void print2(){
//    	  Map<String,Object> params = new HashMap();
//          params.put("f_Id", "");
//          Result<Map<String,Object>>  result = pushService.getselectPushAll(params);
//    	QuartzManager quartzManager = new QuartzManager();
//          if(result.getSuccess())
//    	 QuartzManager.addJob(JSON.toJSONString(result.getList()), "test", "test", "test", QuartzJobOper.class, "0/10 * * * * ?");
//        try {  
//            System.out.println("【系统启动】开始(每1秒输出一次 job2)...");    
//
//            Thread.sleep(5000); 
//            System.out.println("【增加job1启动】开始(每1秒输出一次)...");  
////            quartzManager.addJob("test", "test", "test", "test", QuartzJobOper.class, "0/1 * * * * ?");
//
//            Thread.sleep(5000);    
//            System.out.println("【修改job1时间】开始(每2秒输出一次)...");    
////            quartzManager.modifyJobTime("test", "test", "test", "test", "0/2 * * * * ?");    
//
//            Thread.sleep(10000);    
//            System.out.println("【移除job1定时】开始...");    
////            quartzManager.removeJob("test", "test", "test", "test");    
//
//            // 关掉任务调度容器
//            // quartzManager.shutdownJobs();
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }
}