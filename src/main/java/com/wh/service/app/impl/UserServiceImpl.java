package com.wh.service.app.impl;

import com.alibaba.fastjson.JSON;
import com.wh.dao.UserDao;
import com.wh.entity.BsRegPhone;
import com.wh.entity.Result;
import com.wh.entity.UserInfo;
import com.wh.service.app.UserService;
//import com.wh.utils.AlidayuSendMsgUtil;
import com.wh.utils.AlidayuSendMsgUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao mapper;
	String stringRtrue;



	/**
	 * 登陆
	 */
	@Override
	public String findLogin(String f_UserId) {
		// TODO Auto-generated method stub
		return  mapper.loginId(f_UserId);
	}
	
	/**
	 * 注册
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Result insertReg(UserInfo userInfo) {
		// TODO Auto-generated method stub
		Result result =null;
		try {
			String regCode =mapper.queryRegPhone(userInfo.getF_UserId());
			boolean regflag = regCode==null && err0("请重新获取验证码")
					|| !regCode.equals(userInfo.getF_Code())&& err0("验证码错误");
			//提示信息
			if (regflag) {
				result =new Result(false, "500", stringRtrue);
			}else{
				mapper.insertReg(userInfo);
				result =new Result(false, "500", "注册成功");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			result =new Result(false, "500", "注册失败"+e.toString());
		}
		return result;
		
	}
	
	/**
	 *获取用户的信息
	 */
	@Override
	public UserInfo getUserInfo(String fUserId) {
		// TODO Auto-generated method stub
		return mapper.getUserInfo(fUserId);
	}
	
	/**
	 * 更新用户的信息
	 */
	@Override
	public void getUpdateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		 mapper.getUpdateUserInfo(userInfo);
	}
	


	/**
	 * 注册手机短信验证
	 * @param phoneStr
	 * @throws
	 * @throws InterruptedException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result SmsPhoneCode(String phoneStr)  {
		Result result = null;
		try{// TODO Auto-generated method stub
			String phoneCode =String.valueOf(Math.round(Math.random()*1000000));
//			//发短信
			String jsonStr = new AlidayuSendMsgUtil().sedMsg(phoneStr, phoneCode);
			  Map<String,Object> map=(Map) JSON.parse(jsonStr);
			  Map<String,Object> dataMap=(Map<String, Object>)
					  map.get("alibaba_aliqin_fc_sms_num_send_response");
			  Map<String,Object> dataMap2=(Map<String, Object>) dataMap.get("result");
			  boolean dataMap3= (Boolean) dataMap2.get("success");
			  if(dataMap3){
        	    result = new Result(true, "200", "验证码发送成功");

        	    String regCode =mapper.queryRegPhone(phoneStr);
        	    if(regCode!=null){
        	    	//更新regphone
        	    	mapper.updateRegPhone(new BsRegPhone(phoneStr,phoneCode));
        	    }else{
        	    	 // 添加到regphone;
            	    mapper.addRegPhone(new BsRegPhone(phoneStr,phoneCode));
        	    }

			  }else{
				 result = new Result(false, "500", "短信发送失败");
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(false, "500", "发送频率太多");
			System.out.println(e.toString());
		}
        return result;

	}
	
	private boolean err0(String string) {
		// TODO Auto-generated method stub
		stringRtrue = string;
		return true;
	}
	
	public static void main(String[] args) {
			
		
	}
}
