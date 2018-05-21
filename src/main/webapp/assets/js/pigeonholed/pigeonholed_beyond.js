
$(function(){
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});

	var validator = $("#queryForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			var url = $(form).attr("action");
			if(url){
				return true;
			}else{
				queryFormSubmit();
				return false;
			}
			
			
		},
		messages: {
			outHospitalStartDate: {
				required: " (必需字段)"
			},outHospitalEndDate: {
				required: " (必需字段)"
			}
		}
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
	$("#queryForm").removeAttr("action",null);
	$("input[name='currentPage']").val(1);
	$("#queryForm").submit();
	/*queryFormSubmit();*/
}

/*提交查询*/
function queryFormSubmit(){
	var submitData = $('#queryForm').serialize();
	var basePath = $("#basePath").val();
	clearPage();
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record/ajax_query_pigeonholed_beyond",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var params = data['data'];
				var totalCounts = params['totalCounts'];
				var medicalRecords = params['queryDatas'];
				var beyondRate = ((medicalRecords.length/totalCounts) * 100).toFixed(2);
				if(!isNaN(beyondRate) && medicalRecords.length > 0){
					$("#beyond_rate").html("迟送率:"+beyondRate+"%  <input type='button' value='导出' onclick='exportToExcel()'/>");
				}
				
				if(medicalRecords.length > 0){
					layer.msg("查询成功");
					addRows(medicalRecords);
					
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

/*清空列表和分页控件*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
	$("#beyond_rate").html("");
}
/*添加列表行*/
function addRows(medicalRecords){
	for(var index in medicalRecords){
		var medicalRecord = medicalRecords[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden");
		add_content.attr("id",medicalRecord.visit_guid);
		add_content.find("span[class='only_id']").html(medicalRecord.only_id);
		add_content.find("span[class='mr_id']").html(medicalRecord.mr_id);
		add_content.find("span[class='patient_name']").html(medicalRecord.patient_name);
		add_content.find("span[class='id_number']").html(medicalRecord.id_number);
		add_content.find("span[class='visit_number']").html(medicalRecord.visit_number);
		add_content.find("span[class='out_dept_name']").html(medicalRecord.out_dept_name);
		add_content.find("span[class='out_hospital_date']").html(medicalRecord.out_hospital_date);
		add_content.find("span[class='out_hospital_type_name']").html(medicalRecord.out_hospital_type_name);
		
		add_content.find("span[class='trace_date_format']").html(medicalRecord.trace_date_format);
		
		add_content.find("span[class='beyond_number_day']").html(medicalRecord.beyond_number_day);
		$("#query_show_table tbody").append(add_content);
    } 
	
}

function exportToExcel(){
	var basePath = $("#basePath").val();
	$("#queryForm").attr("action",basePath+"medical_record/pigeonholedBeyondToExcel");
	$("#queryForm").submit();
}
