package com.wh.service.base;

import com.wh.dao.BaseTermQueryDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 * @author BOSS
 *
 */
@Service
@Transactional
public class BaseTermQueryService {
	@Resource
	private BaseTermQueryDao baseTermQueryDao;
	public static final Map<String,Map<String,String>> DictCacheMap=new HashMap<String,Map<String,String>>();
	public static final Map<String,List<Map<String,String>>> DictCacheList=new HashMap<String,List<Map<String,String>>>();
	
	
	/**
	 * 获取数据字典
	 * @param type   字典类型
	 * @param key  字典代码
	 * @return
	 */
	public static String getDictText(String type,Object key) {
		return (key==null|| StringUtils.isEmpty(((BigDecimal)key).toString()))?"":DictCacheMap.get(type).get(key.toString());
	}
	
	/**
	 * 获取数据字典
	 * @param type   字典类型
	 * @param key  字典代码
	 * @return
	 */
	public static String getDictText(String type,String key) {
		return (key==null||StringUtils.isEmpty(key))?"":DictCacheMap.get(type).get(key);
	}
	
	/**
	 * 获取数据字典
	 * @param type   字典类型
	 * @return
	 */
	public static Map<String,String> getDictMap(String type) {
		return DictCacheMap.get(type);
	}
	
	/**
	 * 获取数据字典
	 * @param type   字典类型
	 * @return
	 */
	public static List<Map<String,String>> getDictList(String type) {
		return DictCacheList.get(type);
	}
	
}
