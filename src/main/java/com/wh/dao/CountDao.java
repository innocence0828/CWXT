package com.wh.dao;

import com.wh.entity.CountInfo;

import java.util.List;

public interface CountDao {
	
	/**
	 * 加载数据
	 * @param countInfo
	 * @return
	 */
	List<CountInfo> getListCount(CountInfo countInfo);
	
}
