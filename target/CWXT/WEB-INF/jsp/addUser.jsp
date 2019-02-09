<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加用户</title>
    
	<script type="text/javascript">
	function addUser(){
		var form = document.forms[0];
		form.action = "<%=basePath%>mainController/addBookFlow";
		form.method="post";
		form.submit();
	}
</script>

  </head>
  
  <body>
    <h1>添加用户</h1>
	<form action="" name="userForm">
	ID：<input type="text" name="f_Id">
		日期：<input type="text" name="f_Date">
		业务类型：<input type="text" name="f_Direction_type">
		备注：<input type="text" name="f_Note">
		金额：<input type="text" name="f_Money">
		名称：<input type="text" name="f_Caption">
		图片路径：<input type="text" name="f_Imgurl">
		账户类型：<input type="text" name="f_Account_type">
		消费类别：<input type="text" name="f_Consume_type">
		<input type="button" value="添加" onclick="addUser()">
	</form>
  </body>
</html>
