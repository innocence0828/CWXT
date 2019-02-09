<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
  <head>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
    <base href="<%=basePath%>">
    <title>财务系统登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <style type="text/css">
        *{
            font-family: "微软雅黑";
        }
        html,body,.main{
            height:100%;
            margin:0px;
            padding:0px;
        }
        .main{
            background: url("images/background.jpg") no-repeat center/100%;
            -webkit-filter: blur(0px);
            -moz-filter: blur(0px);
            -ms-filter: blur(0px);
            filter: blur(0px);
        }
        .login-box{
            width: 393px;
		    height: 380px;
		    background: rgba(255, 255, 255, 0.88);
		    box-shadow: 0px 0px 7px 2px rgba(84, 84, 84, 0.4);
		    border-radius: 3px;
		    margin: auto;
		    position: absolute;
		    top: -20px;
		    right: 125px;
		    bottom: 0px;
        }
        .header{
            text-align: center;
		    font-size: 26px;
		    color: #6b6b71;
		    font-weight: 600;
		    padding: 10px;
		    margin-bottom: 10px;
        }
        .split-line{
			font-size: 14px;
		    color: #999;
		    margin: 0 auto;
		    position: relative;
		    text-align: center;
		    margin-bottom: -4px;
        }
        .split-line:before{
            content: "";
		    border-top: 1px solid #d2d2d2;
		    display: block;
		    position: absolute;
		    width: 60px;
		    top: 8px;
		    left: 58px;
        }
        .split-line:after{
            content: "";
		    border-top: 1px solid #d2d2d2;
		    display: block;
		    position: absolute;
		    width: 60px;
		    top: 8px;
		    right: 58px;
        }
        .login-form{
            padding: 0px 38px;
		    margin: auto;
		    font-size: 18px;
        }
        .login-row span{
            display: block;
		    width: 74px;
		    color: #666666;
		    margin: 5px 20px;
		    font-size: 18px;
        }
        .login-row [type=text],[type=password]{
		    padding: 5px;
		    display: block;
		    margin: 0px 24px;
		    width: 260px;
		    height: 27px;
		    font-size: 14px;
		    border: 1px solid #b9b9b9;
		    border-radius: 5px;
		    box-sizing:content-box;
        }
        #loginBtn{
            width: 272px;
		    color: #fff;
		    background: rgb(51, 148, 210);
		    text-align: center;
		    border-radius: 33px;
		    margin: 22px;
		    cursor: pointer;
		    height: 46px;
		    line-height: 46px;
		    font-size: 20px;
        }
        #loginBtn:hover{
            background: #4280DE;
    		box-shadow: 0px 3px 5px rgba(187, 187, 187, 0.56);
        }
        .login-row:last-child{
            text-align: center;
        }
        .errBox{
        	position: fixed;
		    top: -60px;
		    background: rgb(239,169,73);
		    color: #fff;
		    width: 100%;
		    height: 60px;
		    line-height: 60px;
		    font-size: 18px;
		    text-align: center;

		    -webkit-transition:transform ease-in-out .2s;
		    -moz-transition:transform ease-in-out .2s;
		    transition:transform ease-in-out .2s;
        }
        #autoLogin{
        	margin: 16px 0px 5px 26px;
        }
        #autoLogin ~ span{
		    width: 85px;
        	font-size: 14px;
		    display: inline-block;
		    margin: 0px 0px 0px 5px;
        }
    </style>
  </head>
  
  <body>
	<div class="main"></div>
	<div class="login-box">
	    <div class="header">财务系统登录</div>
	    <div class="split-line">使用手机号/邮箱登录</div>
	    <div class="login-form">
	        <div class="login-row">
	            <span>用户名</span>
	            <input id="username"type="text" placeholder="请输入用户名">
	        </div>
	        <div class="login-row">
	            <span>密码</span>
	            <input id="password" type="password" placeholder="请输入密码">
	        </div>
	        <div class="login-row">
	            <input id="autoLogin" type="checkbox">
	            <span>下次自动登录</span>
	        </div>
	        <div class="login-row">
	            <span id="loginBtn" onclick="loginUser()">登录</span>
	        </div>
	    </div>
	</div>
  	<div class="errBox">请输入用户名</div>
    <script type="text/javascript">
    	//登录按钮事件
		function loginUser(){
			var username= document.getElementById("username").value;
			var password= document.getElementById("password").value;
			var url = "<%=basePath%>userController/login";
			$("#loginBtn").text("登录中...");
			//1、校验用户名密码是否为空
			var validFlag = !username && errorTip("请输入手机号或邮箱")||(!password && errorTip("请输入密码"));
		    !validFlag && $.ajax({
		        type: "post",
		        url: url,
		        data: {"f_UserId":username,"f_PassWord":password},
		        dataType: "text",
		        async:true,
		        success: function (data){
	        		//后台返回结果
	        		var JSONRes = JSON.parse(data);
		            if(JSONRes.success){
		            	location.href=ctx+'/main';
		            	//window.location.href = "<%=basePath%>mainController/getAllBookFlow";
		               	return true;
		            }else{
		            	errorTip(JSONRes.msg);
		            	$("#loginBtn").text("登录");
		                return false;
		            }
		        }
		        
		     });
		}
		
		//错误提示
		function errorTip(text){
			$(".errBox").text(text);
			$(".errBox").css({"transform":"translate(0px,60px)","-webkit-transform":"translate(0px,60px)","-moz-transform":"translate(0px,60px)"});
			setTimeout(function(){
				$(".errBox").css({"transform":"translate(0px,-60px)","-webkit-transform":"translate(0px,-60px)","-moz-transform":"translate(0px,-60px)"});
			},1500);
			return true;
		}
		
		//支持Enter键快速登录
		document.onkeydown = function(e){
			var e = e||event;
			var key = e.keyCode||e.which||e.charCode;
			if(key == "13"){
				$("#loginBtn").trigger("click");	
			}
		}
</script>
  </body>
</html>
