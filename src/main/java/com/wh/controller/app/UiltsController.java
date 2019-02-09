package com.wh.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/uiltsController")
public class UiltsController {

	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/downFile")
	public String downFile(HttpServletRequest request) {

		return "/downFile";
	}

	
}
