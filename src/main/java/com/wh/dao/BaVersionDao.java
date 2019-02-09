package com.wh.dao;

import com.wh.entity.BaVersionInfo;

import java.util.List;

public interface BaVersionDao {
	

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
	
}
