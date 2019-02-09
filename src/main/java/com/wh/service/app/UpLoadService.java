package com.wh.service.app;

import com.wh.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UpLoadService {

	
	/**
	 * 创建文件夹
	 * @param request
	 * @param file
	 * @return
	 */
	Result createFile(HttpServletRequest request, MultipartFile file);

	/**
	 * 删除文件
	 * @param path
	 * @return
	 */
	Boolean deleteFile(String path);
}
