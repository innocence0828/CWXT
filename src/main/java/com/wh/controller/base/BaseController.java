package com.wh.controller.base;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


@Controller
@RequestMapping("/baseController")
public class BaseController {
//	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * Excel文件下载
	 * @param request
	 * @param response
	 * @param path 文件id
	 * @param name 文件名
	 * @throws Exception
	 */
	@RequestMapping(value = "/downExcel", produces = "application/json; charset=utf-8")
	public void downExcel(HttpServletRequest request, HttpServletResponse response, String path, String name) throws Exception {
		downLoad(request,response,request.getServletContext().getRealPath("/images/")+path+".xls",java.net.URLDecoder.decode(name,"UTF-8"));
	}
	public void downLoad(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) throws Exception {
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		final String userAgent = request.getHeader("USER-AGENT");
		try {
			// 中文文件名支持
			String finalFileName = null;
			if (StringUtils.contains(userAgent, "MSIE")) {
				finalFileName = URLEncoder.encode(fileName, "UTF8");
			} else if (StringUtils.contains(userAgent, "Mozilla")) {
				finalFileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(fileName, "UTF8");
			}
			response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");
		} catch (UnsupportedEncodingException e) {
//			log.error("Header设置异常",e);
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0) out.write(buf, 0, len);
		br.close();
		out.close();
		if(f.delete()) {
			System.gc();
			f.delete();
		}

	}
}
