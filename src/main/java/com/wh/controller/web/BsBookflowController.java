package com.wh.controller.web;

import com.alibaba.fastjson.JSON;
import com.wh.controller.base.BaseController;
import com.wh.entity.PagerResult;
import com.wh.entity.BsBookFlow;
import com.wh.entity.Result;
import com.wh.service.web.BsBookflowService;
import com.wh.utils.ExcelModel;
import com.wh.utils.ExportUtil;
import com.wh.utils.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 查询所有信息
 * @author BOSS
 *
 */
@Controller
@RequestMapping("/bsBookflowController")
public class BsBookflowController extends BaseController {
	
	
	@Autowired
	private BsBookflowService bookflowService;
	
	/**
	 * 账本基本信息查询页面
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request) {
		model.addAttribute("fs_name",((Map<String, Object>) request.getAttribute("params")).get("f_UserId"));
		return "web/bsBookflow_list";
	}
	
	/**
	 * 查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String query(HttpServletRequest request) {
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		PagerResult<BsBookFlow> pager  = bookflowService.getBookflowCount(params);
		pager.setRows(bookflowService.getListBookFlow(params));
		String dd = JSON.toJSONString(pager);
		return dd;
	}
	
	/**
	 * 数据导出
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/export")
	@ResponseBody
	public String export(HttpServletRequest request){
		Result<Object> result = new Result<Object>();
		Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
		params.put("page", "1");
		params.put("rows", Long.MAX_VALUE);
		PagerResult<BsBookFlow> pager  = bookflowService.getBookflowCount(params);
		pager.setRows(bookflowService.getListBookFlow(params));
		List<Map<String,Object>>  list=  MapUtils.java3Map(pager.getRows());
		if(list.isEmpty()) {
			result.setSuccess(false);
			result.setMsg("导出失败,未查询到数据");
		}else {
			try {
				ExcelModel excelModel=new ExcelModel();
				excelModel.columnsName=new String[]{"f_Date","f_Caption","f_Money","f_Direction_type","f_Account_type","f_Consume_type","f_Note"};
				excelModel.cellsTitle.put(0, "日期");
				excelModel.cellsTitle.put(1, "标题");
				excelModel.cellsTitle.put(2, "金额");
				excelModel.cellsTitle.put(3, "收入支出类型");
				excelModel.cellsTitle.put(4, "支付方式");
				excelModel.cellsTitle.put(5, "支付类别");
				excelModel.cellsTitle.put(6, "备注");
				excelModel.cellsHorizontalAlignment.put(0, HSSFCellStyle.ALIGN_LEFT);
				excelModel.cellsHorizontalAlignment.put(1, HSSFCellStyle.ALIGN_LEFT);
				excelModel.cellsHorizontalAlignment.put(2, HSSFCellStyle.ALIGN_LEFT);
				excelModel.cellsHorizontalAlignment.put(3, HSSFCellStyle.ALIGN_LEFT);
				excelModel.cellsHorizontalAlignment.put(4, HSSFCellStyle.ALIGN_LEFT);
				excelModel.cellsHorizontalAlignment.put(5, HSSFCellStyle.ALIGN_LEFT);
				excelModel.cellsHorizontalAlignment.put(6, HSSFCellStyle.ALIGN_LEFT);
				String excelPath=UUID.randomUUID().toString();
				ExportUtil.exportListToFile(list, excelModel,request.getServletContext().getRealPath("/images/")+excelPath+".xls");
				result.setSuccess(true);
				result.setList(excelPath);
			} catch (Exception e) {
				result.setSuccess(false);
				result.setMsg("导出失败，异常["+e.getMessage()+"]");
			}
		}
		return JSON.toJSONString(result);
	}
}
	