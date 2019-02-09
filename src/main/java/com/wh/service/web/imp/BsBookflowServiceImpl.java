package com.wh.service.web.imp;

import com.wh.dao.BsBookflowDao;
import com.wh.entity.PagerResult;
import com.wh.entity.BsBookFlow;
import com.wh.service.base.BaseTermQueryService;
import com.wh.service.web.BsBookflowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class BsBookflowServiceImpl implements BsBookflowService {
	//暂存数据功能
	Map<String, Object> map = new HashMap<String, Object>();
	@Resource
	private BsBookflowDao mapper;
	private Long i;
	
	/**
	 * 获取所有消费条数
	 */
	@Override
	public PagerResult<BsBookFlow> getBookflowCount(Map<String, Object> params) {
		PagerResult<BsBookFlow> pager =new PagerResult<BsBookFlow>();
		Integer numItem = null;
		Double totalPrice = null;
		// TODO Auto-generated method stub
		//过滤数据
		if(params.get("f_UserId").equals("admin")){
			params.put("f_UserId", null);
		}
		if(params.get("f_UserId")==null && params.get("f_TelNum")!=null){
			params.put("f_UserId", params.get("f_TelNum"));
		}
		if("0".equals(map.get("numItem"))||map.get("numItem")==null||params.get("page").equals(map.get("page"))){
			Map<String, Object>	mapCP = mapper.getListCount(params);
			numItem =Integer.parseInt(mapCP.get("numItem").toString());
			map.put("numItem", mapCP.get("numItem"));
			map.put("f_Money", mapCP.get("totalPrice"));
			map.put("inPrice", mapCP.get("inPrice"));
			map.put("outPrice", mapCP.get("outPrice"));
			map.put("f_UserId", "合计");
			map.put("page", params.get("page"));
		
		}else{
			numItem =Integer.parseInt(map.get("numItem").toString());
			totalPrice =Double.parseDouble(map.get("f_Money")==null?"0":map.get("f_Money").toString());
		}
		Long bb = Long.parseLong(params.get("rows").toString());
		Long log = numItem%bb==0?numItem/bb:(long)Math.ceil(numItem/bb)+1;
		pager.setRecords(Long.parseLong(numItem.toString()));
		pager.setTotal(log);
		pager.setUserdata(map);
		pager.setPage(Long.parseLong(params.get("page").toString()));
		return pager;
	}
	
	/**
	 * 分页获取所有信息
	 */
	@Override
	public List<BsBookFlow> getListBookFlow(Map<String, Object> params) {
		// TODO Auto-generated method stub
		//过滤数据
		if(params.get("f_UserId")!=null&&params.get("f_UserId").equals("admin")){
			params.put("f_UserId", null);
		}
		Long numIn1 = (Long.parseLong(params.get("page").toString())-1);
		Long numIn2 = Long.parseLong(params.get("rows").toString());
		//没用显示行数并且显示序号、
		Long numIn = numIn1*numIn2;
		params.put("startPage", numIn);
		params.put("numPage", Long.parseLong(params.get("rows").toString()));
		List<BsBookFlow> BookFlows1 =new ArrayList<BsBookFlow>();
		List<BsBookFlow> BookFlows2 =mapper.getListBookFlow(params);
		i=numIn;
		for (BsBookFlow bsBook:BookFlows2){
			i++;
			bsBook.setOrder_Id(i.toString());//序号
			bsBook.setF_Account_type((bsBook.getF_Account_type()==null?"": BaseTermQueryService.getDictText("2", bsBook.getF_Account_type())));
			bsBook.setF_Money(((bsBook.getF_Direction_type()==null?bsBook.getF_Money():bsBook.getF_Direction_type().toString().equals("1")?"-"+bsBook.getF_Money():bsBook.getF_Money())));
			bsBook.setF_Consume_type((bsBook.getF_Consume_type()==null?"暂无": BaseTermQueryService.getDictText("1", bsBook.getF_Consume_type())));
			bsBook.setF_Direction_type((bsBook.getF_Direction_type()==null?"": BaseTermQueryService.getDictText("3", bsBook.getF_Direction_type())));
			BookFlows1.add(bsBook);
		}
		
		return BookFlows1;
	}
	

}
