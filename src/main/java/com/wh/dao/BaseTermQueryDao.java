package com.wh.dao;

import java.util.List;
import java.util.Map;

public interface BaseTermQueryDao {
	
	/**
	 * 加载数据
	 * @param
	 * @return
	 */
	List<Map<String,Object>> getTermQuery();
	
}
