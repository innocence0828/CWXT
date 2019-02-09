package com.wh.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.entity.BsBookFlow;
import com.wh.sys.PropertyUtil;
@Service
@Transactional  
public class JdbcUtils {
////	 数据库用户名  
//    private static final String USERNAME = "root";  
//    //数据库密码  
//    private static final String PASSWORD = "liyunwen0506";  
//    //驱动信息   
//    private static final String DRIVER = "com.mysql.jdbc.Driver";  
//    //数据库地址  
//    private static final String URL = "jdbc:mysql://47.95.215.33:3306/bonc";  

	private PreparedStatement pstmt;
	private ResultSet resultSet;


	/**
	 * 增加、删除、改
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params) throws SQLException{
		boolean flag = false;
			int result = -1;
			Connection connection = null;
			try {
				if(connection==null)
				connection = JDBCConnFacory.getConnection();
				connection.setAutoCommit(false);
				//true：sql命令的提交（commit）由驱动程序负责
				//false：sql命令的提交由应用程序负责，程序必须调用commit或者rollback方法
				pstmt = connection.prepareStatement(sql);
			int index = 1;
			if(params != null && !params.isEmpty()){
				for(int i=0; i<params.size(); i++){
					pstmt.setObject(index++, params.get(i));
				}
			}
			result = pstmt.executeUpdate();
			connection.commit();
			pstmt.close();
			flag = result > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.rollback();
			flag=false;
		}
		
		return flag;
	}
	
	/**
	 * 批量导入
	 * @param sql
	 * @param
	 * @return
	 * @throws SQLException
	 */
	
	public boolean updateByPreparedStatement2(String sql, List<BsBookFlow> basBookFlows) throws SQLException{
		boolean flag = false;
		Connection connection = null;
		try {
			if(connection==null)
			connection = JDBCConnFacory.getConnection();
			connection.setAutoCommit(false);
			//true：sql命令的提交（commit）由驱动程序负责
			//false：sql命令的提交由应用程序负责，程序必须调用commit或者rollback方法
			pstmt = JDBCConnFacory.getConnection().prepareStatement(sql);
			if(basBookFlows != null && !basBookFlows.isEmpty()){
				for(int i=0; i<basBookFlows.size(); i++){
					pstmt.setString(1, basBookFlows.get(i).getF_Id());
					pstmt.setString(2, basBookFlows.get(i).getF_Date());
					pstmt.setString(3, basBookFlows.get(i).getF_Direction_type());
					pstmt.setString(4, basBookFlows.get(i).getF_Note());
					pstmt.setString(5, basBookFlows.get(i).getF_Money());
					pstmt.setString(6, basBookFlows.get(i).getF_Caption());
					pstmt.setString(7, basBookFlows.get(i).getF_Imgurl());
					pstmt.setString(8, basBookFlows.get(i).getF_Account_type());
					pstmt.setString(9, basBookFlows.get(i).getF_Consume_type());
					pstmt.setString(10, basBookFlows.get(i).getF_UserId());
					pstmt.addBatch();
				}
			}
			int [] executsize = pstmt.executeBatch();
			connection.commit();
			pstmt.close();
			flag = (executsize.length==basBookFlows.size()) ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.rollback();
			flag=false;
		}
		
		return flag;
	}

	/**
	 * 查询单条记录
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		int index  = 1;
		pstmt = JDBCConnFacory.getConnection().prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i=0; i<params.size(); i++){
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();//返回查询结果
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		while(resultSet.next()){
			for(int i=0; i<col_len; i++ ){
				String cols_name = metaData.getColumnName(i+1);
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null){
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}

	/**查询多条记录
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = JDBCConnFacory.getConnection().prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i = 0; i<params.size(); i++){
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while(resultSet.next()){
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0; i<cols_len; i++){
				String cols_name = metaData.getColumnName(i+1);
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null){
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}

		return list;
	}

	/**通过反射机制查询单条记录
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls )throws Exception{
		T resultObject = null;
		int index = 1;
		pstmt = JDBCConnFacory.getConnection().prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i = 0; i<params.size(); i++){
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData  = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while(resultSet.next()){
			//通过反射机制创建一个实例
			resultObject = cls.newInstance();
			for(int i = 0; i<cols_len; i++){
				String cols_name = metaData.getColumnName(i+1);
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null){
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true); //打开javabean的访问权限
				field.set(resultObject, cols_value);
			}
		}
		return resultObject;

	}

	/**通过反射机制查询多条记录
	 * @param sql 
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls )throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = JDBCConnFacory.getConnection().prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i = 0; i<params.size(); i++){
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData  = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while(resultSet.next()){
			//通过反射机制创建一个实例
			T resultObject = cls.newInstance();
			for(int i = 0; i<cols_len; i++){
				String cols_name = metaData.getColumnName(i+1);
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null){
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true); //打开javabean的访问权限
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}

	/**
	 * 释放数据库连接
	 */
	public void releaseConn(){
		if(resultSet != null){
			try{
				resultSet.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils = new JdbcUtils();
		JDBCConnFacory.getConnection();

		/*******************增*********************/
		String sql = "insert into ba_userinfo (f_userid, f_id,F_PASSWORD) values (?, ?, ?)";
		List<Object> params = new ArrayList<Object>();
		params.add("小明");
		params.add("123xiaoming");
		params.add("zhangsan");
		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*******************删*********************/
		//删除名字为张三的记录
//		String sql = "delete from ba_versionInfo";
//		List<Object> params = new ArrayList<Object>();
//		params.add("小明");
//		boolean flag = jdbcUtils.updateByPreparedStatement(sql, null);
//		System.out.println(flag);

		/*******************改*********************/
		//将名字为李四的密码改了
	/*			String sql = "select id,pid,fs_name  from ba_menu where fi_del=0 order by pid,fi_sort";
		List<Object> params = new ArrayList<Object>();
		params.add("1");
		params.add("菜单管理");
		params.add("/menu/list");
		params.add("fa fa-list");
		params.add("0");
		params.add("2");
		params.add("2");
		List<Map<String, Object>> flag = jdbcUtils.findModeResult(sql,null);
		System.out.println(flag);*/
		
		

		/*******************查*********************/
		//不利用反射查询多个记录
		/*		String sql2 = "select * from userinfo ";
		List<Map<String, Object>> list = jdbcUtils.findModeResult(sql2, null);
		System.out.println(list);*/

		//利用反射查询 单条记录
		/*		String sql = "select * from userinfo where username = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add("李四");
		UserInfo userInfo;
		try {
			userInfo = jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);
			System.out.print(userInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Class clcs = UserInfo.class;
		try {
			Object obj = clcs.newInstance();
			Method f = clcs.getDeclaredMethod("setUsername", String.class);
			f.invoke(obj, "yan123");
			Method f2 = clcs.getDeclaredMethod("getUsername", null);
			Object name = f2.invoke(obj, null);
			System.out.println("反射得到的名字 = "  +  name);

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*      String sql2 = "select * from bs_bookflow"; 
        List<Map<String, Object>> list = jdbcUtils.findModeResult(sql2, null); 
        System.out.println(list); */ 

	}

}
