
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
		url: basePath + "data_manage/ajax_query_page_index",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var medicalRecords = data['data'];
				if(medicalRecords.length > 0){
					layer.msg("查询成功");
					$("#export_h").html("<input type='button' value='导出' onclick='exportToExcel()'/>");
					addRows(medicalRecords);
				}else{
					$("#export_h").html("");
					layer.msg("查询结果为空");
				}
				
			}else{
				layer.msg("查询失败");
			}
			console.log(data);
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status == 200){
				if(textStatus == 'parsererror'){
					layer.msg("认证已失效，请重新登录");
				}else{
					layer.msg("未知错误，请联系管理员");
				}
			}else if(XMLHttpRequest.status == 0){
				if(textStatus == 'error'){
					layer.msg("无法连接服务器，请检测网络");
				}else{
					layer.msg("未知错误，请联系管理员");
				}
			}
			else{
				layer.msg("未知错误，请联系管理员");
				console.log(XMLHttpRequest);
				console.log(XMLHttpRequest.status);
				console.log(textStatus)
			}
			
		},
		complete:function(XMLHttpRequest, textStatus){
			layer.closeAll('loading');
		}
	});
	
};

/*添加列表行*/
function addRows(medicalRecords){
	for(var index in medicalRecords){
		var medicalRecord = medicalRecords[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden");
		add_content.attr("id",medicalRecord.visit_guid);
		add_content.find("span[class='only_id']").html(medicalRecord.onlyId);
		add_content.find("span[class='mr_id']").html(medicalRecord.mrId);
		add_content.find("span[class='patient_name']").html(medicalRecord.patientName);
		add_content.find("span[class='id_number']").html(medicalRecord.idNumber);
		add_content.find("span[class='visit_number']").html(medicalRecord.visitNumber);
		add_content.find("span[class='out_dept_name']").html(medicalRecord.outDeptName);
		add_content.find("span[class='out_hospital_date']").html(medicalRecord.outHospitalDateFormat);
		add_content.find("span[class='out_hospital_type_name']").html(medicalRecord.outHospitalTypeName);
		add_content.find("span[class='quality_control_status_name']").html(medicalRecord.statusName);
		$("#query_show_table tbody").append(add_content);
    } 
	
}

/*清空列表和分页控件*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
}


function exportToExcel(){
	var basePath = $("#basePath").val();
	$("#queryForm").attr("action",basePath+"data_manage/page_index_to_excel");
	$("#queryForm").submit();
}
