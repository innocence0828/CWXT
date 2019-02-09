<%@ page contentType="text/html;charset=UTF-8"%>
<li class="nav-item start active open">
	<a href="javascript:;" class="nav-link  nav-toggle">
		<i class="fa fa-balance-scale"></i>
		<span class="title">首页</span>
        <span class=""></span>
	</a>
</li>
<li class="nav-item start">
	<a href="javascript:;" class="nav-link  nav-toggle">
		<i class="fa fa-balance-scale"></i>
		<span class="title">账单管理</span>
        <span class="arrow open"></span>
	</a>
	<ul class="sub-menu" style="">
		<li class="nav-item start">
			<a href="javascript:;" onclick="myAddMainTab('base/menu/list','我的消息','fa fa-map-signs');" class="nav-link nav-toggle">
				<i class="fa fa-sitemap"></i>
				<span class="title">自主缴费机订单</span>
				<span class=""></span>
			</a>
		</li>
	</ul>
</li>
<li class="nav-item start">
	<a href="javascript:;" class="nav-link  nav-toggle">
		<i class="fa fa-balance-scale"></i>
		<span class="title">系统设置</span>
        <span class="arrow open"></span>
	</a>
	<ul class="sub-menu" style="">
		<li class="nav-item start">
			<a href="javascript:;" onclick="myAddMainTab('menu/list','菜单管理','fa fa-map-signs');" class="nav-link nav-toggle">
				<i class="fa fa-sitemap"></i>
				<span class="title">菜单管理</span>
				<span class=""></span>
			</a>
		</li>
		<li class="nav-item start">
			<a href="javascript:;" onclick="myAddMainTab('user/list','用户管理','fa fa-map-signs');" class="nav-link nav-toggle">
				<i class="fa fa-sitemap"></i>
				<span class="title">用户管理</span>
				<span class=""></span>
			</a>
		</li>
	</ul>
</li>
<li class="nav-item start">
	<a href="javascript:;" class="nav-link  nav-toggle">
		<i class="fa fa-balance-scale"></i>
		<span class="title">主菜单</span>
        <span class="arrow open"></span>
	</a>
	<ul class="sub-menu" style="">
		<li class="nav-item start">
			<a href="javascript:;" class="nav-link  nav-toggle">
				<i class="fa fa-sitemap"></i>
				<span class="title">子菜单</span>
				<span class="arrow"></span>
			</a>
			 <ul class="sub-menu" style="">
				<li class="nav-item start">
					<a href="javascript:;" class="nav-link  nav-toggle">
						<i class="fa fa-sitemap"></i>
						<span class="title">子菜单</span>
						<span class="arrow"></span>
					</a>
					<ul class="sub-menu" style="">
						<li class="nav-item start">
							<a href="javascript:;" class="nav-link  nav-toggle">
								<i class="fa fa-sitemap"></i>
								<span class="title">子菜单</span>
								<span class=""></span>
							</a>
						</li>
					</ul>	 
				</li>
			</ul>
		</li>
	</ul>
</li>