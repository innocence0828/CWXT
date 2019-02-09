package com.wh.dao;

import com.wh.entity.BsBookFlow;

import java.util.List;
import java.util.Map;

public interface BsBookflowDao {
	
	/**
	 *  获取所有消费条数
	 * @param
	 * @return
	 */
	Map<String, Object> getListCount(Map<String, Object> map);
	
	/**
	 * 分页获取所有信息
	 * @param map
	 * @return
	 */
	List<BsBookFlow> getListBookFlow(Map<String, Object> map);
	
}
