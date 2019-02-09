var chartDatas = {"pie1":[],"pie2":{},"bar":[]};

$(function(){
	console.log("---------------------------------------");
	setTime();
	
	$.getJSON(ctx+"/homeController/query",function(result){
//		$("#chartFlowUp").text(JSON.stringify(result));
		//1、页面头部四大类数据展示
		var headJSON = result;
		$("#crntBalance").attr("data-value",headJSON.leaveMoney);
		$("#gross").attr("data-value",headJSON.inMoney);
		$("#expense").attr("data-value",headJSON.outMoney);
		$("#maxGross").attr("data-value",headJSON.maxOutMoney);
		$('.widget-thumb-body-stat').counterUp();
	});
	
	$.getJSON(ctx+"/homeController/query2",{"f_Date":$("#pie1Date").val()},function(result){
		//饼图-类型
		showChartNationPie(result);
		chartDatas.pie1 = result;
	});
	
	$.getJSON(ctx+"/homeController/query1",{"f_Date":$("#pie2Date").val()},function(result){
		//饼图-收入支出
		showChartRighrPie(result);
		chartDatas.pie2 = result;
	});

	$.getJSON(ctx+"/homeController/query3",{"f_Date":$("#barYearDate").val()},function(result){
		//饼图-收入支出
		showCharPoliticalPie(result);
		chartDatas.bar = result;
	});
	$(window).resize(function(){
		echarts.init($("#chartNationPie")[0]).resize();
		echarts.init($("#charPoliticalPie")[0]).resize();
		echarts.init($("#chartFlowUp")[0]).resize();
	});
});

function setTime(){
	var date = new Date();
	var year = date.getFullYear();
	var month = (date.getMonth()<=9?"0":"")+ (date.getMonth()+1)
	$("#pie1Date").val(year+"-"+month);
	$("#pie1Date").on("click",function(){
		WdatePicker({onpicked:function(){
			$.getJSON(ctx+"/homeController/query2",{"f_Date":$("#pie1Date").val()},function(result){
				//饼图-类型
				showChartNationPie(result);
			});
		},dateFmt:'yyyy-MM'});
	});
	$("#pie2Date").val(year+"-"+month);
	$("#pie2Date").on("click",function(){
		WdatePicker({onpicked:function(){
			$.getJSON(ctx+"/homeController/query1",{"f_Date":$("#pie2Date").val()},function(result){
				//饼图-收入支出
				showChartRighrPie(result);
			});
		},dateFmt:'yyyy-MM'});
	});
	$("#barYearDate").val(year);
	$("#barYearDate").on("click",function(){
		WdatePicker({onpicked:function(){
			$.getJSON(ctx+"/homeController/query3",{"f_Date":$("#barYearDate").val()},function(result){
				//饼图-收入支出
				showCharPoliticalPie(result);
			});
		},dateFmt:'yyyy'});
	});
}

function showChartNationPie(result){
	var datas = {"title":[],"value":[]};
	for(var i=0;i<result.length;i++){
		if(result[i].typeName!="合计"){
			datas.title.push(result[i].typeName);
			datas.value.push({value:result[i].typeMoney,name:result[i].typeName});
		}
	}
	var option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: datas.title
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:datas.value,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	var myChart = echarts.init($("#chartNationPie")[0]);
	myChart.setOption(option);
}

function showChartRighrPie(result){
	var datas = {"title":[],"value":[]};
	$.each(result,function(key,value){
		if(key!="余额"){
			datas.title.push(key);
			datas.value.push({value:value,name:key});
		}
	});
	var option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: datas.title
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:datas.value,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	var myChart = echarts.init($("#charPoliticalPie")[0]);
	myChart.setOption(option);
}

function showCharPoliticalPie(result){
	var datas = {"title":[],"balance":[],"expense":[],"income":[]};
	for(var i=0;i<result.length;i++){
		datas.title.push(result[i].fsDate);
		datas.balance.push(result[i].leaveMoney);
		datas.expense.push('-'+result[i].outMoney);
		datas.income.push(result[i].inMoney);
	}

	var option = {
	    "tooltip": {
	        "trigger": "axis",
	        "axisPointer": {
	            "type": "shadow",
	            textStyle: {
	                color: "#fff"
	            }

	        },
	    },
	    "grid": {
	        "borderWidth": 0,
	        "top": 40,
	        "right":40,
	        "left":40,
	        "bottom": 95,
	        textStyle: {
	            color: "#fff"
	        }
	    },
	    "legend": {
	        x: '4%',
	        top: '1%',
	        textStyle: {
	            color: '#90979c',
	        },
	        "data": ['余额', '支出', '收入']
	    },
	     

	    "calculable": true,
	    "xAxis": [{
	        "type": "category",
	        "axisLine": {
	            lineStyle: {
	                color: '#000'
	            }
	        },
	        "splitLine": {
	            "show": false
	        },
	        "axisTick": {
	            "show": false
	        },
	        "splitArea": {
	            "show": false
	        },
	        "axisLabel": {
	            "interval": 0,

	        },
	        "data": datas.title,
	    }],
	    "yAxis": [{
	        "type": "value",
	        "splitLine": {
	            "show": false
	        },
	        "axisLine": {
	            lineStyle: {
	                color: '#000'
	            }
	        },
	        "axisTick": {
	            "show": false
	        },
	        "axisLabel": {
	            "interval": 0,

	        },
	        "splitArea": {
	            "show": false
	        },

	    }],
	    "dataZoom": [{
	        "show": true,
	        "height": 30,
	        "xAxisIndex": [
	            0
	        ],
	        bottom: 30,
	        "start": 0,
	        "end": 100,
	        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
	        handleSize: '110%',
	        handleStyle:{
	            color:"#d3dee5",
	            
	        },
	           textStyle:{
	            color:"#fff"},
	           borderColor:"#90979c"
	        
	        
	    }, {
	        "type": "inside",
	        "show": true,
	        "height": 15,
	        "start": 1,
	        "end": 35
	    }],
	    "series": [{
	            "name": "余额",
	            "type": "bar",
	            "stack": "金额",
	            "barMaxWidth": 35,
	            "barGap": "10%",
	            "itemStyle": {
	                "normal": {
	                    "color": "rgba(255,144,128,1)",
	                    "label": {
	                        "show": true,
	                        "textStyle": {
	                            "color": "#fff"
	                        },
	                        "position": "insideTop",
	                        formatter: function(p) {
	                            return p.value > 0 ? (p.value) : '';
	                        }
	                    }
	                }
	            },
	            "data":datas.balance,
	        },

	        {
	            "name": "支出",
	            "type": "bar",
	            "stack": "金额",
	            "itemStyle": {
	                "normal": {
	                    "color": "rgba(0,191,183,1)",
	                    "barBorderRadius": 0,
	                    "label": {
	                        "show": true,
	                        "position": "top",
	                        formatter: function(p) {
	                            return p.value > 0 ? (p.value) : '';
	                        }
	                    }
	                }
	            },
	            "data": datas.expense
	        }, {
	            "name": "收入",
	            "type": "line",
	            "stack": "金额",
	            symbolSize:10,
	            symbol:'circle',
	            "itemStyle": {
	                "normal": {
	                    "color": "#888",
	                    "barBorderRadius": 0,
	                    "label": {
	                        "show": true,
	                        "position": "top",
	                        formatter: function(p) {
	                            return p.value > 0 ? (p.value) : '';
	                        }
	                    }
	                }
	            },
	            "data": datas.income
	        },
	    ]
	}
	var myChart = echarts.init($("#chartFlowUp")[0]);
	myChart.setOption(option);
}