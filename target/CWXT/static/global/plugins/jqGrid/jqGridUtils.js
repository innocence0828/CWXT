var grid_selector = '#jqGrid';
var pager_selector = '#jqGridPager';
var pcontent_selector = '.page-content';
var seachFrom_selector = '#seachForm';
/**
 * 加载完成初始化数据表格
 * @param a
 * @returns
 */
function loadGrid(a){
	updatePagerIcons();
	enableTooltips(a);
	jqGridResize();
}
/**
 * 更新分页样式
 * @returns
 */
function updatePagerIcons() {
    $('.ui-icon.ui-icon-seek-prev').wrap('<div class="btn btn-sm btn-default"></div>');
    $('.ui-icon.ui-icon-seek-prev').removeClass().addClass('fa fa-backward');
    $('.ui-icon.ui-icon-seek-first').wrap('<div class="btn btn-sm btn-default"></div>');
    $('.ui-icon.ui-icon-seek-first').removeClass().addClass("fa fa-fast-backward");
    $('.ui-icon.ui-icon-seek-next').wrap('<div class="btn btn-sm btn-default"></div>');
    $('.ui-icon.ui-icon-seek-next').removeClass().addClass('fa fa-forward');
    $('.ui-icon.ui-icon-seek-end').wrap('<div class="btn btn-sm btn-default"></div>');
    $('.ui-icon.ui-icon-seek-end').removeClass().addClass('fa fa-fast-forward')
}

/**
 * 加载提示Tool
 * @param a
 * @returns
 */
function enableTooltips(a) {
	$('[data-toggle="tooltip"]').tooltip();
}
/**
 * 数据表格宽度重新适配
 * @returns
 */
function jqGridResize() {
    var b = $(pcontent_selector).width();
    jqGridResizeP(grid_selector, b);
}
function jqGridResizeP(a, b) {
    if (!$.fmatter.isEmpty(b)) {
        $(a).jqGrid('setGridWidth', b);
        $('.ui-jqgrid-bdiv').width(b + 2)
    }
}
function initJsonReader(){
	return {  
        root: 'rows', 
        page: 'page', 
        total: 'total', 
        records: 'records',
        repeatitems: false
    }
}
/**
 * 查询
 * @returns
 */
function query(){
	$(grid_selector).jqGrid('setGridParam',{  
        datatype:'json',  
        postData:$(seachFrom_selector).serializeJson(),
        page:1  
    }).trigger("reloadGrid");
}
/**
 * Excel导出
 * @param url      生成Excel地址
 * @param fileName Excel文件名称
 * @returns
 */
function exportExcel(url,fileName){
	if($(grid_selector).jqGrid("getRowData").length<1){
		tipx("请先查询出数据", 'error');
		return;
	}
	var params=$(seachFrom_selector).serializeJson();
	params.sortname=$(grid_selector).jqGrid('getGridParam','sortname');
	params.sortorder=$(grid_selector).jqGrid('getGridParam','sortorder');
	loading('正在生成Excel，请稍等...');
	$.post(ctx+url,params,function(r) {
		var datastr = JSON.parse(r);
		alert(fileName);
		if(datastr.success){
			location.href=ctx+'/baseController/downExcel?path='+datastr.list+'&name='+encodeURI(encodeURI(fileName));
		}else{
			tipx(r.message, 'error');
		}
		closeTip();
	}).error(function(e) {
		closeTip();
		tipx('导出失败，系统异常！', 'error');
		console.log(e);
	});
}
$(function(){
	$(window).resize(function(){  
		jqGridResize();
    });
});