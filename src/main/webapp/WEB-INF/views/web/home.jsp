<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<link href="${ctxStatic}/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
 	<link href="${ctxStatic}/global/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
	<script src="${ctxStatic}/global/plugins/WdatePicker/WdatePicker.js" type="text/javascript"></script>
	
	<style>
		.page-header-fixed .page-container{
			margin-top: 0px;
		}
		.page-content-wrapper .page-content {
		    margin-left: 0px;min-height: 0px;
		}
		.widget-thumb .widget-thumb-body .widget-thumb-body-stat{
			font-size:22px;
		}
		.widget-thumb-icon>i{-webkit-transition:all .5s ease-in-out;-moz-transition:all .5s ease-in-out}
		.widget-thumb-icon>i:hover{
			 transform:rotate(360deg) scale(1.3);
		     -webkit-transform:rotate(360deg) scale(1.3);
		     -moz-transform:rotate(360deg) scale(1.3);
		     -ms-transform:rotate(360deg) scale(1.3);
		     -o-transform:rotate(360deg) scale(1.3);
		}
		.widget-thumb-icon{
			-webkit-animation: floating-bubble .5s 1 cubic-bezier(0.18,.89,.32,1.28) both;
			animation: floating-bubble .5s 1 cubic-bezier(0.18,.89,.32,1.28) both;
			animation-delay: 0.5s;
		}
		
		@-webkit-keyframes floating-bubble {
		    from {
		        -webkit-transform: translate(0,-5px) scale(0);
		        transform: translate(0,-5px) scale(0)
		    }
		
		    to {
		        -webkit-transform: translate(0,500px) scale(1);
		        transform: translate(0,500px) scale(1)
		    }
		}
		
		@-moz-keyframes floating-bubble {
		    from {
		        -moz-transform: translate(0,-5px) scale(0);
		        transform: translate(0,-5px) scale(0)
		    }
		
		    to {
		        -moz-transform: translate(0,0) scale(1);
		        transform: translate(0,0) scale(1)
		    }
		}
		
		@keyframes floating-bubble {
		    from {
		        -webkit-transform: translate(0,-5px) scale(0);
		        -moz-transform: translate(0,-5px) scale(0);
		        transform: translate(0,-5px) scale(0)
		    }
		
		    to {
		        -webkit-transform: translate(0,0) scale(1);
		        -moz-transform: translate(0,0) scale(1);
		        transform: translate(0,0) scale(1)
		    }
		}
		
		
	</style>
	<script>
		var isFlowUpDatew=false;
		var FlowUpDate=${FlowUpDate};
		for(var i=0;i<FlowUpDate.seriesDate.length;i++){
			if(FlowUpDate.seriesDate[i]>9999){
				isFlowUpDatew=true;
				break;
			}
		}
		var FlowUpQQDate=${FlowUpQQDate};
		var NationPieDate=${NationPieDate};
		var PoliticalPieDate=${PoliticalPieDate};
	</script>
</head>
<body class="page-header-fixed page-content-white">


<!-- BEGIN HEADER & CONTENT DIVIDER -->
<div class="clearfix"> </div>
<!-- END HEADER & CONTENT DIVIDER -->

<!-- BEGIN CONTAINER -->
<div class="page-container">

    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content">
        
			<div class="row widget-row">
              <div class="col-md-3">
                  <!-- BEGIN WIDGET THUMB -->
                  <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
                      <h4 class="widget-thumb-heading">当前余额</h4>
                      <div class="widget-thumb-wrap">
                          <i class="widget-thumb-icon bg-green"><i class="icon-bulb"></i></i>
                          <div class="widget-thumb-body">
                              <span class="widget-thumb-subtitle">金额</span>
                              <span id="crntBalance" class="widget-thumb-body-stat" data-counter="counterup">0</span>
                          </div>
                      </div>
                  </div>
                  <!-- END WIDGET THUMB -->
              </div>
              <div class="col-md-3">
                  <!-- BEGIN WIDGET THUMB -->
                  <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
                      <h4 class="widget-thumb-heading">总收入</h4>
                      <div class="widget-thumb-wrap">
                          <i class="widget-thumb-icon bg-red"><i class="icon-layers"></i></i>
                          <div class="widget-thumb-body">
                              <span class="widget-thumb-subtitle">金额</span>
                              <%-- <span class="widget-thumb-body-stat" data-counter="counterup" data-value="${UnitpersonCount}">0</span> --%>
                              <span id="gross" class="widget-thumb-body-stat" data-counter="counterup" >0</span>
                          </div>
                      </div>
                  </div>
                  <!-- END WIDGET THUMB -->
              </div>
              <div class="col-md-3">
                  <!-- BEGIN WIDGET THUMB -->
                  <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
                      <h4 class="widget-thumb-heading">总支出</h4>
                      <div class="widget-thumb-wrap">
                          <i class="widget-thumb-icon bg-blue"><i class="icon-bar-chart"></i></i>
                          <div class="widget-thumb-body">
                              <span class="widget-thumb-subtitle">金额</span>
                              <%-- <span class="widget-thumb-body-stat" data-counter="counterup" data-value="${WorkerpersonCount}">0</span> --%>
                              <span id="expense" class="widget-thumb-body-stat" data-counter="counterup" >0</span>
                          </div>
                      </div>
                  </div>
                  <!-- END WIDGET THUMB -->
              </div>
              <div class="col-md-3">
                  <!-- BEGIN WIDGET THUMB -->
                  <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
                      <h4 class="widget-thumb-heading">最大支出</h4>
                      <div class="widget-thumb-wrap">
                          <i class="widget-thumb-icon bg-purple"><i class="icon-screen-desktop"></i></i>
                          <div class="widget-thumb-body">
                              <span class="widget-thumb-subtitle">金额</span>
                              <%-- <span class="widget-thumb-body-stat" data-counter="counterup" data-value="${FlowCount}">0</span> --%>
                              <span id="maxGross" class="widget-thumb-body-stat" data-counter="counterup" >0</span>
                          </div>
                      </div>
                  </div>
                  <!-- END WIDGET THUMB -->
              </div>
             
          </div>
        
          <div class="row">
        	<div class="col-md-6 col-sm-6">
               <div class="portlet light bordered">
                   <div class="portlet-title">
                       <div class="caption">
                           <i class="icon-pie-chart font-green"></i>
                           <span class="caption-subject font-green bold uppercase">分类支出</span>
                       </div>
                       <div class="actions">月份：<input id="pie1Date" type="text"></div>
                   </div>
                   <div class="portlet-body" id="NationPie">
                   		<div id="chartNationPie" style="height:300px"></div>
                   </div>
               </div>
           </div>
           
	          <div class="col-md-6 col-sm-6">
	               <div class="portlet light bordered">
	                   <div class="portlet-title">
	                       <div class="caption">
	                           <i class="icon-support font-yellow"></i>
	                           <span class="caption-subject font-yellow bold uppercase">收入支出占比</span>
	                       </div>
	                       <div class="actions">月份：<input id="pie2Date" type="text"></div>
	                   </div>
	                   <div class="portlet-body" id="PoliticalPie">
	                   		<div id="charPoliticalPie" style="height:300px"></div>
	                   </div>
	               </div>
	         	</div>
	        </div>
          
          <div class="row">
          <div class="col-md-12 col-sm-12">
              <div class="portlet light bordered">
                  <div class="portlet-title">
                      <div class="caption">
                          <i class="icon-cursor font-blue"></i>
                          <span class="caption-subject font-blue bold uppercase">年度每月收入支出展示</span>
                      </div>
                      <div class="actions">年份：<input id="barYearDate" type="text"></div>
                  </div>
                  <div class="portlet-body" id="FlowUp">
                      <div id="chartFlowUp" style="height:450px"></div>
                  </div>
              </div>
          </div>
          
        </div>
        
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
 </div>
 <!-- END CONTAINER -->
</body>
<script src="${ctxStatic}/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/echart/echarts.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/views/web/home.js" type="text/javascript"></script>
</html>