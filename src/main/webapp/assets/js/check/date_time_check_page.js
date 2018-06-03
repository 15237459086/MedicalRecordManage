
$(function(){
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});
	
});
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
		url: basePath + "medical_record_coding/ajax_date_time_check",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var medicalRecords = data['data'];
				if(medicalRecords.length > 0){
					layer.msg("审核成功");
					addRows(medicalRecords);
				}else{
					layer.msg("审核结果为空");
				}
				
			}else{
				layer.msg("审核失败");
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
function addRows(medicalRecords){
	for(var index in medicalRecords){
		var medicalRecord = medicalRecords[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden");
		add_content.attr("id",medicalRecord.visit_guid);
		add_content.find("span[class='only_id']").html(medicalRecord.only_id);
		add_content.find("span[class='mr_id']").html(medicalRecord.mr_id);
		add_content.find("span[class='visit_number']").html(medicalRecord.visit_number);
		add_content.find("span[class='patient_name']").html(medicalRecord.patient_name);
		add_content.find("span[class='id_number']").html(medicalRecord.id_number);
		add_content.find("span[class='check_desc']").html(medicalRecord.check_desc);
		$("#query_show_table tbody").append(add_content);
    } 
	
}

/*清空列表和分页控件*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
}

