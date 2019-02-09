package com.wh.dao;

import java.util.List;

public interface TimedTaskDao {
	
	/**
	 * 定时任务添加
	 * @param
	 * @return
	 */
	void copyAddAllData();
	
	/**
	 * 删除表
	 * @param
	 * @return
	 */
	void copyDeleteBookFlows();
	
	/**
	 * 插入另一张数据库表里
	 */
	
	void insertToTestDB();



	/**
	 * 加载所有每天流水数据
	 */
	List<String>  loadBookFlowsData();

	/**
	 * 加载每天用户数据
	 * @return
	 */
	List<String> loadUserInfo();
}
