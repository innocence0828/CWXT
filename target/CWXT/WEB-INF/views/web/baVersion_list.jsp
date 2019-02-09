<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>文件上传</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="utf-8" />
<title>APP托管平台</title>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" href="https://static.pgyer.com/static-20170503/assets/plugins/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="${ctxStatic}/global/layout/css/baVersion_list.css" />
</head>

<body>
	<div class="main">
		<div class="header">
			<h2>APP托管平台</h2>
		</div>
		<div id="list-box" class="show">
			<!--顶部上传文件-->
			<div class="main-top">
				<p>
					<button class="btn-u btn-u-lg" id="uploadButton">
						<i class="fa fa-cloud-upload"></i>立刻上传
					</button>
				</p>
			</div>

			<!--底部展示列表-->
			<div class="main-bottom">
				<ul class="ul-header">
					<li><span>标题</span><span>文件名</span><span>版本号</span><span>创建时间</span>
					</li>
				</ul>
				<ul class="ul-body">

				</ul>
			</div>
		</div>
		<div id="form-box" class="hide">
			<ul>
				<li><span>文件标题*</span><input type="text" placeholder="请输入标题名"
					id="fileTitle"></li>
				<li><span>版本号*</span><input type="text" placeholder="请输入版本号"
					id="version"></li>

				<li><span>上传文件*</span><input type="file" name="file" id="file_upload"/>
				</li>
				
				<li style="padding-left: 321px;">
					<button class="btn-u" type="submit" id="cancelButton">取消</button>
					<button class="btn-u" type="submit" id="submitButton">发布应用</button>
				</li>
			</ul>
		</div>
	</div>
</body>
<script src="https://cdn.wilddog.com/js/client/current/wilddog.js"></script>
<script type="text/javascript" src="http://oopkl7bcv.bkt.clouddn.com/commMethod.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript">
	var cancelButton = document.getElementById("cancelButton");
	var uploadButton = document.getElementById("uploadButton");
	var submitButton = document.getElementById("submitButton");
	var form_box = document.getElementById("form-box");
	var list_box = document.getElementById("list-box");
	var fileTitle = document.getElementById("fileTitle");
	var version = document.getElementById("version");
	var file_upload = document.getElementById("file_upload");
	var fileStream = "";
	var html = "";
	cancelButton.addEventListener("click", function() {
		form_box.className = "hide";
		list_box.className = "show";
	});
	uploadButton.addEventListener("click", function() {
		form_boxStyle.show();
		list_box.className = "hide";
	});

	//恢复上传文件容器内组件的样式
	var form_boxStyle = {
		show : function() {
			form_box.className = "show";
			fileTitle.value = "";
			version.value = "";
			file_upload.value = "";
			//恢复按钮状态
			submitButton.disabled = false;
			submitButton.style.backgroundColor = '#1abc9c';
			submitButton.style.cursor = 'pointer';
		}
	}
	//查询信息
	$(document) .ready( function() {
			$.ajax({
		        type: "post",
		        url: "<%=basePath%>baVersionController/verquery",
		        dataType: "text",
		        async:true,
		        success: function (data){
	        		//后台返回结果
	        		var JSONRes = JSON.parse(data);
		            if(JSONRes.success){
		            	loadVersionHisLog(JSONRes.list);
		               	return true;
		            }else{
		            	errorTip(JSONRes.msg);
		            	
		                return false;
		            }
		        }
		        
		     });
		});

	$("#file_upload") .change( function(obj) {
						var $file = $(this);
						fileValue = $("#file_upload").val(); //文件名
						fileValue = fileValue.replace("C:\\fakepath\\", "");
						var endName = fileValue.substr(
								(fileValue.lastIndexOf(".") + 1)).toLowerCase();//获得文件后缀名
						var isLegal = endName == "apk";
						if (!isLegal) { //如果用户选择的文件不是apk类型
							$("#file_upload").val('');
							alert("请选择apk文件！");
							return false;
						} else { //用户选择的文件是正确的文件类型
							var fileObj = $file[0];
							var file = fileObj.files[0];
							var reader = new FileReader(); //File API
							reader.onloadend = function() { //文件加载完毕
								if (reader.error) { //文件读取错误
									console.log(reader.error);
								} else { //文件读取成功
									
									fileStream = reader.result; //获取文件的base64编码
									fileStream = fileStream
											.substring(fileStream
													.indexOf("base64") + 7); //去除编码前面的文字说明
									fileName = fileValue; //设置文件名
								}
							}
							reader.readAsDataURL(file); //读取文件内容，读取结果为一个 data: 的 URL
						}
					});

	//发布应用
	submitButton .addEventListener( "click", function() {
						if (!fileTitle.value || !version.value) {
							alert("请填写" + (!fileTitle.value ? "文件名!" : "版本号!"));
							return false;
						}
						if (!file_upload.value) {
							alert("请上传文件!");
							return false;
						}
						//设置按钮状态为不可点击
						submitButton.disabled = "disabled";
						submitButton.style.backgroundColor = '#b7c1bf';
						submitButton.style.cursor = 'not-allowed';

						//上传文件名
						var toqiniuKey = "cwxt_v" + (version.value).replace('.', '');
						//	var waiting = plus.nativeUI.showWaiting("正在上传");
						var url ="<%=basePath%>baVersionController/uploadFile";  
						//alert($("#file_upload")[0].files[0]+"=="+version.value+"=="+fileTitle.value+"=="+url);
						 
						 $.ajaxFileUpload({
			                url : "<%=basePath%>baVersionController/uploadFile",
			                secureuri : false,
			                fileElementId : 'file_upload',
			              	beforeSend:loading(),
			                dataType : 'text',//此时指定的是后台需要返回json字符串,前端自己解析,可以注释掉.后台直接返回map
			                data : {"f_Vertitle":fileTitle.value,"f_Verid":version.value},
			                success : function(data) {
			                    //后台返回结果
				        		var JSONRes = JSON.parse(data);
					            if(JSONRes.success){
					            $("#file_upload") .val("");
					             //alert(JSONRes.msg);
						             loadVersionHisLog(JSONRes.list);
						             form_box.className = "hide";
									 list_box.className = "show";
									 removeLoading();
					            }else{
					            	alert(JSONRes.msg);
					            	removeLoading();
					            }
			                    
			                },
			                error : function(data, status, e) {
			                removeLoading();
			                     //后台返回结果
				        		var JSONRes = JSON.parse(data);
					            if(JSONRes.success){
					             alert(JSONRes.msg);
					            }else{
					            	alert(JSONRes.msg);
					            }
			                }
			            });
						
					
					});
					
	
		function addUser(){
		var form = document.forms[0];
		form.action = "<%=basePath%>userController/addUser";
		form.method="post";
		form.submit();
	}

	/**
	* 加载版本历史记录信息
	*/
	function loadVersionHisLog(datas){
		var html = "";
		$.each(datas,function(i,obj){
			html += "<li key="
				+ (i+1)
				+ "><span>"
				+ datas[i].f_Vertitle
				+ "</span><span>"
				+ datas[i].f_Filename
				+ "</span><span>"
				+ datas[i].f_Verid
				+ "</span><span>"
				+ datas[i].f_Crttime
				+ "</span><span><a href="+datas[i].f_Url+">下载</a><a onclick=\""
				+"changetext(this,'"+datas[i].f_Url+"','"+datas[i].f_Id+"')\">删除</a></span></li>";
		});
		$(".ul-body").empty().append(html);
	}
	
	/**
	* 删历史记录信息
	*/
	function changetext(self,furl ,fid) {
		var filename =furl.substring(furl.lastIndexOf("path=")+5);
			$.ajax({
		        type: "post",
		        url: "<%=basePath%>baVersionController/deleteFile",
		        dataType: "text",
		        data : {"fileName":filename,"fId":fid},
		        async:true,
		        success: function (data){
		       
	        		//后台返回结果
	        		var JSONRes = JSON.parse(data);
		            if(JSONRes.success){
		            	 //alert(JSONRes.msg);
		            	 $(self).parents("li").remove();
		               	return true;
		            }else{
		            	errorTip(JSONRes.msg);
		            	
		                return false;
		            }
		        }
		        
		     });
		}
		//加载滚动条
		function loading() {
			if ($("#cu_loadding").length <= 0) {
				var html = "", path ="<%=basePath%>images/ajaxloading.gif";
				html += "<div id='cu_loadding' class='loading' style='width:100%;height:100%'></div><img id='img' src='"
						+ path
						+ "' style='position:absolute;z-index:201;top:45%;bottom:50%;left:50%';right:50%>";
				$("body").append(html);
			}
		
		}
		// 取消动图
		function removeLoading() {
		//	alert(1);
			var loadding = $("#cu_loadding");
			if (loadding.length > 0) {
				loadding.next().remove();
				loadding.remove();
			}
		}
	
</script>
</html>
