<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>${sys_name}</title>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<link href="static/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="static/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="static/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
	<link href="static/global/common/common.min.css" rel="stylesheet" id="style_components" type="text/css" />
	<link href="static/global/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
	<link href="static/global/layout/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
	<script>
		var menuList=${menuTree};
	</script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed page-quick-sidebar-open">
		<!-- BEGIN HEADER -->
        <div id="header" class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
                    <img src="static/global/layout/images/logo.png" alt="logo" width="160px" height="28px" class="logo-default" />
                    <div class="menu-toggler sidebar-toggler dropdown-toggle popovers"  data-placement="bottom"  data-container="body" data-trigger="hover" data-placement="bottom" data-content="点击[显示]/[隐藏]边框" data-original-title="边框切换"> </div>
                </div>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                        <!-- BEGIN USER LOGIN DROPDOWN -->
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <img alt="" class="img-circle" src="static/global/layout/images/user.png" />
                                <span class="username username-hide-on-mobile"> ${fs_name} </span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                              <%--   <li>
                                    <a href="#" onclick="myAddMainTab('${ctx}/sys/user/info','个人信息','icon-user');">
                                        <i class="icon-user"></i> 个人信息 </a>
                                </li>
                                <li>
									<a href="#" onclick="myAddMainTab('${ctx}/utils/utilsDemo?page=app_todo_2','我的任务','icon-rocket');">
                                        <i class="icon-rocket"></i> 我的任务
                                        <span class="badge badge-success"> 12 </span>
                                    </a>
                                </li>
                                <li class="divider"> </li>
                                <li>
                                    <a  href="#" onclick="myAddMainTab('${ctx}/sys/user/modifyPwd','修改密码','icon-wrench');">
                                        <i class="icon-wrench"></i> 修改密码 </a>
                                </li>	 --%>							
                                <li>
                                    <a onclick="loginOut()">
                                        <i class="icon-key"></i> 退出登录 </a>
                                </li>
                            </ul>
                        </li>
                        <!-- END USER LOGIN DROPDOWN -->
                        <!-- BEGIN QUICK SIDEBAR TOGGLER -->
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-quick-sidebar-toggler">
                            <a onclick="loginOut()"  class="dropdown-toggle"   data-toggle="tooltip" title="退出">
                                <i class="icon-logout"></i>
                            </a>
                        </li>
                        <!-- END QUICK SIDEBAR TOGGLER -->
                    </ul>
                </div>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END HEADER INNER -->
        </div>		
		<!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div id="pageContainerId" class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div id="pageSidebarId" class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <ul id="menuUl" class="page-sidebar-menu  page-header-fixed menu-ul-pd" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                    </ul>
                    <!-- END SIDEBAR MENU -->
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
					<div id="mainTabs">
					</div>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->
        
<div id="mainTabsMenu">
	<div type="refresh"><i class="fa fa-refresh"></i>&nbsp;&nbsp;&nbsp;刷新</div>
	<div class="menu-sep"></div>
	<div type="close"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;关闭</div>
	<div type="closeOther"><i class="fa fa-map-signs"></i>&nbsp;&nbsp;关闭其他</div>
	<div type="closeAll"><i class="fa fa-gavel"></i>&nbsp;&nbsp;关闭所有</div>
</div>
		
<style type="text/css">
	.menu {
	    background-color: #fff;
	    border-color: #e6e6e6;
	    color: #333;
	}	
	.menu {
	    position: absolute;
	    margin: 0;
	    padding: 2px;
	    border-width: 1px;
	    border-style: solid;
	    overflow: hidden;
	}	
	.menu-text{
		padding-left:5px;
	}
	.menu-text i{
	text-align:center;
	font-size:14px;}
	.menu-text, .menu-text span{
		font-size:14px;
	}
	.page-content-wrapper .page-content {
		padding: 0px 0px 0px;
	}
</style>
<script src="static/global/plugins/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="static/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="static/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="static/global/layout/js/app.min.js" type="text/javascript"></script>
<script src="static/global/layout/js/layout.min.js" type="text/javascript"></script>
<script src="static/global/layout/js/quick-sidebar.min.js" type="text/javascript"></script>
<script src="static/global/common/common.js" type="text/javascript"></script>
<%@include file="/WEB-INF/views/include/inc_easy.jsp"%>
<%@include file="/WEB-INF/views/include/inc_jBox.jsp"%>
<%@include file="/WEB-INF/views/include/inc_layer.jsp"%>
<script type="text/javascript" src="static/views/base/main.js"></script>
</body>
</html>