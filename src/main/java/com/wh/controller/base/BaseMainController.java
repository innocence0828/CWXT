package com.wh.controller.base;

import com.alibaba.fastjson.JSON;
import com.wh.service.base.BaseMainService;
import com.wh.sys.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author BOSS
 *
 */
@Controller
public class BaseMainController {
	@Autowired
	private BaseMainService mainService;
	@SuppressWarnings("unchecked")
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		Map<String,Object> params=new HashMap<String, Object>();
		List<Map<String, Object>> li=this.mainService.queryMenu(params);
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		for(Map<String,Object> item:li) {
			String id=item.get("id").toString();
			String name=(String)item.get("fs_name");
			String pid=(String)item.get("pid");
			Integer level= (Integer) item.get("fi_level");
			Integer sort= Integer.parseInt(item.get("fi_sort").toString());
			treeList.add(new TreeNode(id,name,pid,level,sort,item));
		}
		model.addAttribute("menuTree", JSON.toJSONString(treeList));
		model.addAttribute("sys_name","文荷集团");
		model.addAttribute("fs_name",((Map<String, Object>) request.getAttribute("params")).get("f_UserId"));
		return "/base/main";
	}

	@RequestMapping("/home")
	public String home() {
		return "web/home";
	}
}
