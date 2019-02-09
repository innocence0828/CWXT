package com.wh.sys;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.wh.entity.UserInfo;
import com.wh.utils.Des;

/**
 * springmvc拦截器
 * @author BOSS
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	/**
	 * 跳过登入验证的url
	 */
	private List<String> excludeURL;
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler) throws Exception {
    	String url=request.getRequestURI();
    	Map<String, String[]> paramsMap=request.getParameterMap();
    	Map<String,Object> params=new HashMap<String,Object>();
    	Set<String> keys=paramsMap.keySet();
    	for(String key:keys) {
    		String [] values=paramsMap.get(key);
    		StringBuffer value=new StringBuffer();
    		if(values.length>1) {
    			for(int i=0;i<values.length;i++) {
    				if(i==0) {
    					value.append(values[i]);
    				}else {
    					value.append(","+values[i]);
    				}
    			}
    		}else {
    			value.append(values[0]);
    		}
    		params.put(key, value.toString());
    	}
    	System.out.println("\n请求地址："+url+"\n请求参数："+params);
    	for(String s:excludeURL) {
    		if(url.contains(s)) {
    			return true;
    		}
    	}
    	//app加密算法
    	if(params.get("desKey")!=null&&!params.get("desKey").toString().equals("")){
    		long appDecKey = Long.parseLong(new Des().strDec(params.get("desKey").toString(), "1", "2", "3"));
            long javaDesKey = System.currentTimeMillis();
//			if((javaDesKey-appDecKey)>100000){
//    			System.out.println("重新请求Url");
//    			params.put("desKeyBoolean", false);
//    			params.put("desKeyMsg", "重新请求Url");
//    			return false;
//    		}
    		
    	}else{

    		//web前端
    		UserInfo user=(UserInfo) request.getSession().getAttribute(Contents.USER_SESSION);
        	if(user==null||(user.getF_Id()==null||StringUtils.isEmpty(String.valueOf(user.getF_Id())))) {//web用户验证
				if (request.getHeader("x-requested-with") != null
						&& request.getHeader("x-requested-with")
								.equalsIgnoreCase("XMLHttpRequest")) {
					response.addHeader("sessionstatus", "timeOut");
					response.addHeader("loginPath", request.getContextPath()
							+ "/userController/actionLogin");
				} else {
					String str = "<script language='javascript'>alert('会话过期,请重新登录');"
							+ "window.top.location.href='"
							+ request.getContextPath()
							+ "/userController/actionLogin" + "';</script>";
					response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
					try {
						PrintWriter writer = response.getWriter();
						writer.write(str);
						writer.flush();
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
        		response.sendRedirect(request.getContextPath()+"/userController/actionLogin");
        		return false;
        	}
        	params.put(Contents.USER_PARAM, user);
        	params.put(Contents.USER_ID, user.getF_UserId());
    	}
    	request.setAttribute("params", params);
    	return true;
    }  
  
    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    }

	public List<String> getExcludeURL() {
		return excludeURL;
	}

	public void setExcludeURL(List<String> excludeURL) {
		this.excludeURL = excludeURL;
	}
	public static void main(String[] args) throws Exception {
//		String mw="123";
//		md5(mw)+des(mw);
//		des(des(mw))==mw
//		String appToken="72fc3c5fd3dd5989f5ed7b3a3e9fcec46Kg7lcxs1qD3/rjDp4N5C5b+o+QXPFb9QplYjje9Ahs=";
//		String MD5Sign=appToken.substring(0, 32);
//		String DESToken=appToken.substring(32);
//		String token=DES.decryptDES(DESToken, "AppToken");
//		202d54cd383e2e2e2895ca5e2b5a452f6Kg7lcxs1qD3/rjDp4N5CwsUeurpVhYd0cSXUm0kkPU=
//		admin_123456_1513930490332
//		202d54cd383e2e2e2895ca5e2b5a452f
//		202d54cd383e2e2e2895ca5e2b5a452f
//		cf3495bba56048ac42c845a62d0af7c1
//		System.out.println(MD5Sign.equals(MD5.encrypt32(token)));
	}
}
