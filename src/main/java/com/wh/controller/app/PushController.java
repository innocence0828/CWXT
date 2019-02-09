package com.wh.controller.app;

import com.alibaba.fastjson.JSON;
import com.wh.entity.Result;
import com.wh.service.app.PushService;
import com.wh.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/pushController")
public class PushController {
    /**
     * 提交人李云文1
     *
     */
	@Autowired
	private PushService pushService;
	String stringRtrue;
	/**
	 * 推送统计插入
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insertPush", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String insertPush(HttpServletRequest request) {
		String result = null;
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		params.put("f_Insertdate", DateUtils.getCurrentDateTime());
		params.put("f_Id", UUID.randomUUID().toString().replace("-", ""));
		params.put("f_Static", "0");
		boolean regflag = StringUtils.isBlank(params.get("f_Push_date")+"")&& err0("请输入推送日期")
				|| StringUtils.isBlank(params.get("f_Static")+"")&& err0("请输入推送状态")
				|| StringUtils.isBlank(params.get("f_Note")+"")&& err0("请输入备注")
				|| StringUtils.isBlank(params.get("f_Userid")+"")&& err0("请输入用户名")
				|| StringUtils.isBlank(params.get("f_Cid")+"") && err0("获取对应Cid");
		//提示信息
		if (regflag) 
			result = JSON.toJSONString(new Result(false, "500", stringRtrue));
		Boolean bl = pushService.getInsertPush(params);
		if(bl){
			result = JSON.toJSONString(new Result(true, "200", "数据插入成功"));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "数据插入失败"));
		}
		
		return result;

	}

	/**
	 * 查询统计
	 * @param request
	 * @return
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/selectPushAll", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectPushAll(HttpServletRequest request) {
		String result = null;
		Result resul2 = null;
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		resul2 = pushService.getselectPushAll(params);
		if(resul2.getSuccess()){
			result = JSON.toJSONString(new Result(true, "200", "数据查询成功",resul2));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "数据查询失败"));
		}
		return result;

	}
	
	/**
	 * 更新任务状态
	 * @param request
	 * @return
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/updateTaskStatus", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateTaskStatus(HttpServletRequest request) {
		String result = null;
		Result resul2 = null;
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		resul2 = pushService.updateTaskStatus(params);
		if(resul2.getSuccess()){
			return JSON.toJSONString(new Result(true, "200", "更新成功"));
		}else{
			return JSON.toJSONString(new Result(false, "500", "更新失败"));
		}
	}
	
	/**
	 * 删除推送表
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/deletetPushOne", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deletetPushOne(HttpServletRequest request) {
		String result = null;
		Result result2 = null;
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		result2 = pushService.getDeletetPushOne(params);
		//提示信息
		if(result2.getSuccess()){
			result = JSON.toJSONString(new Result(true, "200", "数据查询成功"));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "数据查询失败"));
		}
		
		return result;

	}

	/**
	 * 更新推送表
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/updatetPushOne", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updatetPushOne(HttpServletRequest request) {
		String result = null;
		Result result2 = null;
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		result2 = pushService.getUpdatetPushOne(params);
		//提示信息
		if(result2.getSuccess()){
			result = JSON.toJSONString(new Result(true, "200", "数据查询成功"));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "数据查询失败"));
		}
		
		return result;

	}
	
	private boolean err0(String string) {
		// TODO Auto-generated method stub
		stringRtrue = string;
		return true;
	}

}
