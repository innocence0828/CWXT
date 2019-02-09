package com.wh.service.web.imp;

import com.wh.dao.HomeDao;
import com.wh.service.web.HomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class HomeServiceImpl implements HomeService {
	@Resource
	private HomeDao mapper;
	
	/**
	 * 获取主页头部四大信息数据
	 */
	@Override
	public Map<String,Object> getheadFourDatas(Map<String, Object> params) {
		if(params.get("f_UserId")!=null&&params.get("f_UserId").equals("admin")&&!params.get("f_UserId").equals("")){
			params.put("f_UserId", null);
		}
		// 获取主页头部四大数据
		return mapper.getheadFourDatas(params);
	}
	
	/**
	 * 收入与支出安月查询
	 */
	@Override
	public Map<String,Object> getMonthInOut(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Map<String,Object>  map = null;
		if(params.get("f_UserId")!=null&&params.get("f_UserId").equals("admin")&&!params.get("f_UserId").equals("")){
			params.put("f_UserId", null);
			map = mapper.getMonthInOut(params);
		}else{
//			params.put("f_Date",DateUtils.getCurrentDateTime());
			map = mapper.getMonthInOut(params);
		}
		// 获取主页头部四大数据
		return map;
	}
	
	
	/**
	 * 每月消费类型
	 */
	@Override
	public List<Map<String,Object>> getConsumeType(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  maps = null;
		if(params.get("f_UserId")!=null&&params.get("f_UserId").equals("admin")&&!params.get("f_UserId").equals("")){
			params.put("f_UserId", null);
			maps = mapper.getConsumeType(params);
		}else{
//			params.put("f_Date",DateUtils.getCurrentDateTime());
			maps = mapper.getConsumeType(params);
		}
		//消费类型
		return maps;
	}
	
	
	/**
	 * 分页获取所有信息
	 */
	@Override
	public List<Map<String,Object>> getInOutType(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  maps = null;
		if(params.get("f_UserId")!=null&&params.get("f_UserId").equals("admin")&&!params.get("f_UserId").equals("")){
			params.put("f_UserId", null);
			maps = mapper.getInOutType(params);
		}else{
//			params.put("f_Date",DateUtils.getCurrentDateTime());
			maps = mapper.getInOutType(params);
		}
		//按年查询收入支出
		return maps;
	}
	

}
