package com.wh.dao;
import java.util.List;
import java.util.Map;

public interface HomeDao {
	
	/**
	 * 获取主页头部四大数据
	 * @param params
	 * @return
	 */
	Map<String, Object> getheadFourDatas(Map<String, Object> params);
	
	/**
	 * 每个月收入和支出
	 * @param params
	 * @return
	 */
	Map<String, Object> getMonthInOut(Map<String, Object> params);
	
	/**
	 * 消费类型
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getConsumeType(Map<String, Object> params);

	/**
	 * 按年查询收入支出
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getInOutType(Map<String, Object> params);

	
	
}
