<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html style="overflow-x: hidden; overflow-y: auto;">
<head>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ include file="/WEB-INF/views/include/inc_jqValidation.jsp"%>
	<script src="${ctxStatic}/views/base/menu_input.js"></script>
</head>
<body>
	<div class="portlet light bordered">
		<div class="portlet-body form">
			<form id="inputForm" class="form-horizontal" method="post">
				<input id="id" name="id" class="form-control" type="hidden" value="${menu.id}"/>
				<div class="form-body">
					<div class="form-group" id="parentDiv">
						<div class="col-md-6">
							<label class="col-md-3 control-label">上级菜单:</label>
							<div class="col-md-9">
								<div class="input-group required input-medium">
									<input id="pid" name="pid" class="form-control input-medium" type="hidden" value="${menu.pid}"/>
									<input id="pname" readonly="readonly" type="text" 
										class="form-control" placeholder="请选择上级菜单" value="${menu.pname}"/>	
								    <span class="input-group-btn">
								        <button style="padding: 9px 12px;" class="btn blue" type="button" onclick="sel_pid()">
								        	<i class="fa fa-search"></i>
								        </button>
								    </span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<label class="col-md-3 control-label">菜单名称:</label>
							<div class="col-md-9">
								<input id="fs_name" name="fs_name" value="${menu.fs_name}" placeholder="请输入菜单名称" class="form-control input-medium" type="text" maxlength="100" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
						<label class="col-md-3 control-label">菜单链接:</label>
						<div class="col-md-9">
							<input id="fs_url" name="fs_url" value="${menu.fs_url}" class="form-control input-medium" type="text" placeholder="请输入链接" maxlength="100"/>
						</div>
						</div>
						<div class="col-md-6">
					<label class="col-md-3 control-label">排序序号:</label>
						<div class="col-md-9">
							<input id="fi_sort" name="fi_sort" value="${menu.fi_sort}" class="form-control input-medium" type="text" placeholder="请输入序号"/>
						</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<label class="col-md-3 control-label">菜单图标:</label>
						<div class="col-md-6">
							<i id="iconi" class="" style="line-height: 2.44;"></i>
							<label id="iconl">${menu.fs_icon}</label><a class="btn btn-default btn-sm" onclick="sel_icon()">选择</a>
							<input id="fs_icon" name="fs_icon" type="hidden" class="form-control" value="${menu.fs_icon}"/>
						</div>
						
						</div>
					</div>
					
				</div>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-4 col-md-4 text-center" >
							<button type="button" class="btn btn-primary" onclick="save()" title="保 存"><i class="fa fa-save"></i> 保 存</button>
						</div>
					</div>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>
