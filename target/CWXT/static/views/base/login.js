if (self.frameElement && self.frameElement.tagName == "IFRAME"
	||window.frames.length != parent.frames.length
	||self != top) {
	var url =window.document.location.href;
	top.location.href=url;
}
$(function() {
	$('#loginForm').validate({
		rules : {
			acc : 'required',
			pwd : 'required'
		},
		messages : {
			acc : '请输入帐号',
			pwd : '请输入密码'
		},
		errorPlacement : function(error, element) {
			$(element.parent().parent()).css('marginBottom','0');
			error.insertAfter(element);
		},
		success: function (label) {
			$(label.parent().parent()).css('marginBottom','15');
			label.remove();
        }
	});
	$('#btn_login').click(function(){
		verification();
	});
	$('body').keyup(function(event){
		if(event.keyCode==13){
			verification();
		}
	}); 
});
function go(){
	if ($('#loginForm').valid()) {
		loading('正在登入，请稍等...');
		$.post(ctx+'/login/generatSignKey',function(r) {
			if(r.success){
				verification(r.data);
			}else{
				closeTip();
				tipx(r.message, 'error');
			}
		}).error(function(e) {
			closeTip();
			tipx('登入失败，系统异常！', 'error');
			console.log(e);
		});
	}
}
function verification(){
	var acc= $('#acc').val();
	var pwd= $('#pwd').val();
	alert(acc+pwd);
	$.post(ctx+'/userController/login',{f_UserId:acc,f_PassWord:pwd},function(r) {
		if(r.success){
			location.href=ctx+'/main';
		}else{
			closeTip();
			tipx(r.message, 'error');
		}
	}).error(function(e) {
		closeTip();
		tipx('登入失败，系统异常！', 'error');
		console.log(e);
	});
}