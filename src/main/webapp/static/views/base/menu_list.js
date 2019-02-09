$(function(){
	query();
});
/**
 * 列表查询
 */
function query(){
	loading("正在加载数据，请稍等...");
	$.ajax({
		type:'post',
		dataType:'json',
		url:ctx+'/menu/search',
		success:function(data){
			closeTip();
			var arr=data;
			var html='<tr><th>菜单名称</th><th>链接</th><th>序号</th><th>操作</th></tr>';
			for(var i=0;i<arr.length;i++){
				var obj=arr[i].other;
				if(obj.pid==null){
					obj.pid=1;
				}
				html+='<tr id="'+obj.id+'" pId="'+obj.pid+'">';
				html+='<td><a onclick="openInput(\'编辑菜单\',\''+obj.id+'\')"><i class="'+obj.fs_icon+'"></i>'+obj.fs_name+'</a></td>';
				if(obj.fs_url==null){
					obj.fs_url='';
				}
				html+='<td>'+obj.fs_url+'</td>';
				html+='<td><input id="'+obj.pid+'" name="sorts" type="text" value="'+obj.fi_sort+'" style="width:50px;margin:0;padding:0;text-align:center;"></td>';
				html+='<td>';
				html+='<div onclick="openInput(\'编辑菜单\',\''+obj.id+'\')" style="float:left;cursor:pointer;margin-left:5px;" class="ui-pg-div ui-inline-edit" onclick="" data-toggle="tooltip" data-original-title="编辑菜单">'
						+'<span class="ui-icon ui-icon-pencil"></span>'
						+'</div>';
				html+='<div style="float:left;cursor:pointer;margin-left:5px;" class="ui-pg-div ui-inline-edit" onclick="deleteThis(\''+obj.id+'\')" data-toggle="tooltip" data-original-title="删除菜单">'
					+'<span class="ui-icon ui-icon-trash"></span>'
					+'</div>';
				html+='</td>';
				html+='</tr>';
			}
			$("#treeTable").html(html);
		    $('#treeTable').treeTable({expandLevel : 3});
		    $('[data-toggle="tooltip"]').tooltip();
		},
		error:function(e){
			closeTip();
			tipx('系统异常!', 'error');
			console.log(e);
		}
	});
}
/**
 * 数据删除操作
 * @param {} id
 */
function deleteThis(id){
//	alert(id);
	confirmxFun('确认要删除当前所选菜单和子菜单吗？', function(){
		loading('正在删除，请稍等...');
		$.ajax({
			type : 'post',
			dataType : "json",
			url : ctx+'/menu/delete',
			data : {
				id:id
			},
			success : function(data) {
				closeTip();
				if (data.success) {
					tipx('删除成功', 'success',function(){
						query();
					});
				}
				else{
					tipx(data.message, 'error');
				}
			},
			error : function(e) {
//				alert(JSON.stringify(e));
				closeTip();
				tipx('系统异常!', 'error');
				console.log(e);
			}
		});
	});
}
/**
 * 打开功能列表
 * @param {} id
 */
function openOper(id){
	top.$.jBox.open('iframe:'+contextPath+'/base/menu_oper_list.jsp?className=userService&method=upload&menu_id='+id, 
	'功能选择',600,$(top.document).height()-10,{
		buttons:{},
		loaded:function(h){
			$('.jbox-content', top.document).css('overflow-y','inherit');
			$('.jbox-content', top.document).css('overflow-x','inherit');
		}
	});
}
/**
 * 打开input页面
 * @param id
 * @param pid
 * @returns
 */
function openInput(title,id,pid){
	top.$.jBox.open('iframe:'+ctx+'/menu/input?id='+id+'&pid='+pid,title,1000,$(top.document).height()-120,{
		buttons:{},
		loaded:function(h){
			$('.jbox-content', top.document).css('overflow-y','inherit');
		}
	});
}