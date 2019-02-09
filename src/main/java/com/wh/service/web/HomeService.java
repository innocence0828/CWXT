package com.wh.service.web;

import java.util.List;
import java.util.Map;

public interface HomeService {
	
	/**
	 * 获取主页头部四大信息数据
	 */
	Map<String,Object> getheadFourDatas(Map<String, Object> params);
	
	/**
	 * 收入与支出安月查询
	 */
	Map<String,Object> getMonthInOut(Map<String, Object> params);
	
	/**
	 * 每月消费类型
	 */
	List<Map<String,Object>> getConsumeType(Map<String, Object> params);

	/**
	 * 每年收入支出
	 */
	List<Map<String,Object>> getInOutType(Map<String, Object> params);

	
	

	
}
