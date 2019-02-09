package com.wh.digui;


import com.alibaba.fastjson.JSON;
import com.wh.utils.DbUtils;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 递归方
 * @author Thinkpad-e470
 *
 */
public class RecurrenceAlgorithm {
	
	private List<Map<String, Object>> queryForList;

	public RecurrenceAlgorithm() {
		// TODO Auto-generated constructor stub
		queryForList = DbUtils.queryForList("select * from bonc.budget where 1=1 order by level_id", null);
		System.out.println("=============================");
	}
	
	/**
	 * 递归方法一
	 */
	private void RecurenceMethordOne(){
		Map<String, Object> map = queryForList.get(0);
		Vector<ConcurrentHashMap> newList = new Vector<ConcurrentHashMap>();
		ConcurrentHashMap hashmap = new ConcurrentHashMap();
		 hashmap.put("erpCode", map.get("ERP_CODE"));
		 hashmap.put("parErpCode", map.get("PAR_ERP_CODE"));
		 hashmap.put("erpName", map.get("ERP_NAME"));
		 hashmap.put("getSon", getSon(queryForList,hashmap.get("erpCode").toString()));
		 newList.add(hashmap);
		 String jsonString = JSON.toJSONString(newList);
		 System.out.println(jsonString);
	}
	/**
	 * 递归方法
	 *
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<ConcurrentHashMap> getSon(List<Map<String, Object>> oldList,String erpCode) {
		// TODO Auto-generated method stub
		Vector<ConcurrentHashMap> arrayList = new Vector<ConcurrentHashMap>();
		for (Map<String, Object> map : oldList) {
			if(erpCode.equals(map.get("PAR_ERP_CODE").toString())){
				ConcurrentHashMap hashmap = new ConcurrentHashMap();
				 hashmap.put("erpCode", map.get("ERP_CODE"));
				 hashmap.put("parErpCode", map.get("PAR_ERP_CODE"));
				 hashmap.put("erpName", map.get("ERP_NAME"));
				 hashmap.put("getSon", getSon(oldList,hashmap.get("erpCode").toString()));
				 arrayList.add(hashmap);
			}
			
		}
		
		return arrayList;
	}
	
	/**
	 * 递归方法二
	 */
	private void RecurenceMethordTwo() {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		new RecurrenceAlgorithm().RecurenceMethordOne();
	}

}
