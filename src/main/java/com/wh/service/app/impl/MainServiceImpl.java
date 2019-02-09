package com.wh.service.app.impl;

import com.wh.dao.MainDao;
import com.wh.entity.BaBusinessdct;
import com.wh.entity.BsBookFlow;
import com.wh.entity.Pagination;
import com.wh.entity.TotalPaginationitem;
import com.wh.service.app.MainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class MainServiceImpl implements MainService {
	
	@Resource
	private MainDao mapper;
	
	/**
	 * 删除每BS_BOOKFLOW表中每条数据
	 */
	public boolean deleteBookflowf_Id(String id) {
		
		return mapper.deleteBookflowf_Id(id);
	}
	
	/**
	 * 查询每BS_BOOKFLOW表中所有数据
	 */
	public List<BsBookFlow> getAllBookFlow() {
		List<BsBookFlow> findAllList = mapper.getAllBookFlow();
		return findAllList;
	}

	/**
	 * 查询每BS_BOOKFLOW表中每条数据
	 */
	public BsBookFlow getBookflowf_Id(String f_Id) {

		BsBookFlow bookFlow = mapper.getBookflowf_Id(f_Id);
		
		return bookFlow;
	}
	
	/**
	 * 插入每BS_BOOKFLOW表中所有数据
	 */
	public void addAll(List<BsBookFlow> bookFlows,String f_UserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", bookFlows);
		map.put("f_UserId", f_UserId);
		mapper.addALL(map);
	}
	
	/**
	 * 更新每BS_BOOKFLOW表中每条数据
	 */
	public boolean updatebookFlow(BsBookFlow bookFlows) {

		return mapper.updatebookFlow(bookFlows);
	}
	
	/**
	 * 添加每BS_BOOKFLOW表中每条数据
	 */
	@Override
	public void addBookFlow(BsBookFlow bookFlow) {
		// TODO Auto-generated method stub
		mapper.addBookFlow(bookFlow);
		
	}
	
	/**
	 * 删除用户对应bookFlows表
	 */
	@Override
	public void deleteBookFlows(String fUserId) {
		// TODO Auto-generated method stub
		mapper.deleteBookFlows(fUserId);
	}

	/**
	 * 分页加搜索
	 */
	@Override
	public List<BsBookFlow> getListBookFlow(Pagination pagin) {
		// TODO Auto-generated method stub
		Pagination pagination=null;
			pagination = new Pagination(pagin.getStartPage()*10,10,pagin.getStartDate()
					,pagin.getEndDate(),pagin.getF_Direction_type(),pagin.getF_Consume_type(),
					pagin.getF_Account_type(),pagin.getF_UserId());
		return mapper.getListBookFlowsPage(pagination);
	}
	
	/**
	 * 总笔数和总价格
	 */
	@Override
	public TotalPaginationitem getTotalItemPrice(
			Pagination pagination) {
		// TODO Auto-generated method stub
		if(pagination.getF_Direction_type()==null){
			TotalPaginationitem paginationitem1 =null;
			TotalPaginationitem paginationitem2 =null;
				//收入
				 paginationitem1 = mapper.getTotalItemPrice(new Pagination(pagination.getStartPage(),
						pagination.getNumPage(),pagination.getStartDate(),pagination.getEndDate(), "0",
						pagination.getF_Consume_type(),pagination.getF_Account_type(),pagination.getF_UserId()));
				//支出
				 paginationitem2 = mapper.getTotalItemPrice(new Pagination(pagination.getStartPage(),
						pagination.getNumPage(),pagination.getStartDate(),pagination.getEndDate(), "1",
						pagination.getF_Consume_type(),pagination.getF_Account_type(),pagination.getF_UserId()));
				
			
			//支出与收入相加
			Integer numItem = Integer.parseInt(paginationitem1.getNumItem())+Integer.parseInt(paginationitem2.getNumItem());
			float totalPrice =0;
			if(paginationitem1.getTotalPrice()==null){
				if(paginationitem2.getTotalPrice()==null){
					totalPrice = Float.parseFloat("0")-Float.parseFloat("0");
				}else{
					totalPrice = Float.parseFloat("0")-Float.parseFloat(paginationitem2.getTotalPrice());
				}
			}else{
				 totalPrice = Float.parseFloat(paginationitem1.getTotalPrice()==null?"0":paginationitem1.getTotalPrice())-
				 Float.parseFloat(paginationitem2.getTotalPrice()==null?"0":paginationitem2.getTotalPrice());		

			}
			
			return new TotalPaginationitem(String.valueOf(numItem),String.valueOf(totalPrice));
		}else{
			TotalPaginationitem paginationitem3 =null;
				 paginationitem3 =mapper.getTotalItemPrice(new Pagination(pagination.getStartPage(), pagination.getNumPage(), 
							pagination.getStartDate(), pagination.getEndDate(), pagination.getF_Direction_type()
							,pagination.getF_Consume_type(),pagination.getF_Account_type(),pagination.getF_UserId()));
			
			return new TotalPaginationitem(paginationitem3.getNumItem(), paginationitem3.getTotalPrice()==null?"0":paginationitem3.getTotalPrice());
		}
		
	}
	
	/**
	 * 查询字典表
	 * @param str 
	 * @param
	 * @return
	 */
	@Override
	public List<BaBusinessdct> getZdb(String str) {
		// TODO Auto-generated method stub
		return mapper.getZdb(str);
	}

	
	
	

}
