package com.wh.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 核心组件控制器
 * @author BOSS
 *
 */
@Controller
@RequestMapping("/com")
public class ComCoreController {
	/**
	 * 图标选择页面
	 * @param model
	 * @param fs_icon 默认被选中的图标CSS
	 * @return
	 */
	@RequestMapping("/IconS")
	public String IconS(Model model, String fs_icon){
		model.addAttribute("fs_icon", fs_icon);
		return "common/com_IconS";
	}
	
	/**
	 * Tree选择页面
	 * @param model
	 * @param postUrl   获取Trees数据URL
	 * @param selectIds 默认选中的id 多个用,分割
	 * @param multi     是否多选  传true/false
	 * @param level     默认展开的层级
	 * @return
	 */
	@RequestMapping("/TreeS")
	public String TreeS(Model model, String postUrl, String selectIds, String multi, String level) {
		model.addAttribute("postUrl", postUrl);
		model.addAttribute("selectIds", selectIds);
		model.addAttribute("multi", multi);
		model.addAttribute("level", level);
		return "common/com_TreeS";
	}
}
