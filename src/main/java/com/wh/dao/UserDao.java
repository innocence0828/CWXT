package com.wh.dao;

import com.wh.entity.BsRegPhone;
import com.wh.entity.UserInfo;

public interface UserDao {
	
	/**
	 * 登录
	 * @param f_UserId
	 * @return
	 */
	String loginId(String f_UserId);
	/**
	 * 注册
	 * @param userInfo
	 */
	void insertReg(UserInfo userInfo);
	/**
	 * 获取用户的信息
	 * @param fUserId
	 * @return
	 */
	UserInfo getUserInfo(String fUserId);
	/**
	 * 更新用户的信息
	 * @param userInfo
	 */
	void getUpdateUserInfo(UserInfo userInfo);

	
	/**
	 * add加数据regPhone
	 * @param
	 */
	void addRegPhone(BsRegPhone bsRegPhone);
	
	/**
	 * 查询regPhone
	 * @param phoneStr 
	 * @return
	 */
	String queryRegPhone(String phoneStr);
	
	/**
	 * 更新regPhone
	 * @param
	 * @return
	 */
	void updateRegPhone(BsRegPhone bsRegPhone);

	
}
