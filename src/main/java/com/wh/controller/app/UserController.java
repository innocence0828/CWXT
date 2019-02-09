package com.wh.controller.app;

import com.alibaba.fastjson.JSON;
import com.wh.entity.Result;
import com.wh.entity.UserInfo;
import com.wh.service.app.UserService;
import com.wh.sys.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;

@Controller
@RequestMapping("/userController")
public class UserController {
	@Autowired
	private UserService userService;
	String stringRtrue;
	
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request) {

		return "/login";
	}
	
	/**
	 * 跳转到登陆页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/actionLogin")
	public String actionLogin(HttpServletRequest request) {

		return "base/login";
	}
	
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/userInfo")
	public String userInfo(HttpServletRequest request) {

		return "/addUser";
	}
	/**
	 * 登录模式
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String login(UserInfo userInfo, HttpServletRequest request){
		HttpSession session=request.getSession();
		PrivateKey privateKey=(PrivateKey)session.getAttribute(Contents.RSA_PRIVATE_KEY);
		session.removeAttribute(Contents.RSA_PRIVATE_KEY);
		try{
			String password = userService.findLogin(userInfo.getF_UserId());
			if (userInfo.getF_UserId() != null && userInfo.getF_PassWord() != null) {
				if (userInfo.getF_PassWord().equals(password)) {
					//获取用户信息
					UserInfo userInfo2 = userService.getUserInfo(userInfo.getF_UserId());
					Result result = new Result(true, "200", "登录成功".trim(),userInfo2);
					session.setAttribute(Contents.USER_SESSION, userInfo2);
					return JSON.toJSONString(result);
				}else if(password==null){
					return JSON.toJSONString(new Result(false, "500", "用户名不存在"));
				}else{
					return JSON.toJSONString(new Result(false, "500", "密码错误"));
				}
			}
		}catch(Exception e){
			return JSON.toJSONString(new Result(false, "404", "数据库连接失败"));
		}finally{
			
		}
		return JSON.toJSONString(new Result(false, "505", "登录失败"));
	}
	
	/**
	 * 注册模式
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/reg", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String reg(UserInfo userInfo, HttpServletRequest request) {
		String result =null;

		boolean regflag = userInfo.getF_UserId() == null && err0("请输入用户名")
				|| (userInfo.getF_PassWord() == null && err0("请输入密码"));
		//提示信息
		if (regflag) {
			result = JSON.toJSONString(new Result(false, "500", stringRtrue));
		}else{
			// 判断用户注册没有
			String password = userService.findLogin(userInfo.getF_UserId());
			if (password != null) {
				result = JSON.toJSONString(new Result(false, "500", "该用户已注册"));
			}else{
				try {
					result = JSON.toJSONString(userService.insertReg(userInfo));

				} catch (Exception e) {
					// TODO: handle exception
					result = JSON.toJSONString(new Result(false, "500", e.toString()));
				} 
			}
		}
			return result;
		
	}
	
	/**
	 * 信息注册维护
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/updateInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateInfo(UserInfo userInfo, HttpServletRequest request) {
		try {
		boolean regflag = userInfo.getF_UserId() == null && err0("用户名为空");
		//提示信息
		if (regflag) {
			return JSON.toJSONString(new Result(false, "500", stringRtrue));
		}
		userService.getUpdateUserInfo(userInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return JSON.toJSONString(new Result(false, "500", e.toString()));
		} 
			return JSON.toJSONString(new Result(true, "500", "信息添加成功"));

	}
	
	/**
	 * SMS短信验证码
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/smsPhoneCode", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String SmsPhoneCode(String phoneStr, HttpServletRequest request) {
		String result = null;
		try {
			boolean regflag = phoneStr == null && err0("手机号不为空");
			//提示信息
			if (regflag) {
				result = JSON.toJSONString(new Result(false, "500", stringRtrue));
			}else{
				// 判断用户注册没有
				String password = userService.findLogin(phoneStr);
				if (password != null) {
					result = JSON.toJSONString(new Result(false, "500", "该手机号已注册"));
				}else{
					result = JSON.toJSONString(userService.SmsPhoneCode(phoneStr));
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			result = JSON.toJSONString(new Result(false, "500", e.toString()));
		} 
			return result;

	}
	


	private boolean err0(String string) {
		// TODO Auto-generated method stub
		stringRtrue = string;
		return true;
	}

}
