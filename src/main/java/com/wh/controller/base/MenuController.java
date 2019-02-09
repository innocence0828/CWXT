package com.wh.controller.base;

import com.alibaba.fastjson.JSON;
import com.wh.service.base.MenuService;
import com.wh.sys.TreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理控制器
 * 
 * @author BOSS
 * 
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;

	/**
	 * 跳转菜单列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "base/menu_list";
	}

	/**
	 * 查询菜单列表数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object search(HttpServletRequest request) {
		Map<String, Object> params = (Map<String, Object>) request
				.getAttribute("params");
		List<Map<String, Object>> li = menuService.search(params);
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		for (Map<String, Object> item : li) {
			String id = item.get("id").toString();
			String name = (String) item.get("fs_name");
			String pid = item.get("pid").toString();
			Integer level = (Integer) item.get("fi_level");
			Integer sort = Integer.valueOf(item.get("fi_sort").toString());
			treeList.add(new TreeNode(id, name, pid, level, sort, item));
		}
		return JSON.toJSONString(treeList);
	}

	/**
	 * 打开编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/input", produces = "application/json; charset=utf-8")
	public ModelAndView input(HttpServletRequest request, String id, String pid) {
		ModelAndView mv = new ModelAndView("base/menu_input");
		if (StringUtils.isNotEmpty(id)) {// 如果ID不为空 跳转修改页面
			mv.addObject("menu", menuService.getMenu(id));
		} else if (StringUtils.isNotEmpty(pid)) {
			mv.addObject("menu", menuService.getPMenuNmae(id));
		}
		return mv;
	}
	
	/**
	 * 删除页面
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delete(HttpServletRequest request, String id, String pid) {
		Map<String, Object> params = (Map<String, Object>) request.getAttribute("params");
		String ss = JSON.toJSONString(menuService.getDelete((String) params.get("id")));
		return ss;
	}

	/**
	 * 菜单保存
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/save", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String save(HttpServletRequest request) {
		Map<String, Object> params = (Map<String, Object>) request.getAttribute("params");
		String	result = JSON.toJSONString(menuService.save(params));
		return result;
	}

	/**
	 * 获取菜单Tree数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTreeDate", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getTreeDate() {
		List<Map<String, Object>> reli = menuService.getTreeDate();
		for (Map<String, Object> map : reli) {
			map.put("pId", map.get("pid"));
			map.put("name", map.get("fs_name"));
			map.remove("pid");
		}
		return JSON.toJSONString(reli);
	}
}
