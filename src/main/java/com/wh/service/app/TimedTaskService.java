package com.wh.service.app;

import com.wh.entity.BsBookFlow;

import java.sql.SQLException;
import java.util.List;

public interface TimedTaskService {
	
	/**
	 * 定时任务插入
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
	 * 插入另一个数据库表
	 * @param
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	void dbwhjtTestAddAllData(List<BsBookFlow> basBookFlows) throws SQLException, Exception;

	/**
	 * 清理没有数据图片
	 */
	void cleanPhoto();
}
