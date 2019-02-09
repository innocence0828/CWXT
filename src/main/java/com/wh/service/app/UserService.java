package com.wh.service.app;

import com.wh.entity.Result;
import com.wh.entity.UserInfo;

public interface UserService {
	
	/**
	 * 登录
	 * @param f_UserId
	 * @return
	 */
	String findLogin(String f_UserId);
	/**
	 * 注册
	 * @param userInfo
	 */
	@SuppressWarnings("rawtypes")
	Result insertReg(UserInfo userInfo);
	
	
	/**
	 * 注册手机短信验证
	 * @param phoneStr
	 * @throws
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	Result SmsPhoneCode(String phoneStr);
	
	/**
	 * 获取用户信息
	 * @param fUserId
	 * @return
	 */
	UserInfo getUserInfo(String fUserId);
	/**
	 * 更新用户信息
	 * @param userInfo
	 */
	void getUpdateUserInfo(UserInfo userInfo);



	



	
}
