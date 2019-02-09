package com.wh.controller.web;

import com.alibaba.fastjson.JSON;
import com.wh.entity.BaVersionInfo;
import com.wh.entity.Result;
import com.wh.service.web.BaVersionService;
import com.wh.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/baVersionController")
public class BaVersionController {

	@Autowired
	private BaVersionService countService;
	String stringRtrue;
   
	/**
	 * 插入版 本更新
	 * @param
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/insertData", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String insertData(BaVersionInfo versionInfo) {
		String result = null;
		boolean regflag =  (versionInfo.getF_Vertitle() == null && err0("请输入版本号"))
				|| (versionInfo.getF_Filename() == null && err0("请输入名字"))
				|| (versionInfo.getF_Crttime() == null && err0("请选择时间"))
				|| (versionInfo.getF_Url() == null && err0("请输文件"));
		//提示信息
		if (regflag) {
			result = JSON.toJSONString(new Result(false, "500", stringRtrue));
		}else{
			try {
				countService.verInertData(versionInfo);
				result = JSON.toJSONString(new Result(true, "500", "上传成功"));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
				result = JSON.toJSONString(new Result(false, "500", e.toString()));
			}
		
		}
		
		return result;

	}
	
	/**
	 * 查询版本更新信息
	 * @param
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/verquery", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String verquery(String fileName,String typeStr) {
		String result = null;
		double i =0;
		BaVersionInfo baVerInfo =null;
		List<BaVersionInfo> baVersionInfos1 = new ArrayList<BaVersionInfo>();
			try {
				List<BaVersionInfo>	baVersionInfos2 =countService.getVerQuery(fileName);
				if("App".equals(typeStr)&&baVersionInfos2.size()!=0){
					for(BaVersionInfo baVersionInfo:baVersionInfos2){
						double j =Double.parseDouble(baVersionInfo.getF_Verid());
						if(j>i){i=j; baVerInfo =baVersionInfo; }
					}
					baVersionInfos1.add(baVerInfo);
					result = JSON.toJSONString(new Result(true, "200",null, baVersionInfos1));
				}else{
					result = JSON.toJSONString(new Result(true, "200",null, baVersionInfos2));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
				result = JSON.toJSONString(new Result(false, "500", e.toString()));
			}
		
		return result;

	}
	
	/**
	 * 删除版本更新信息
	 * @param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/delver", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delver(String f_Id) {
		String result = null;
			try {
				countService.delVer(f_Id);
			    result = JSON.toJSONString(new Result(true, "200","删除成功"));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
				result = JSON.toJSONString(new Result(false, "500", e.toString()));
			}
		
		return result;

	}

	/**
	 * 上传文件会自动绑定到MultipartFile中
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/uploadFile", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, BaVersionInfo baVersionInfo, @RequestParam(value="file",required=false) MultipartFile file) throws Exception  {
		String result =null;
		String basePath =  request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/";
		// 如果文件不为空，写入上传路径
		if (!file.isEmpty()) {
			//文件有没有上传成功
			Boolean flagFile =	countService.createFile(request,file);
			if(flagFile){
				boolean regflag = (baVersionInfo.getF_Vertitle() == null && err0("请输入名字"))
						|| (baVersionInfo.getF_Verid() == null && err0("请输版本号"));
				//提示信息
				if (regflag) {
					result = JSON.toJSONString(new Result(false, "500", stringRtrue));
				}else{
					try {
						// 上传文件名
						String filename = file.getOriginalFilename();
						baVersionInfo.setF_Crttime(DateUtils.getCurrentDateTime());
						baVersionInfo.setF_Url(basePath+"baVersionController/downloadFile?path="+DateUtils.getShortYMDHMS()+filename);
						baVersionInfo.setF_Filename(filename);
						countService.verInertData(baVersionInfo);
						List<BaVersionInfo>	baVersionInfos =countService.getVerQuery(null);
						result = JSON.toJSONString(new Result(true, "500", "上传成功",baVersionInfos));
					} catch (Exception e) {
						// TODO: handle exception
						result = JSON.toJSONString(new Result(false, "500", e.toString()));
					}
				}
			}else {
				result = JSON.toJSONString(new Result(false, "500", "上传失败"));
			}
		} else {
			result = JSON.toJSONString(new Result(false, "500", "上传失败"));
		}
		return result;
	}
	
    /**
     * 下载文件
     * @param
     * @param
     * @return 下载文件的字节数组
     */
    @RequestMapping(value = "/downloadFile", produces = "application/json; charset=utf-8")
    public ResponseEntity<byte[]> downloadFile(String path, String typeStr, HttpServletRequest request){
    	//下载文件
    	ResponseEntity<byte[]> resultBoo = countService.downLoadFile(path,typeStr,request,countService);
    	return resultBoo;
    }
	
	/**
	 * 删除文件
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteFile", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteFile(HttpServletRequest request, String fileName, String fId) {
		String result = null;
		try {
		String path = request.getServletContext().getRealPath("/images/")+ File.separator+fileName;
		boolean booFile = countService.deleteFile(path);
		if(booFile){
			countService.delVer(fId);
			result = JSON.toJSONString(new Result(true, "200", "删除成功"));
		}else{
			result = JSON.toJSONString(new Result(false, "500", "删除失败"));
		}
		} catch (Exception e) {
			// TODO: handle exception
			result = JSON.toJSONString(new Result(false, "500", e.toString()));
		}
		
		return result;

	}
	
	/**
	 * 打开上传页面路径
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String downFile(HttpServletRequest request) {

		return "web/baVersion_list";
	}

	/**
	 * 工具类
	 * @param string
	 * @return
	 */
	private boolean err0(String string) {
		// TODO Auto-generated method stub
		stringRtrue = string;
		return true;
	}

}
