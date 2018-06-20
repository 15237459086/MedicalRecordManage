
$(function(){
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});
	
	var validator = $("#queryForm").validate({
		errorElement: "title",
		ignoreTitle: true,
		submitHandler : function(){
			queryFormSubmit();
		},
		rules:{
			outHospitalStartDate: {
				required: true
            },
            outHospitalEndDate: {
				required: true
            }
		}
		
	});
	
	
});
/*点击查询按钮*/
function queryBtnClick(){
	$("#divPieChart").html("");
	$("#queryForm").submit();
}

/*提交查询*/
function queryFormSubmit(){
	
	var submitData = $('#queryForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
	$.ajax({
		url: basePath + "statistics_analysis/in_hospital_type_analysis",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			
			if(success){
				var datas = data['data'];
				var chartDataMap ={  
	                     'caption':'入院方式统计('+datas.totalCount+'人)', 
	                     'useRoundEdges':"1",
	                     'formatnumberscale':'1',  
	                     'showBorder':'0',
	                     'showLegend':"1",
	                     'showLabels':'1',
	                     'showValues':'1',
	                     'labelSepChar':',',
	                     'pieslicedepth':'30',
	                     'yAxisMinValue':'10',  
	                     'yAxisMaxValue':'100',  
//	                     'xAxisName':'单位名称',  
//	                     'yAxisName':'完成度百分比',  
	                     "canvasbgColor": "#1790e1",  
	                     "canvasbgAlpha": "10",  
	                     "showCanvasBorder": "1", 
//	                     "labelDisplay": "rotate"  
//	                     "labelDisplay": "auto"  
	                     "toolTipSepChar":"：",  
	                     "baseFontSize":"12"
	            };  
	              
	            //将后台返回的数据与前台设置好的统计图属性数据一起封装成map集合给FusionCharts解析  
				var showDataMap = {'data':datas.items,'chart':chartDataMap};  
				var myChartId = new Date().getTime();
				var myChart = new FusionCharts(basePath+"assets/FusionChart/Pie2D.swf",myChartId,"100%", "396");  
	              
	            myChart.setJSONData(showDataMap);  
	            /*var myChart = new FusionCharts(basePath+"assets/FusionChart/Pie2D.swf",myChartId,"100%", "396");  
	            var showData = '<chart useRoundEdges="1" showBorder="0" showLegend="1" formatNumberScale="0" baseFontSize="12" toolTipSepChar="" labelSepChar="">';
	            var items = datas['items'];
            	for(var index in items){
        			var item = items[index];
        			showData +='<set value="'+item.value+'" label="'+item.label+'" />';
        	    }
            	showData +='<set value="2" label="未知" />';
	            showData +='<styles><definition><style name="CaptionFont" type="FONT" face="Verdana" size="15" color="7D8892" bold="1"/><style name="LabelFont" type="FONT" color="7D8892" bold="1"/></definition><application><apply toObject="DATALABELS" styles="LabelFont"/><apply toObject="CAPTION" styles="CaptionFont"/></application></styles></chart>';
	            var a = '<chart caption="地区分布(2832260人)" useRoundEdges="1" showBorder="0" showLegend="1" formatNumberScale="0" baseFontSize="12" toolTipSepChar="" labelSepChar=""><set value="1" label="a" /><styles><definition><style name="CaptionFont" type="FONT" face="Verdana" size="15" color="7D8892" bold="1"/><style name="LabelFont" type="FONT" color="7D8892" bold="1"/></definition><application><apply toObject="DATALABELS" styles="LabelFont"/><apply toObject="CAPTION" styles="CaptionFont"/></application></styles></chart>';
	            myChart
				.setXMLData(showData);*/
	            
	           //将对应的英文提换转为中文  
	            myChart.configure("ChartNoDataText","没有查询到相关数据");  
	            myChart.configure("LoadDataErrorText","加载数据出错啦!");  
	            myChart.configure("XMLLoadingText","加载数据中...");  
	            myChart.configure("InvalidXMLText","初始化...");  
	            myChart.configure("ReadingDataText","读取数据...");  
	            myChart.configure("ChartNotSupported","图表不支持");  
	            myChart.configure("LoadingText","加载"); 
	            myChart.render("divPieChart");  
			}else{
				layer.msg("统计失败");
			}
			console.log(data);
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			layer.msg("未知错误，请联系管理员");
		},
		complete:function(XMLHttpRequest, textStatus){
			layer.closeAll('loading');
		}
	});
	
};



