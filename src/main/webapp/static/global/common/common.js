function resetTip() {
	top.$.jBox.tip.mess = null
}
function closeTip() {
	top.$.jBox.closeTip();
}
function loading(a) {
	if(a == undefined || a == "") {
		a = "正在提交，请稍等..."
	}
	resetTip();
	top.$.jBox.tip(a, "loading", {
		opacity: 0.5,
		persistent: true
	})
}
function tipx(b, c,a) {
	top.$.jBox.tip(b, c, {
		closed: function() {
			if(typeof a == "function") {
				a()
			}
		}
	});
}
function confirmxFun(b, a) {
	top.$.jBox.confirm(b, "系统提示", function(c, d, e) {
		if(c == "ok") {
			if(typeof a == "function") {
				a();
			}
		}
	}, {
		buttonsFocus: 1
	});
}
function closeJbox(url,e){
	var o;
	for(var i=0;i<parent.frames.length;i++){
		var obj=parent.frames[i];
		if(obj.document.URL.indexOf(url)!=-1){
			o=obj;
			break;
		}
	}
	e(o);
	parent.$.jBox.close(true);
}