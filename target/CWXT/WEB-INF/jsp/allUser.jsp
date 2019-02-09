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
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>数据详情</title>
    <style type="text/css">
        *{
            font-family: Arial,"Microsoft Yahei";
        }
        html,body{
            height:100%;
            overflow:hidden;
        }
        h6{
        	text-align: right;
    		padding: 10px 20px 0 0;
    		margin-bottom: 10px;
        }
        h6>a{
        	text-decoration: none;
		    width: 86px;
		    height: 34px;
		    display: inline-block;
		    line-height: 34px;
		    text-align: center;
		    background: rgb(227,163,8);
		    color: #fff;
		    border-radius: 3px;
        }
        .table{
		    height: calc(100% - 68px);
		    height: -webkit-calc(100% - 68px);
		    height: -moz-calc(100% - 68px);
		    overflow-x: auto;
		    overflow-y: hidden;
		    border: 1px solid #d8d8d8;
		    border-top: 1px solid transparent;
        }
        table{
            border-collapse: collapse;
		    border: 1px solid #eee;
		    width: 100%;
		    table-layout: fixed;
		    font-size:14px;
        }
        .thead table{
        	border:1px solid transparent;
        }
        thead{
            color: #fff;
        }
        .thead{
        	background: rgb(0,169,251);
        	padding-right:17px;
        }
        tr{
            border-top: 1px solid #eceaea;
            text-align: left;
            height: 40px;
        }
        tbody>tr:nth-of-type(odd){background:#FFF;}/*奇数行*/
		tbody>tr:nth-of-type(even){background:#F5F5F5;}/*偶数行*/
		tbody>tr:hover{
			background:rgb(234,242,255);
		}
        th, td {
            padding: 0 10px;
		    width: 150px;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		    overflow: hidden;
		    border-left:1px solid #eceaea;
        }
        thead>tr:before{
        	content:"";
        	display:inline-block;
        	width:30px;
        	height:36px;
        	line-height:36px;
        }
        tbody{
        	counter-reset:number;
        }
        tbody>tr{
        	counter-increment:number;
        }
        tbody>tr:before{
        	content: counter(number);
		    display: inline-block;
		    width: 30px;
		    height: 36px;
		    line-height: 36px;
		    text-align: center;
        	
        }
        .tbody{
        	height: calc(100% - 40px);
    		overflow-y: auto;
        }
    </style>
	<script type="text/javascript">
	function del(f_Id){
		$.ajax({
	        type: "post",
	        url: "<%=basePath%>mainController/delBookflowF_Id",
	        dataType: "text",
	        data : {"f_Id":f_Id},
	        async:true,
	        success: function (data){
        		//后台返回结果
        		var JSONRes = JSON.parse(data);
	            if(JSONRes.success){
	            	alert("删除成功请刷新页面");
	               	return true;
	            }else{
	            	alert("删除失败");
	                return false;
	            }
	        }
		        
		 });
	}
</script>
  </head>
  
  <body>
    <h6><a href="<%=basePath%>userController/userInfo">添加用户</a>&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>baVersionController/downFile">发布应用</a></h6>
    <div class="table">
    	<div class="thead">
			<table border="0">
				<thead>
		            <tr>
		            	<th style="width: 170px;">名称</th>
		                <th style="width:90px">金额(元)</th>
		                <th style="width:74px">业务类型</th>
		                <th style="width:86px">账户类型</th>
		                <th style="width:86px">消费类别</th>
		                <th>发生时间</th>
		                <th style="width:220px">备注</th>
		                <th style="width:80px">操作</th>
		                <th>图片路径</th>
		            </tr>
		        </thead>
		     </table>
		 </div>
	     <div class="tbody">
		     <table border="0">
		        <tbody>
					<c:if test="${!empty BsBookFlow }">
						<c:forEach items="${BsBookFlow}" var="bookFlow">
							<tr>
								<td style="width: 170px;">${bookFlow.f_Caption }</td>
								<td style="text-align:right;width:90px">${bookFlow.f_Money }</td>
								<td style="width:74px">${bookFlow.f_Direction_type }</td>
								<td style="width:86px">${bookFlow.f_Account_type }</td>
								<td style="width:86px">${bookFlow.f_Consume_type }</td>
								<td>${bookFlow.f_Date }</td>
								<td style="width:220px">${bookFlow.f_Note }</td>
								<td style="text-align:center;width:80px">
									<a href="<%=basePath%>mainController/getBookflowf_Id?f_Id=${bookFlow.f_Id}">编辑</a>
									<a href="javascript:del('${bookFlow.f_Id}')">删除</a>
								</td>
								<td>${bookFlow.f_Imgurl }</td>
							</tr>				
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
  </body>
</html>
