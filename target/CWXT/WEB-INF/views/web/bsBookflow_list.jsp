<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ include file="/WEB-INF/views/include/inc_select2.jsp"%>
	<%@ include file="/WEB-INF/views/include/inc_datepicker.jsp"%>
	<%@ include file="/WEB-INF/views/include/inc_jqGrid.jsp"%>
	<script src="${ctxStatic}/views/web/bsBookflow_list.js?3"></script>  
	<script>
		//当前登录用户
		var loginId = "${fs_name}";
	</script>
</head>
<body>
  <div class="form-body breadcrumb" style="margin-bottom: 10px;">
		<form id="seachForm">
   	<div class="form-group">			
			<label style="margin-left:15px;">收入支出：</label>
				<select id="direction_type_sel" class="form-control select2 input-medium" style="display:inline;"></select>
				<input type="hidden" name="f_Direction_type" id="f_Direction_type" />
			<label style="margin-left:15px;">交易渠道：</label>
				<select id="account_type_sel" class="form-control select2 input-medium" style="display:inline;"></select>
				<input type="hidden" name="f_Account_type" id="f_Account_type" />
				
			<label style="margin-left:15px;">消费类型：</label>
				<select id="consume_type_sel" class="form-control select2 input-medium" style="display:inline;"></select>
				<input type="hidden" name="f_Consume_type" id="f_Consume_type" />
			</div>
			
			<div class="form-group">
			<label id="userPhone1" style="margin-left:15px;">电话号码：</label>
			<input id="userPhone2" type="input" name="f_TelNum" style="display:inline;" class="form-control input-medium" />
			<label style="margin-left: 15px;">开始日期：</label>
			<div class="input-icon" style="display: inline-block;">
				<i class="fa fa-calendar"></i> <input id="startDate" name="startDate"
					type="text" class="form-control input-medium" readonly />
			</div>
			<label style="margin-left: 15px;">结束日期：</label>
			<div class="input-icon" style="display: inline-block;">
				<i class="fa fa-calendar"></i> <input id="endDate" name="endDate"
					type="text" class="form-control input-medium" readonly />
			</div>
			</div>
		</form>
		<button onclick="query()" style="margin-left:15px;" class="btn btn-success" title="查询记录"><i class="fa fa-search"></i> 查询记录</button>
		<button onclick="resetForm()" style="margin-left:15px;" class="btn btn-warning" title="重置查询条件"><i class="fa fa-repeat"></i> 重置条件</button>
	 	<button onclick="exportExcel('/bsBookflowController/export','文荷财务统计报表.xls')" style="margin-left:15px;" class="btn btn-primary" title="数据导出" ><i class="fa fa-cloud-download"></i> 数据导出</button>
	 </div>		
	<div class="page-content">
		<table id="jqGrid"></table>
	   	<div id="jqGridPager"></div>
   	</div>
</body>
</html>