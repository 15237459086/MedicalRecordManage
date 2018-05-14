
$(function(){
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});

	var basePath = $("#basePath").val();
	$.ajax({
		url: basePath + "base_info/ajax_un_pigeonhole_base_data",
		type: "GET",
		dataType: "json",
		success: function( datas ) {
			console.log(datas);
			init(datas);
			
		}
	});
});

function init(baseInfo){
	var medicalDepts = baseInfo['medicalDepts'];
	$("select[name='outHospitalDeptCode'],[name='outDeptCode']").each(function(){
		var options = "";
		for(var index in medicalDepts){
			var medicalDept = medicalDepts[index];
	    	options+="<option value='"+medicalDept.uniq_code+"'>"+medicalDept.label+"</option>"
	    } 
       $(this).append(options);
       
	});
}


/*点击查询按钮*/
function queryBtnClick(){
	queryFormSubmit();
}

/*提交查询*/
function queryFormSubmit(){
	var submitData = $('#queryForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
	clearPage();
	$.ajax({
		url: basePath + "medical_record/ajax_quality_control_score_rate",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var qualityControlScoreRates = data['data'];
				
				if(qualityControlScoreRates.length > 0){
					layer.msg("查询成功");
					
					addRows(qualityControlScoreRates);
				}else{
					layer.msg("查询结果为空");
				}
				
			}else{
				layer.msg("查询失败");
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

/*添加列表行*/
function addRows(qualityControlScoreRates){
	for(var index in qualityControlScoreRates){
		var qualityControlScoreRate = qualityControlScoreRates[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden").removeAttr("id");
		add_content.find("span[class='out_dept_name']").html(qualityControlScoreRate.out_dept_name);
		add_content.find("span[class='total_count']").html(qualityControlScoreRate.total_count);
		
		add_content.find("span[class='quality_control_count']").html(qualityControlScoreRate.quality_control_count);
		add_content.find("span[class='quality_control_rate']").html(qualityControlScoreRate.quality_control_rate+"%");
		add_content.find("span[class='quality_control_a_count']").html(qualityControlScoreRate.quality_control_a_count);
		add_content.find("span[class='quality_control_a_rate']").html(qualityControlScoreRate.quality_control_a_rate+"%");
		add_content.find("span[class='quality_control_b_count']").html(qualityControlScoreRate.quality_control_b_count);
		
		add_content.find("span[class='quality_control_b_rate']").html(qualityControlScoreRate.quality_control_b_rate+"%");
		add_content.find("span[class='quality_control_c_count']").html(qualityControlScoreRate.quality_control_c_count);
		add_content.find("span[class='quality_control_c_rate']").html(qualityControlScoreRate.quality_control_c_rate+"%");
		$("#query_show_table tbody").append(add_content);
    } 
	
}

/*清空列表*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
}


