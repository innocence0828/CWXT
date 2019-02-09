package com.wh.service.app;

import com.wh.entity.CountInfo;

import java.util.List;

public interface CountService {
	
	/**
	 * 加载统计数据
	 * @param
	 * @return
	 */
	List<CountInfo> getListCount(CountInfo countInfo);
	

	
}
