package com.wh.service.app;

import com.wh.entity.Result;

import java.util.Map;

public interface PushService {
	

	/**
	 * 插入推送数据
	 * @param params
	 * @return
	 */
	Boolean getInsertPush(Map<String, Object> params);
	
	/**
	 * 查询全部或者查询隔壁
	 * @param params
	 * @return
	 */
	Result getselectPushAll(Map<String, Object> params);

	/**
	 * 更新任务状态
	 * @param params
	 * @return
	 */
	Result updateTaskStatus(Map<String, Object> params);
	
	
	/**
	 * 删除每一行
	 * @param params
	 * @return
	 */
	Result getDeletetPushOne(Map<String, Object> params);
	
	/**
	 * 更新每一行代码
	 * @param params
	 * @return
	 */
	Result getUpdatetPushOne(Map<String, Object> params);
	

	
}
