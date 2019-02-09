package com.wh.service.web;

import com.wh.entity.BaVersionInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BaVersionService {
	
	/**
	 * 插入版本更新表数据
	 * @param
	 * @return
	 */
	void verInertData(BaVersionInfo versionInfo);
	
	/**
	 * 查询版本更新信息
	 * @param
	 * @return
	 */
	List<BaVersionInfo> getVerQuery(String fileName);
	
	/**
	 * 删除版本更新信息
	 * @param
	 * @return
	 */
	void delVer(String f_Id);
	
	/**
	 * 下载路径
	 * @param
	 * @return
	 */
	String downQuery(String path);
	
	/**
	 * 创建文件夹
	 * @param request
	 * @param file
	 * @return
	 */
	Boolean createFile(HttpServletRequest request, MultipartFile file);
	
	/**
	 * 下载逻辑语句
	 * @param request
	 * @param countService
	 * @param
	 * @return
	 */
	ResponseEntity<byte[]> downLoadFile(String path, String typeStr, HttpServletRequest request, BaVersionService countService);
	
	/**
	 * 删除文件
	 * @param path
	 * @return
	 */
	Boolean deleteFile(String path);
	

	
}
