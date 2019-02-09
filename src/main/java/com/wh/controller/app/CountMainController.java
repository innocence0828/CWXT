package com.wh.controller.app;

import com.alibaba.fastjson.JSON;
import com.wh.entity.CountInfo;
import com.wh.entity.Result;
import com.wh.service.app.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/countMainController")
public class CountMainController {

	@Autowired
	private CountService countService;

	/**
	 * 统计分析
	 * @param
	 * @return
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/listCount", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listCount(CountInfo countInfo) {
		String result = null;
		List<CountInfo> countInfos  = countService.getListCount(countInfo);
		if(countInfos!=null){
			result = JSON.toJSONString(new Result(true, "200", "数据查询成功",countInfos));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "数据查询失败"));
		}
		
		return result;

	}

}
