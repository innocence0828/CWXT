package com.wh.utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
@Transactional  
public class DbUtils {
	private static JdbcUtils dbHelper = new JdbcUtils();
	
	/**
	 * 插入封装一
	 * @param sql
	 * @param
	 * @return
	 * @throws SQLException 
	 */
	public static Boolean execute(String sql,Object... params1) throws SQLException {
		// TODO Auto-generated method stub
		Boolean flagBoo = null;
			List<Object> params2 = new ArrayList<Object>();
			for (int i=0;i<params1.length;i++) {
				params2.add(params1[i]);
			}
			
			flagBoo = dbHelper.updateByPreparedStatement(sql, params2);
//			if(conn!=null)
//			conn.close();
		return flagBoo;
	}

	
	
	/**
	 * 查询单条记录封装1
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static Object executeQueryToValue(String sql,String params) throws SQLException {
		// TODO Auto-generated method stub
		Object object = null;
			object = dbHelper.findSimpleResult(sql,null).get(params);
//			if(conn!=null)
//				conn.close();
		return object;
	}
	
	/**
	 * 查询单条记录封装等到一条数据
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static Object executeQueryToValue2(Object key,String sql,Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Object object = null;
		if(params!=null&&params.length>0){
			List<Object> params2 = new ArrayList<Object>();
			for (int i=0;i<params.length;i++) {
				params2.add(params[i]);
			}
			object = dbHelper.findSimpleResult(sql,params2).get(key);
		}else{
			object = dbHelper.findSimpleResult(sql,null).get(key);
		}
			
//			if(conn!=null)
//			conn.close();
		return object;
	}
	
	/**
	 * Map<String,Object> 查询单条记录封装1
	 * @param sql
	 * @param params1
	 * @return
	 */
	public static Map<String,Object> queryForMap(String sql ,Object... params1)  {
		// TODO Auto-generated method stub
		Map<String, Object> maps=null;
		try {
			if(null != params1 && params1.length>0){
				List<Object> params2 = new ArrayList<Object>();
				for (int i=0;i<params1.length;i++) {
					params2.add(params1[i]);
				}
				maps = dbHelper.findSimpleResult(sql,params2);
			}else{
				maps = dbHelper.findSimpleResult(sql,null);
			}
//			if(conn!=null)
//			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return maps;
	}
	
	/**
	 * 查询多条记录封装
	 * @param sql
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String,Object>> queryForList(String sql,Object... params1) {
		// TODO Auto-generated method stub
		
 		List<Map<String, Object>> lists=null;
		try {
			if(params1!=null && params1.length>0){
				List<Object> params2 = new ArrayList<Object>();
				for (int i=0;i<params1.length;i++) {
					params2.add(params1[i]);
				}
				lists = dbHelper.findModeResult(sql,params2);
			}else{
				lists = dbHelper.findModeResult(sql,null);
			}
//			if(conn!=null)
//			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return lists;
	}
	
	

	
	

}
