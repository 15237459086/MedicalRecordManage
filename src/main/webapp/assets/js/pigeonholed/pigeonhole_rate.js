
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
	
	var outHospitalTypes = baseInfo['outHospitalTypes'];
	$("select[name='outHospitalTypeCode']").each(function(){
		var options = "";
		for(var index in outHospitalTypes){
			var outHospitalType = outHospitalTypes[index];
	    	options+="<option value='"+outHospitalType.uniq_code+"'>"+outHospitalType.label+"</option>"
	    } 
       $(this).append(options);
	});
}


/*点击查询按钮*/
function queryBtnClick(){
	$("input[name='currentPage']").val(1);
	queryFormSubmit();
}

/*提交查询*/
function queryFormSubmit(){
	var submitData = $('#queryForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
	clearPage();
	$.ajax({
		url: basePath + "medical_record/ajax_query_pigeonholed_rate",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var params = data['data'];
				var totalCounts = params['totalCounts'];
				if(totalCounts > 0){
					layer.msg("查询成功");
					var medicalRecordRates = params['queryDatas'];
					addRows(medicalRecordRates);
					var pageSize = params['pageSize'];
					var currentPage = params['currentPage'];
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
function addRows(medicalRecordRates){
	for(var index in medicalRecordRates){
		var medicalRecordRate = medicalRecordRates[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden").removeAttr("id");
		add_content.find("span[class='out_dept_name']").html(medicalRecordRate.out_dept_name);
		add_content.find("span[class='medical_record_count']").html(medicalRecordRate.medical_record_count);
		add_content.find("span[class='medical_record_pigeonhold_count']").html(medicalRecordRate.medical_record_pigeonhold_count);
		add_content.find("span[class='medical_record_pigeonhold_rate']").html(medicalRecordRate.medical_record_pigeonhold_rate);
		add_content.find("span[class='two_dys_pigeonhole_count']").html(medicalRecordRate.two_dys_pigeonhole_count);
		add_content.find("span[class='two_dys_pigeonhole_rate']").html(medicalRecordRate.two_dys_pigeonhole_rate+"%");
		add_content.find("span[class='three_dys_pigeonhole_count']").html(medicalRecordRate.three_dys_pigeonhole_count);
		
		add_content.find("span[class='three_dys_pigeonhole_rate']").html(medicalRecordRate.three_dys_pigeonhole_rate+"%");
		add_content.find("span[class='seven_dys_pigeonhole_count']").html(medicalRecordRate.seven_dys_pigeonhole_count);
		add_content.find("span[class='seven_dys_pigeonhole_rate']").html(medicalRecordRate.seven_dys_pigeonhole_rate+"%");
		$("#query_show_table tbody").append(add_content);
    } 
	
}

/*清空列表*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
}


