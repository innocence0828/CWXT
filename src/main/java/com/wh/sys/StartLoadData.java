package com.wh.sys;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.wh.dao.BaseTermQueryDao;
import com.wh.service.base.BaseTermQueryService;
public class StartLoadData  implements InitializingBean, ServletContextAware{
	@Resource
	private BaseTermQueryDao  baseTermQueryDao;
	/**
	 * 启动执行的方法，也可以下写到下面的方法中
	 */
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> parmLi=baseTermQueryDao.getTermQuery();
		for(Map<String,Object> parm:parmLi) {
			String type=parm.get("type1").toString();
			String key=parm.get("key1").toString();
			String value=parm.get("value1").toString();
			Map<String,String> CacheMap=BaseTermQueryService.DictCacheMap.get(type);
			if(CacheMap==null) {
				CacheMap=new HashMap<String,String>();
				BaseTermQueryService.DictCacheMap.put(type,CacheMap);
			}
			CacheMap.put(key, value);
			List<Map<String,String>> CacheList=BaseTermQueryService.DictCacheList.get(type);
			if(CacheList==null) {
				CacheList=new ArrayList<Map<String,String>>();
				BaseTermQueryService.DictCacheList.put(type, CacheList);
			}
			Map<String,String> new_parm=new HashMap<String,String>();
			new_parm.put(key, value);
			CacheList.add(new_parm);
		}
		System.out.println(BaseTermQueryService.DictCacheList);
	}
	  /**
     * InitializingBean 接口方法
     * 用于初始化工作
     */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}




}
