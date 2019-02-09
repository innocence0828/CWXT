package com.wh.service.base;

import com.wh.utils.DbUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author BOSS
 *
 */
@Service
@Transactional
@Scope("prototype")
public class BaseMainService {
	
	/**
	 * 菜单查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryMenu(Map<String, Object> params) {
		List<Map<String, Object>> lists = null;
		 StringBuffer sql=new StringBuffer("select a.id,a.pid,a.fi_level,a.fs_name,a.fs_url,a.fs_icon,a.fi_sort "
					+ "from ba_menu a "
					+ "where a.fi_del=0 ");
		 try {
			lists = DbUtils.queryForList(sql.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lists;
	}
}
