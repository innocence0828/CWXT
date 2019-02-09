package com.wh.dao;

import java.util.List;
import java.util.Map;

public interface PushDao {
	
	/**
	 * 插入数据推送数据
	 * @param params
	 * @return 
	 * @return
	 */
	void getBoolean(Map<String, Object> params);
	
	
	/**
	 * 查询所有数据
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getselectPushAll(Map<String, Object> params);
	
	/**
	 * 更新任务状态
	 * @param params
	 * @return
	 */
	void updateTaskStatus(Map<String, Object> params);
	
	/**
	 * 删除一条
	 * @param params
	 * @return
	 */

	void getDeletetPushOne(Map<String, Object> params);
	
	/**
	 * 更新每一条数据
	 * @param params
	 */
	void getUpdatetPushOne(Map<String, Object> params);
	
}
