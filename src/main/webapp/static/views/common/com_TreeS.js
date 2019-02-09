var key, lastValue = '', nodeList = [];
var tree, setting = {
	view:{selectedMulti:false},check:{enable:multi,nocheckInherit:false},
	data:{simpleData:{enable:true}},
	view:{
		fontCss:function(treeId, treeNode) {
			return (!!treeNode.highlight) ? {'font-weight':'bold'} : {'font-weight':'normal'};
		}
	},
	callback:{
		beforeClick:function(id, node){
			if(multi){
				tree.checkNode(node, !node.checked, true, true);
				return false;
			}
		}
	}
};
$(function(){
	$.post(ctx+postUrl, function(zNodes){
		// 初始化树结构
		tree = $.fn.zTree.init($('#tree'), setting, zNodes);
		// 默认展开一级节点
		var nodes = tree.getNodesByParam('level', level);
		for(var i=0; i<nodes.length; i++) {
			tree.expandNode(nodes[i], true, false, false);
		}
		// 默认选择节点
		var ids = selectIds.split(',');
		for(var i=0; i<ids.length; i++) {
			var node = tree.getNodeByParam('id', ids[i]);
			if(multi){
				try{tree.checkNode(node, true, true);}catch(e){}
				tree.selectNode(node, false);
			}else{
				tree.selectNode(node, true);
			}
		}
	});
	key = $('#key');
	key.bind('focus', focusKey).bind('blur', blurKey).bind('change keydown cut input propertychange', searchNode);
}).error(function(e) {
	tipx('数据加载失败，系统异常！', 'error');
	console.log(e);
});
function focusKey(e) {
	if (key.hasClass('empty')) {
		key.removeClass('empty');
	}
}
function blurKey(e) {
	if (key.get(0).value === '') {
		key.addClass('empty');
	}
	searchNode(e);
}
function searchNode(e) {
	// 取得输入的关键字的值
	var value = $.trim(key.get(0).value);

	// 按名字查询
	var keyType = 'name';
	if (key.hasClass('empty')) {
		value = '';
	}
	
	// 如果和上次一次，就退出不查了。
	if (lastValue === value) {
		return;
	}
	
	// 保存最后一次
	lastValue = value;
	
	// 如果要查空字串，就退出不查了。
	if (value === '') {
		return;
	}
	updateNodes(false);
	nodeList = tree.getNodesByParamFuzzy(keyType, value);
	updateNodes(true);
}
function updateNodes(highlight) {
	for(var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;				
		tree.updateNode(nodeList[i]);
		tree.expandNode(nodeList[i].getParentNode(), true, false, false);
	}
}
function search() {
	$('#search').slideToggle(200);
	$('#txt').toggle();
	$('#key').focus();
}
/**
 * 获取选中的node
 * @returns
 */
function getSelNode(){
	if(multi){
		return tree.getCheckedNodes(true);
	}else{
		return tree.getSelectedNodes();
	}
}