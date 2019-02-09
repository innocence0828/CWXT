package com.wh.service.base;

import com.wh.entity.Result;
import com.wh.utils.DateUtils;
import com.wh.utils.DbUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理服务
 * @author BOSS
 *
 */
@Service
@Scope("prototype")
public class MenuService {

	/**
	 * 菜单查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> search(Map<String, Object> params) {
		try {
			StringBuffer sql=new StringBuffer("select a.id,a.pid,a.fi_level,a.fs_name,a.fs_url,a.fs_icon,a.fi_sort "
					+ "from ba_menu a where a.fi_del=0 order by fi_sort");
			return DbUtils.queryForList(sql.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 菜单保存
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Result save(Map<String, Object> params) {
		Result result=new Result(true,"保存成功");
		String id=(String)params.get("id");
		String pid=(String)params.get("pid");
		String fs_name=(String)params.get("fs_name");
		String fs_url=(String)params.get("fs_url");
		String fs_icon=((String)params.get("fs_icon")).trim();
		String fi_sort=(String)params.get("fi_sort");
		Object fi_level=1;
		java.sql.Timestamp fd_ndtate = DateUtils.getTimestamp();
		try {
			if(StringUtils.isEmpty(id)) {//新增
				//生成ID
				id=(String)DbUtils.executeQueryToValue2("id","select CONCAT(max(id)+1,'') id from ba_menu",null);
				//根据pid生成pids
				if(StringUtils.isEmpty(pid)) {
					pid="0";
				}else {
					fi_level=DbUtils.executeQueryToValue2("fi_level+1","select fi_level+1 from ba_menu where id=?",pid);
				}
				DbUtils.execute("insert into ba_menu (id,pid,fs_name,fs_url,fs_icon,fi_sort,fd_ndtate,fi_level) "
						+ "values (?,?,?,?,?,?,?,?)", 
						id,pid,fs_name,fs_url,fs_icon,fi_sort,fd_ndtate,fi_level);
			}else {//修改
				//根据pid生成pids
				if(StringUtils.isEmpty(pid)||pid.equals("0")) {
					pid="0";
				}else {
					fi_level=DbUtils.executeQueryToValue2("fi_level+1","select fi_level+1 from ba_menu where id=?",pid);
				}
				DbUtils.execute("update ba_menu set pid=?,fs_name=?,fs_url=?,fs_icon=?,fi_sort=?,fd_ndtate=?,fi_level=? "
						+ "where id=?", pid,fs_name,fs_url,fs_icon,fi_sort,fd_ndtate,fi_level,id);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("保存失败，"+e.getMessage());
		} finally {
		}
		return result;
	}

	/**
	 * 获取菜单Tree数据
	 * @return
	 */
	public List<Map<String,Object>> getTreeDate() {
		return DbUtils.queryForList("select id,pid,fs_name from ba_menu where fi_del=0 order by pid,fi_sort");
	}

	/**
	 * 获取menu对象
	 * @param id
	 * @return
	 */
	public Map<String, Object> getMenu(String id) {
		return DbUtils.queryForMap("select a.*,b.fs_name pname from ba_menu a left join ba_menu b on a.pid=b.id where a.id=?",id);
	}

	/**
	 * 获取父级menu id name
	 * @param pid
	 * @return
	 */
	public Map<String, Object> getPMenuNmae(String pid) {
		return DbUtils.queryForMap("select id pid,fs_name pname from ba_menu where id=?",pid);
	}
	
	/**
	 * 删除menu id name
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Result getDelete(String id) {
		Result boo = null;
		try {
			if(id!=null&&DbUtils.execute("delete from ba_menu where id=?",id)){
				boo = new Result(true, "删除成功");
			}else{
				boo = new Result(false, "删除失败");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			boo = new Result(false, e.toString());
		}
		return boo;
		
	}
}
