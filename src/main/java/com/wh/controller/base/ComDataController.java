package com.wh.controller.base;

import com.alibaba.fastjson.JSON;
import com.wh.service.base.BaseTermQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 公共数据控制器
 * @author BOSS
 *
 */
@Controller
@RequestMapping("/comData")
public class ComDataController {
	
	/**
	 * 获取数据字典下拉框数据
	 * @param type 字典类型
	 * @return
	 */
	@RequestMapping(value = "/comDictSel", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String comDictSel(String type){
		return JSON.toJSONString(toSelData(BaseTermQueryService.getDictList(type))) ;
	}
//	
	private List<Map<String,String>> toSelData(List<Map<String,String>> li){
		List<Map<String,String>> selLi=new ArrayList<Map<String,String>>();
		Map<String,String> selMap;
		for(Map<String,String> item:li) {
			Set<String> keys=item.keySet();
			selMap=new HashMap<String,String>();
			for(String key:keys) {
				selMap.put("id", key);
				selMap.put("text", item.get(key));
				break;
			}
			selLi.add(selMap);
		}
		return selLi;
	}
}
