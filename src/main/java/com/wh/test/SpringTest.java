package com.wh.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.wh.sys.PropertyUtil;
import com.wh.utils.DateUtils;
import com.wh.utils.JdbcUtils;


public class SpringTest {

	 public static void main(String[] args) {
//		JdbcUtils jdbcUtils = new JdbcUtils();
//		jdbcUtils.getConnection();
//		 StringBuffer sql=new StringBuffer("select a.id,a.pid,a.fi_level,a.fs_name,a.fs_url,a.fs_icon,a.fi_sort "
//					+ "from ba_menu a "
//					+ "where a.fi_del=0 ");
////		 StringBuffer sql=new StringBuffer("select * from ba_meun");
//		 try {
//			
//			List<Map<String, Object>> list = jdbcUtils.findModeResult(sql.toString(), null);
//	        System.out.println(list);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 System.out.println(DateUtils.getCurrentDateTime());
	 }
}
