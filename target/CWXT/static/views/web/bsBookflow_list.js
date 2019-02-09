$(function(){
	(function(){
		if(loginId!=="admin"){
			$("#userPhone1").css("display","none");
			$("#userPhone2").css("display","none");
		}
	})();
	//交易渠道
	$('#account_type_sel').select2({
		tags: false,
		multiple: false,//是否多选
		allowClear:true,//是否显示清除按钮
		ajax: {
			url: ctx+'/comData/comDictSel?type=2',
			type:'post',
			dataType: 'json',
			processResults: function (data) {return {results: data};}
		}
    }).on('change', function(e) {
    	$('#f_Account_type').val($(this).select2('val'));
    });
	
	//消费类型
	$('#consume_type_sel').select2({
		tags: false,
		multiple: false,//是否多选
		allowClear:true,//是否显示清除按钮
		ajax: {
			url: ctx+'/comData/comDictSel?type=1',
			type:'post',
			dataType: 'json',
			processResults: function (data) {return {results: data};}
		}
    }).on('change', function(e) {
    	$('#f_Consume_type').val($(this).select2('val'));
    });
	
	//收入支出
	$('#direction_type_sel').select2({
		tags: false,
		multiple: false,//是否多选
		allowClear:true,//是否显示清除按钮
		ajax: {
			url: ctx+'/comData/comDictSel?type=3',
			type:'post',
			dataType: 'json',
			processResults: function (data) {return {results: data};}
		}
	}).on('change', function(e) {
		$('#f_Direction_type').val($(this).select2('val'));
	});
	
	//开始日期
	$('#startDate').datepicker({
		 language: 'zh-CN',
		 autoclose: true,
         clearBtn: true,
         format: "yyyy-mm-dd"
	});
	//结束日期
	$('#endDate').datepicker({
		language: 'zh-CN',
		autoclose: true,
		clearBtn: true,
		format: "yyyy-mm-dd"
	});
	
	
	//数据表格加载
	$(grid_selector).jqGrid({
        url: ctx+'/bsBookflowController/query',
        mtype: 'post',
        datatype: 'json',
        postData:{
		'name':function(){
			if($('#name').val()==undefined){
				return " ";
			}
			return $('#name').val();
		 },
		 'idnumber':function(){
				if($('#idnumber').val()==undefined){
					return " ";
				}
				return $('#idnumber').val();
			 }
        },
        colModel: [
        	{
            	label : '序号',
            	name : 'order_Id',
            	align : 'center',
            	width: 50,
            	frozen:true
            },{
            	label : '电话',
            	name : 'f_UserId',
            	align : 'left',
            	frozen:true
            },{
            	label : '日期',
            	name : 'f_Date',
            	align : 'left',
            	
            },{ 
            	label : '名称 ',
            	name : 'f_Caption',
            	align : 'left'
            		
            },{ 
            	label : '交易渠道 ',
            	name : 'f_Account_type',
            	align : 'left'
            		
            },{ 
            	label : '收入支出 ',
            	name : 'f_Direction_type',
            	align : 'left'
            		
            },{ 
            	label : '消费类型 ',
            	name : 'f_Consume_type',
            	align : 'left'
            		
            },{
            	label : '金额（元）',
            	name : 'f_Money',
            	align : 'left'
            },{
            	label : '备注',
            	name : 'f_Note',
            	align : 'left'
            }
        ],
        loadComplete : function(xhr) {
			setTimeout(function(){
				loadGrid(this);
			}, 0);
		},
		height : 'auto',
		jsonReader : initJsonReader ,
		autowidth:true,
        prmNames:{
        	sort: 'sortname',
            order: 'sortorder'
        },
        pager: pager_selector,
		shrinkToFit:false,//是否按比例计算列宽
		viewrecords:true,//是否显示页码
		footerrow:true,//是否显示页脚 合计
		userDataOnFooter:true, //是否显示页脚 合计
        rowNum:10,
        subGridWidth:100,
        rowList : [10,20,50,100,500,1000],
        sortname:'OPERNO',
		sortorder : 'desc'
    }).jqGrid('setFrozenColumns');
});

/**
 * 重置查询条件
 * 
 * @returns
 */
function resetForm(){
    $('#consume_type_sel').select2('val', '');
    $('#account_type_sel').select2('val', '');
    $('#direction_type_sel').select2('val', '');
    $('#startDate').html("");
    $('#endDate').html("");
	$(':input','#seachForm').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
}