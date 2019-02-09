<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ include file="/WEB-INF/views/include/inc_treetable.jsp"%>
	<link href="${ctxStatic}/global/plugins/jqGrid/ui.jqgrid.min.css" rel="stylesheet" type="text/css" />
	<script src="${ctxStatic}/views/base/menu_list.js"></script>
</head>
<body>
	<div class="portlet light" style="margin-bottom:0px;"> 
		<button class="btn btn-success" onclick="query()" title="重新加载"><i class="fa fa-refresh"></i> 重新加载</button>		
		<button class="btn btn-success" onclick="openInput('菜单添加','')" title="菜单添加"><i class="fa fa-plus-circle"></i> 菜单添加</button>
		<button class="btn btn-success" onclick="save_sort()" title="保存排序"><i class="fa fa-save"></i> 保存排序</button>
	</div>
	<table id="treeTable" class="table table-striped table-bordered table-condensed"></table>
</body>
</html>