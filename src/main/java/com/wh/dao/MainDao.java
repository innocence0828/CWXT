package com.wh.dao;

import com.wh.entity.BaBusinessdct;
import com.wh.entity.BsBookFlow;
import com.wh.entity.Pagination;
import com.wh.entity.TotalPaginationitem;

import java.util.List;
import java.util.Map;

public interface MainDao {
	
	/**
	 * 同步
	 * @param map
	 */
	void addALL(Map<String, Object> map);
	/**
	 * 添加信息
	 * @param bookFlow
	 */
	void addBookFlow(BsBookFlow bookFlow);
	/**
	 * 更新
	 * @param bookFlow
	 * @return
	 */
	boolean updatebookFlow(BsBookFlow bookFlow);
	/**
	 * 删除
	 * @param f_Id
	 * @return
	 */
	boolean deleteBookflowf_Id(String f_Id);
	/**
	 * 查询单个
	 * @param f_Id
	 * @return
	 */
	BsBookFlow getBookflowf_Id(String f_Id);
	/**
	 * 查询所有
	 * @return
	 */
	List<BsBookFlow> getAllBookFlow();
	
	/**
	 * 删除用户对应bookFlows表
	 * @param fUserId
	 */
	void deleteBookFlows(String fUserId);
	
	/**
	 * 分页查询和日期收索
	 * @param pagination
	 * @return
	 */
	List<BsBookFlow> getListBookFlowsPage(Pagination pagination);
	
	/**
	 * 总条数和总价格
	 * @param pagination
	 * @return
	 */
	TotalPaginationitem getTotalItemPrice(Pagination pagination);
	
	/**
	 * 查询字典表
	 * @param str
	 * @return
	 */
	List<BaBusinessdct> getZdb(String str);
	
	
}
