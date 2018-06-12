
$(function(){
	/*$("select").each(function(){
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
	});*/
	
	var validator = $("#statisticsForm").validate({
		errorElement: "title",
		ignoreTitle: true,
		submitHandler : function(){
			statisticsFormSubmit();
		},
		rules:{
			startDate: {
				required: true
            },
            endDate: {
				required: true
            }
		}
		
	});
});

function init(baseInfo){
	/*var medicalDepts = baseInfo['medicalDepts'];
	$("select[name='outHospitalDeptCode'],[name='outDeptCode']").each(function(){
		var options = "";
		for(var index in medicalDepts){
			var medicalDept = medicalDepts[index];
	    	options+="<option value='"+medicalDept.uniq_code+"'>"+medicalDept.label+"</option>"
	    } 
       $(this).append(options);
       
	});*/
}


/*点击统计按钮*/
function statisticsBtnClick(){
	$("#statisticsForm").submit();
}

/*提交查询*/
function statisticsFormSubmit(){
	var submitData = $('#statisticsForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
	clearPage();
	$.ajax({
		url: basePath + "work_statistics/coding_statistics",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var datas = data['data'];
				addRows(datas);
				
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

/*添加列表行*/
function addRows(datas){
	var basePath = $("#basePath").val();
	var medicalRecordCountOfCodingeds = datas['groupMedicalRecordCountOfCodingeds'];
	if(medicalRecordCountOfCodingeds.length == 0){
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden");
		add_content.removeAttr("id");
		add_content.find("span[class='medicalRecordCount']").html(datas.medicalRecordCount);
		$("#query_show_table tbody").append(add_content);
		/*add_content.find("span[class='mr_id']").html(medicalRecord.mr_id);
		add_content.find("span[class='patient_name']").html(medicalRecord.patient_name);
		add_content.find("span[class='id_number']").html(medicalRecord.id_number);*/
	}else{
		for(var index in medicalRecordCountOfCodingeds){
			var medicalRecordCountOfCodinged = medicalRecordCountOfCodingeds[index];
			var add_content=$("#template_tr").clone();
			add_content.removeAttr("hidden");
			add_content.removeAttr("id");
			if(index == 0){
				add_content.find("span[class='medicalRecordCount']").parent().attr("rowspan",medicalRecordCountOfCodingeds.length);
				add_content.find("span[class='medicalRecordCount']").html(datas.medicalRecordCount);
				
				add_content.find("span[class='create_user_name']").html(medicalRecordCountOfCodinged.create_user_name);
				add_content.find("span[class='count']").html(medicalRecordCountOfCodinged.count);
				add_content.find("span[class='codingCountRate']").html(medicalRecordCountOfCodinged.codingCountRate);
			}else{
				add_content.find("span[class='medicalRecordCount']").parent().remove();
				add_content.find("span[class='create_user_name']").html(medicalRecordCountOfCodinged.create_user_name);
				add_content.find("span[class='count']").html(medicalRecordCountOfCodinged.count);
				add_content.find("span[class='codingCountRate']").html(medicalRecordCountOfCodinged.codingCountRate);
			}
			/*add_content.find("span[class='only_id']").html(medicalRecord.only_id);
			add_content.find("span[class='mr_id']").html(medicalRecord.mr_id);
			add_content.find("span[class='patient_name']").html(medicalRecord.patient_name);
			add_content.find("span[class='id_number']").html(medicalRecord.id_number);
			add_content.find("span[class='visit_number']").html(medicalRecord.visit_number);
			add_content.find("span[class='out_dept_name']").html(medicalRecord.out_dept_name);
			add_content.find("span[class='out_hospital_date']").html(medicalRecord.out_hospital_date);
			add_content.find("span[class='out_hospital_type_name']").html(medicalRecord.out_hospital_type_name);*/
			$("#query_show_table tbody").append(add_content);
	    }
	}
	 
	
}

/*清空列表*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
}

