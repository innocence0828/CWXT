package com.wh.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wh.entity.*;
import com.wh.service.app.MainService;
import com.wh.utils.ClassifyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mainController")
public class MainController {

	@Autowired
	private MainService mainService;
	
	
	/**
	 * pc:获取所有流水信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllBookFlow")
	public String getAllBookFlow(HttpServletRequest request) {

		List<BsBookFlow> findAll = mainService.getAllBookFlow();
		request.setAttribute("BsBookFlow", findAll);
		return "/allUser";
	}

	

	/**
	 * 添加addBookFlow每一条
	 * @param bookFlow
	 * @param request
	 * @return
	 */
	@RequestMapping("/addBookFlow")
	@ResponseBody
	public String addBookFlow(BsBookFlow bookFlow, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			if (bookFlow != null && !"".equals(bookFlow)) {
				bookFlow.setF_Id(UUID.randomUUID().toString().trim().replace("-", ""));
				mainService.addBookFlow(bookFlow);
			}
			result = JSON.toJSONString(new Result(true, "200", "上传成功"));
		} catch (Exception e) {
			// TODO: handle exception
			result = JSON.toJSONString(new Result(false, "500", "上传失败"));
		}
		 return result;

	}

	/**
	 * appaddAllBookFlow:数据同步
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/addAllBookFlow", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addAllBookFlow(String json,String f_UserId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<BsBookFlow> bookFlows = null;
		try {
			if (json != null && !"".equals(json)) {
				bookFlows = JSONArray.parseArray(json, BsBookFlow.class);
				for (BsBookFlow bookFlow : bookFlows) {
					bookFlow.setF_Id(UUID.randomUUID().toString().trim()
							.replace("-", ""));
				}
				//删除用户对应bookFlows表
				mainService.deleteBookFlows(f_UserId);
				mainService.addAll(bookFlows,f_UserId);

			}
			return JSON.toJSONString(new Result(true, "200", "数据同步成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSON.toJSONString(new Result(false, "500", "数据同步失败"));
		}
		// return "redirect:/user/getAllUser";

	}
	
	/**
	 * app:获取所有流水信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDetails")
	public String getDetails(HttpServletRequest request) {
		List<BsBookFlow> findAll = mainService.getAllBookFlow();
		List<BsBookFlow> bookFlows= new ArrayList<BsBookFlow>();
		for(BsBookFlow bookFlow:findAll){
			bookFlow.setF_Direction_type(bookFlow.getF_Direction_type().equals("1")?"支出":"收入");
			for (int i = 0; i < ClassifyUtils.f_Consume_Type.length; i++) {
				if(bookFlow.getF_Consume_type()!=null&&bookFlow.getF_Consume_type()
						.equals(ClassifyUtils.f_Consume_Type[i])){
					bookFlow.setF_Consume_type(ClassifyUtils.f_Consume_TypeValue[i]);
				}
				
			}
			for (int i = 0; i < ClassifyUtils.f_Card_Type.length; i++) {
				if(bookFlow.getF_Account_type()!=null&&bookFlow.getF_Account_type()
						.equals(ClassifyUtils.f_Card_Type[i])){
					bookFlow.setF_Account_type(ClassifyUtils.f_Card_TypeValue[i]);
				}
				
			}
			bookFlows.add(bookFlow);
			
			
		}

		request.setAttribute("userList", bookFlows);
		return "/allUser";
	}

	/**
	 *updatebookFlow修改记账数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updatebookFlow")
	@ResponseBody
	public String updatebookFlow(BsBookFlow bookFlow,String typestr, HttpServletRequest request) {
		String result=null;
		try {
			if("App".equals(typestr)){
				 mainService.updatebookFlow(bookFlow);
				 result = JSON.toJSONString(new Result(true, "200", "更新保存成功"));
			 }else if (mainService.updatebookFlow(bookFlow)) {
				bookFlow = mainService.getBookflowf_Id(bookFlow.getF_Id());

				request.setAttribute("bs_bookflow", bookFlow);
				result = "redirect:/user/getAllUser";
			} else {
				result =  "/error";
			}
		} catch (Exception e) {
			result = JSON.toJSONString(new Result(true, "200", e.toString()));
		}
		 
		return result;
	}

	/**
	 * Bookflow根据id查询单个用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBookflowf_Id")
	public String getBookflowf_Id(String f_Id, HttpServletRequest request) {

		request.setAttribute("bs_bookflow", mainService.getBookflowf_Id(f_Id));
		return "/editUser";
	}

	/**
	 * 删除delBookflowF_Id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delBookflowF_Id", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delBookflowF_Id(String f_Id,String typestr, HttpServletRequest request,
			HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			if("App".equals(typestr)){
				mainService.deleteBookflowf_Id(f_Id);
				result = JSON.toJSONString(new Result(true, "200", "删除成功"));
			}
			if (mainService.deleteBookflowf_Id(f_Id)) {
				result = JSON.toJSONString(new Result(true, "200", "删除成功"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			result = JSON.toJSONString(new Result(false, "500", "删除失败"));
		}
		
		return result;
	}

	/**
	 * 主页分页
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/listPage", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listPage(Pagination pagination) {
		String result =null;
		List<BsBookFlow> bookFlows = null;
		TotalPaginationitem paginationitem = null;
		if(pagination!=null){
			bookFlows = mainService.getListBookFlow(pagination);
			paginationitem = mainService.getTotalItemPrice(pagination);
			result = JSON.toJSONString(new Result(true, "200", "数据查询成功".trim(),bookFlows,paginationitem));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "数据查询失败"));
		}
		
		return result;

	}
	
	/**
	 * 字典表
	 * @return
	 * @throws 
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/zdb", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String zdb(String type) {
		String result =null;
		List<BaBusinessdct> baBusinessdcts = mainService.getZdb(type);
		if(baBusinessdcts!=null){
			result = JSON.toJSONString(new Result(true, "200", "字典表成功".trim(),baBusinessdcts));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "字典表失败"));
		}
		return result;
	}
}
