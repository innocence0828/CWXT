package com.wh.service.web;

import com.wh.entity.PagerResult;
import com.wh.entity.BsBookFlow;

import java.util.List;
import java.util.Map;

public interface BsBookflowService {
	
	/**
	 * 获取所有消费条数
	 */
	PagerResult<BsBookFlow> getBookflowCount(Map<String, Object> params);
	
	/**
	 * 分页获取所有信息
	 */
	List<BsBookFlow> getListBookFlow(Map<String, Object> params);
	

	
}
