<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%response.setStatus(200);%>
<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
%>
<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<%-- <%@include file="/WEB-INF/views/include/dialog.jsp" %> --%>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>系统发生内部错误.</h1></div>
		<p>错误信息：</p><p>
		<%
			if (ex!=null){
				out.print(ex+"<br/>");
			}
		%>
		</p>
		<div><a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a></div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
</body>
</html>