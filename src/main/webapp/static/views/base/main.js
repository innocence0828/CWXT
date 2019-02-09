var initTabUrl=ctx+"/home";//默认加载的主页地址
/*首页菜单 begin*/
var html='<li class="nav-item start active open">'
			+'<a href="javascript:;" class="nav-link  nav-toggle" onclick="myAddMainTab(\''+initTabUrl+'\',\'首页\',\'fa fa-laptop\');">'
				+'<i class="fa fa-laptop"></i>'
				+'<span class="title">首页</span>'
				+'<span class=""></span>'
			+'</a>'
		  +'</li>';

$('#menuUl').html(html);
/*首页菜 end*/

//获取一级菜单
function meanList(jsonArr){
	//菜单主容器
	$dom = $("#menuUl");
	$.each(jsonArr,function(item,sObj){
		var obj = sObj.other;
		if(obj.fi_level=="1"){
			var $li = $('<li pid="'+obj.id+'" class="nav-item start">').append($('<a href="javascript:;" class="nav-link  nav-toggle">')
					.append($('<i class="'+obj.fs_icon+'"></i><span class="title">'+obj.fs_name+'</span>')));
			//添加一级菜单
			$dom.append($li);
			//获取二级菜单
			getChildMenu($("[pid="+obj.id+"]"),jsonArr,obj.id,'2');
		}
	});
}

//获取二级子菜单
function getChildMenu($dom,jsonArr,parentId,level){
	var $ul = $('<ul class="sub-menu" style="">');
	$.each(jsonArr,function(item,sObj){
		var obj = sObj.other;
		if(parentId == obj.pid && obj.fi_level==level){
			//给一级子菜单添加指示箭头图标元素
			if($dom.find("span.arrow").length==0){
				$dom.find("a").append('<span class="arrow"></span>');
			}
			var $li = $('<li class="nav-item start">')
			.append($('<a href="javascript:;" class="nav-link  nav-toggle" onclick="myAddMainTab(\''+ctx+obj.fs_url+'\',\''+obj.fs_name+'\',\''+obj.fs_icon+'\');" >')
			.append('<i class="'+obj.fs_icon+'"></i><span class="title">'+obj.fs_name+'</span>'));
			//循环查找添加同一菜单下的子菜单
			$ul.append($li);
		}
	});
	//给一级菜单添加二级菜单
	$dom.append($ul);
}


var mainTabs;
$(function(){
	//创建左侧菜单
	meanList(menuList);
	$('[data-toggle="tooltip"]').tooltip();
	/*菜单初始化 begin*/
	$('#mainTabsMenu').menu({
		onClick : function(item) {
			var curTabTitle = $(this).data('tabTitle');
			var type = $(item.target).attr('type');
			if (type === 'refresh') {
				layout_center_refreshTab(curTabTitle);
				return;
			}
			if (type === 'close') {
				var t = $('#mainTabs').tabs('getTab', curTabTitle);
				if (t.panel('options').closable) {
					$('#mainTabs').tabs('close', curTabTitle);
				}
				return;
			}
			var allTabs = $('#mainTabs').tabs('tabs');
			var closeTabsTitle = [];
			$.each(allTabs, function() {
				var opt = $(this).panel('options');
				if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === 'closeAll') {
					closeTabsTitle.push(opt.title);
				}
			});
			for ( var i = 0; i < closeTabsTitle.length; i++) {
				$('#mainTabs').tabs('close', closeTabsTitle[i]);
			}
		}
	});
	mainTabs = $("#mainTabs").tabs({
		fit : true,
		border : false,
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#mainTabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data('tabTitle', title);
		},
		onSelect : function(title,index){
			if(index != 0 )return;
			if(window.frames["protal"] && window.frames["protal"].initProtalInfo) {
				window.frames["protal"].initProtalInfo();
				window.frames["protal"].redrawDate();
			}
		}
	});
	$(window).resize(function(){
		if(window.frames["protal"].draw) {
			window.frames["protal"].draw();
		}
	});
	$(".panel-header").css("height","18px");
	$("#mainTabs").tabs("add",{
		title : "首页",
        closable : true,
        iconCls : 'fa fa-laptop',
        content : "<iframe id='protal' src='"+initTabUrl+"' name='protal' allowTransparency='true' "
        		+ "style='border:0;width:100%;height:100%;overflow:hidden'></iframe>",
        border : false,
        fit : true
	});
	$("#mainTabs").find(".panel-body").css("overflow","hidden");
	/*菜单初始化 end*/
	
});

var leftWidth = "230"; // 左侧窗口大小
if(!Array.prototype.map)
	Array.prototype.map = function(fn,scope) {
	var result = [],ri = 0;
	for (var i = 0,n = this.length; i < n; i++){
	if(i in this){
		result[ri++]  = fn.call(scope ,this[i],i,this);
	}
	}
	return result;
};
var getWindowSize = function(){
	return ["Height","Width"].map(function(name){
	return window["inner"+name] ||
		document.compatMode === "CSS1Compat" && document.documentElement[ "client" + name ] || document.body[ "client" + name ];
	});
};		
function wSize(){
	var minHeight = 300, minWidth = 980;
	var strs=getWindowSize().toString().split(",");
	$("#mainTabs").height((strs[0]<minHeight?minHeight:strs[0])-$("#header").height());
	if(strs[1]<minWidth){
		$("#mainTabs").css("width",minWidth-10);
		$("html,body").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
	}else{
		$("#mainTabs").css("width","auto");
		$("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
	}
	$("#mainTabs").width($("#pageContainerId").width()-$("#pageSidebarId").width()-5);
}
$(window).resize(function(){
	wSize();
});
wSize();

function myAddMainTab(href,title,iconname){
	document.getElementById('mainiframeid').contentWindow.myAddMainTab(href,title,iconname);
}
function gotoUrl(url){
	document.location.href = url;
}
function myAddMainTab(phref,ptitle,iconname){

	var src = phref;
	var tabs = $("#mainTabs");
	var opts = {
		title : ptitle,
		closable : true,
		iconCls : iconname,
		content : "<iframe src='" + src + "' id='"+ptitle+"' allowTransparency='true' style='border:0;width:100%;height:100%;' frameBorder='0'></iframe>",
		border : false,
		fit : true
	};
	if (tabs.tabs("exists", opts.title)) {
		tabs.tabs("select", opts.title);
		//$(window).trigger('resize');
//		alert(opts.title);
	} else {
		
        //显示loading提示
        var loading = layer.load();
        tabs.tabs({
        	
            onAdd: function(title,index){
                $('#'+ptitle).on('load', function(){
                	layer.close(loading);
                });    
                return true;
            }
        });
		tabs.tabs("add", opts);
	}
	$(".tabs-icon").attr('style', 'top: 50%;position: absolute;');
}
function myAddTab(href,title){
	top.$.jBox.open("iframe:"+href, title, 700, $(top.document).height()-180, {
        buttons:{"关闭":true}, submit:function(v, h, f){
            if (v=="ok"){
            }else if (v=="clear"){
            }
        }, loaded:function(h){
            $(".jbox-content", top.document).css("overflow-y","hidden");
        }
    });
}
function layout_center_refreshTab(title) {
	$('#mainTabs').tabs('getTab', title).panel('refresh');
}
function loginOut(){
	confirmxFun('是否退出登入？',function(){
		location.href=ctx+'/userController/actionLogin';
	});
}
