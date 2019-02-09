$(function(){
	$('#inputForm').validate({
		rules : {
			fs_name : 'required',
			fi_sort : {
				required:true,
				number:true
			}
		},
		messages : {
			fs_name : '请输入菜单名称',
			fi_sort : {
				required:'请输入序号',
				number:'请输入合法的数字'
			}
		},
		errorPlacement : function(error, element) {
			if (element.is(':checkbox') || element.is(':radio') || element.parent().is('.input-append')) {
				error.appendTo(element.parent());
			} else {
				error.insertAfter(element);
			}
		},success: function (label) {
			label.remove();
        }
	});
});
/**
 * 菜单保存
 * @returns
 */
function save(){
	if ($('#inputForm').valid()) {
		loading('正在提交，请稍等...');
		var options = {
			type : 'post',
			dataType : 'json',
			url : ctx + '/menu/save',
			success : function(data) {
				closeTip();
				if (data.success) {
					tipx('保存成功！', 'success', function() {
						closeJbox('/menu/list',function(o){
							o.query();
						});
					});
				} else {
					tipx(data.message, 'error');
				}
			},
			error : function(e) {
				closeTip();
				tipx('保存失败，系统异常！', 'error');
				console.log(e);
			}
		};
		$('#inputForm').ajaxSubmit(options);
	}
	return false;
}
/**
 * 选择上级菜单
 * @returns
 */
function sel_pid(){
	top.$.jBox.open('iframe:'+ctx+'/com/TreeS?postUrl='+encodeURIComponent('/menu/getTreeDate')+'&selectIds='+$('#pid').val()+'&multi=false&level=0',
			'选择上级菜单', 300, $(top.document).height()-120, {
		buttons:{'确定':'ok', '关闭':true}, submit:function(v, h, f){
			if (v=='ok'){
				var treeFrame = h.find('iframe')[0].contentWindow;
				var ids = [], names = [], nodes = treeFrame.getSelNode();
				for(var i=0; i<nodes.length; i++) {
					ids.push(nodes[i].id);
					names.push(nodes[i].name);
					break;
				}
				$('#pid').val(ids).change();
				$('#pname').val(names).change();
			}
		}, loaded:function(h){
			$('.jbox-content', top.document).css('overflow-y','inherit');
		}
	});
}
/**
 * 选择图标
 * @returns
 */
function sel_icon(){
	top.$.jBox.open('iframe:'+ctx+'/com/IconS?fs_icon='+$('#fs_icon').val(), '选择图标', 700, $(top.document).height()-120, {
        buttons:{'确定':'ok', '关闭':true}, submit:function(v, h, f){
            if (v=='ok'){
            	var icon = h.find('iframe')[0].contentWindow.$('#icon').val();
            	$('#iconi').attr('class', icon);
                $('#iconl').text(icon);
                $('#fs_icon').val(icon).change();
            }
        }, loaded:function(h){
//        	alert(JSON.stringify());
        	$('.jbox-content', top.document).css('overflow-y','inherit');
        }
    });
}