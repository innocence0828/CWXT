package com.wh.controller.web;

import com.alibaba.fastjson.JSON;
import com.wh.service.web.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 查询所有信息
 * @author drh
 *
 */
@RestController
@RequestMapping("/homeController")
public class HomeController  {
	
	@Autowired
	private HomeService homeService;
	
	/**
	 * 四个头数据查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query", produces = "application/json; charset=utf-8")
	public String headFourDatas(HttpServletRequest request) {
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		Map<String,Object> maxMap = homeService.getheadFourDatas(params);
		return JSON.toJSONString(maxMap);
	}
	
	/**
	 * 每个月支出与收入
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query1", produces = "application/json; charset=utf-8")
	public String getMonthInOut(HttpServletRequest request) {
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		Map<String,Object> maxMap = homeService.getMonthInOut(params);
		return JSON.toJSONString(maxMap);
	}
	
	/**
	 * 每个月支出分类
	 * @return  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query2", produces = "application/json; charset=utf-8")
	public String getConsumeType(HttpServletRequest request) {
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		List<Map<String,Object>> maxMap = homeService.getConsumeType(params);
		return JSON.toJSONString(maxMap);
	}
	
	/**
	 * 按年收入支出数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query3", produces = "application/json; charset=utf-8")
	public String getInOutType(HttpServletRequest request) {
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		List<Map<String,Object>> maxMap = homeService.getInOutType(params);
		return JSON.toJSONString(maxMap);
	}
}
	