package com.wh.service.app.impl;

import com.alibaba.fastjson.JSON;
import com.wh.dao.PushDao;
import com.wh.entity.Result;
import com.wh.service.app.PushService;
import com.wh.time.CronDateUtils;
import com.wh.time.QuartzJobOper;
import com.wh.time.QuartzManager;
import com.wh.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
@Transactional
@Service
public class PushServiceImpl implements PushService {
	@Resource
	private PushDao pushMapper;
	public static String JOB_NAME = "动态任务调度";  
	public static String TRIGGER_NAME = "动态任务触发器";  
	public static String JOB_GROUP_NAME = "XLXXCC_JOB_GROUP";  
	public static String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP"; 
	
	@Override
	public Boolean getInsertPush(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Boolean bl = true;
		try {
			 pushMapper.getBoolean(params);
			 //插入推送
			 String cron = CronDateUtils.getCron(DateUtils.smartFormat(params.get("f_Push_date").toString()));
	    	 Map<String,Object> params1 = new HashMap();
	         params1.put("f_Userid", params.get("f_Userid"));
	         Result<Map<String,Object>>  result = getselectPushAll(params1);
	         String str =  (String)params.get("f_Cid")+(String)params.get("f_Id");
	         if(result.getSuccess()){
				 QuartzManager.addJob(JSON.toJSONString(result.getList().get("undo")), str, str, str, QuartzJobOper.class, cron);
			 }
	         
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			bl = false;
//			throw new RollbackException(e.getMessage());
		}
		return bl;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result getselectPushAll(Map<String, Object> params) {
		int f_StartPage = StringUtils.isBlank((String)params.get("f_StartPage"))?0:Integer.parseInt(params.get("f_StartPage")+"");
		int f_NumPage = (10 * (f_StartPage+1))+1;
		params.put("f_StartPage", f_StartPage);
		params.put("f_NumPage", f_NumPage);
		Result<Map<String, Object>> result = new Result();
		Map<String, Object> dataMap = new HashMap();
		List<Map<String, Object>> list1 = new ArrayList();
		List<Map<String, Object>> list2 = new ArrayList();
		List<Map<String, Object>> maps =null;
		try {
			 maps = pushMapper.getselectPushAll(params);
			 for(int i=0;i<maps.size();i++){
				 if("0".equals(maps.get(i).get("F_STATIC"))){
					 list1.add(maps.get(i));
				 }else if("1".equals(maps.get(i).get("F_STATIC"))){
					 list2.add(maps.get(i));
				 }
			 }
			 dataMap.put("undo", list1);
			 dataMap.put("finished", list2);
			 result.setList(dataMap);
			 result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("查询失败");
		}
		return result;
	}

	@Override
	public Result getDeletetPushOne(Map<String, Object> params) {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Result result = new Result();
		try {
			 pushMapper.getDeletetPushOne(params);
	         System.out.println("【移除定时】开始...");    
	         QuartzManager.removeJob(params.get("f_Id")+"", params.get("f_Cid")+"", TRIGGER_NAME, TRIGGER_GROUP_NAME);    
	         System.out.println("【移除定时】成功"); 
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("删除失败");
		}
		
		return result;
	}

	@Override
	public Result getUpdatetPushOne(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Result result = new Result();
		try {
			pushMapper.getUpdatetPushOne(params);
	         System.out.println("【修改时间】开始(每5秒输出一次)...");    
	         QuartzManager.modifyJobTime(params.get("f_Id")+"", params.get("f_Cid")+"", TRIGGER_NAME, TRIGGER_GROUP_NAME, "0/5 * * * * ?");    
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMsg("更新失败");
		}
		return result;
	}

	@Override
	public Result updateTaskStatus(Map<String, Object> params) {
		Result result = new Result();
		try {
			pushMapper.updateTaskStatus(params);
			result.setSuccess(true);
			result.setMsg("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("更新失败");
		}
		
		return result;
	}



	



	
	
	

}
