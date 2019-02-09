<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
	<title>数据选择</title>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ include file="/WEB-INF/views/include/inc_ztree.jsp"%>
	<script type="text/javascript">
		var postUrl='${postUrl}';
		var selectIds='${selectIds}';
		var multi=${multi};
		var level=${level};
	</script>
	<script src="${ctxStatic}/views/common/com_TreeS.js"></script>
</head>
<body>
	<div style="position:absolute;right:8px;top:10px;cursor:pointer;" onclick="search()">
		<i class="icon-search"></i><label id="txt">搜索</label>
	</div>
	<div id="search" class="control-group" style="padding:10px 0 0 15px;">
		<label for="key" class="control-label" style="float:left;">关键字：</label>
		<input type="text" class="empty" id="key" name="key" maxlength="50" style="width:160px;"/>
	</div>
	<div id="tree" class="ztree" style="padding:15px 20px;"></div>
</body>