package com.wh.controller.app;

import com.alibaba.fastjson.JSON;
import com.wh.entity.BaVersionInfo;
import com.wh.entity.Result;
import com.wh.service.app.UpLoadService;
import com.wh.service.base.BaseTermQueryService;
import com.wh.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Controller
@RequestMapping("/upLoadController")
public class UpLoadController {

	@Resource(name ="UpLoadServiceImpl")
	private UpLoadService countService;
	String stringRtrue;

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
	public String uploadFile(HttpServletRequest request,@RequestParam(value="file",required=false) MultipartFile file,String base64) {
		Result result = null;
		// 如果文件不为空，写入上传路径
		if (file!=null&&!file.isEmpty()) {
			//文件有没有上传成功
			result = countService.createFile(request, file);

		}else if(StringUtils.isNotEmpty(base64)) {
			MultipartFile file3 = FileUtil.base64ToMultipart(base64);
			result = countService.createFile(request, file3);

		}else{
			result = new Result(false,"上传图片文件为空");
		}
		return JSON.toJSONString(result);
	}

	/**
	 * 获取路径
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/getpath", method = RequestMethod.GET,  produces = {"application/vnd.ms-excel;charset=UTF-8"})
	public String getpath( HttpServletResponse response,String pathImage) throws IOException {
		String dictList = BaseTermQueryService.getDictList("10").get(0).get("pathImage");
		String path = dictList+pathImage;
		response.setContentType("image/jpeg/jpg/png/gif/bmp/tiff/svg"); // 设置返回内容格式
		path=new String(path.getBytes("ISO-8859-1"),"UTF-8");
		File file = new File(path);       //括号里参数为文件图片路径
		if(file.exists()){   //如果文件存在
			InputStream in = new FileInputStream(path);   //用该文件创建一个输入流
			OutputStream os = response.getOutputStream();  //创建输出流
			byte[] b = new byte[1024];
			while( in.read(b)!= -1){
				os.write(b);
			}
			in.close();
			os.flush();
			os.close();
			return os.toString();
		}
		return null;
	}


	/**
	 * 删除文件
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteFile", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteFile(HttpServletRequest request) {
		String result = null;
		try {
			Map<String,Object> params=(Map<String, Object>) request.getAttribute("params");
			String dictList = BaseTermQueryService.getDictList("10").get(0).get("pathImage");
			if(StringUtils.isBlank((String)params.get("f_imgurl"))){
				boolean booFile = countService.deleteFile(dictList+params.get("f_imgurl"));
				if(booFile){
					result = JSON.toJSONString(new Result(true, "200", "图片删除成功"));
				}else{
					result = JSON.toJSONString(new Result(false, "500", "图片删除失败"));
				}
			}else {
				result = JSON.toJSONString(new Result(true, "200", "没有图片成功"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			result = JSON.toJSONString(new Result(false, "500", e.toString()));
		}

		return result;

	}



}
