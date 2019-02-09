package com.wh.service.app;

import com.wh.entity.BaBusinessdct;
import com.wh.entity.BsBookFlow;
import com.wh.entity.Pagination;
import com.wh.entity.TotalPaginationitem;

import java.util.List;


public interface MainService {
	
	
	
	/**
	 * 同步当前用户消费信息
	 * @param bookFlows
	 * @param fUserId
	 */
	void addAll(List<BsBookFlow> bookFlows, String fUserId);
	
	/**
	 * 添加每BS_BOOKFLOW表中每条数据
	 * @param bookFlow
	 */
	void addBookFlow(BsBookFlow bookFlow);
	
	/**
	 * 更新数据
	 * @param bookFlow
	 * @return
	 */
	boolean updatebookFlow(BsBookFlow bookFlow);
	/**
	 * 删除每BS_BOOKFLOW表中每条数据
	 * @param f_Id
	 * @return
	 */
	boolean deleteBookflowf_Id(String f_Id);
	/**
	 * 
	 * 查询每BS_BOOKFLOW表中每条数据
	 * @param f_Id
	 * @return
	 */
	BsBookFlow getBookflowf_Id(String f_Id);
	/**
	 *  查询每BS_BOOKFLOW表中所有数据
	 * @return
	 */
	 
	List<BsBookFlow> getAllBookFlow();
	
	
	/**
	 * 删除用户对应bookFlows表
	 * @param fUserId
	 */
	void deleteBookFlows(String fUserId);

	/**
	 * 主要加载分页和日期搜索
	 * @param pagination 
	 * @return
	 */
	List<BsBookFlow> getListBookFlow(Pagination pagination);
	
	/**
	 * 加载总条数，和总价格
	 * @param pagination 
	 * @return
	 */
	TotalPaginationitem getTotalItemPrice(Pagination pagination);
	
	/**
	 * 查询字典表
	 * @param str 
	 * @param
	 * @return
	 */
	List<BaBusinessdct> getZdb(String str);

	
}
