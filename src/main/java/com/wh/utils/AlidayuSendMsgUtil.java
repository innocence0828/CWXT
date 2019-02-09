package com.wh.utils;


import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
public class AlidayuSendMsgUtil {

	//这个方法是天元短信接口
	public  String sedMsg(String phoneStr,String phoneCode) {
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "23777942";
		String secret ="cefc8420d74b612a4860699061080f3e";

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		 //实例化请求
		 AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		 //公共回传参数，在消息返回中会回传该参数
		 req.setExtend(phoneCode);
		 //短信类型
		 req.setSmsType("normal");
		 //短信签名
	     req.setSmsFreeSignName("天元易通电商");
         //短信模板变量SmsParamString 传递规则 "{key:value}"
	     String smsParams = "{\"code\":\""+phoneCode+"\",\"product\":\"文荷工作室\"}";
         req.setSmsParamString(smsParams);
         //接受的手机号码，可以是一个也可以是多个， 如果是多个手机号，则用逗号进行分割
	     req.setRecNum(phoneStr);
	     //短信模板id
	     req.setSmsTemplateCode("SMS_64200034");
		     AlibabaAliqinFcSmsNumSendResponse rsp =null;
		    try {
				rsp = client.execute(req);
				System.out.println(rsp.getBody());
				return rsp.getBody();
			} catch (ApiException e) {
				e.printStackTrace();
			}
		    return rsp.getBody();

		}


	    public static void main(String[] args) {

			   System.out.println("========="+new AlidayuSendMsgUtil().sedMsg("15299100579","224567"));

		}

}
