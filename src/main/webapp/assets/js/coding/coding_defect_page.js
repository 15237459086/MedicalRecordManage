
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
	$("#queryForm").submit();
}

/*提交查询*/
function queryFormSubmit(){
	var submitData = $('#queryForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
	
	clearPage();
	$.ajax({
		url: basePath + "medical_record_coding/query_coding_defect",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var medicalRecordDefects = data['data'];
				if(medicalRecordDefects.length > 0){
					layer.msg("查询结果成功");
					addRows(medicalRecordDefects);
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
function addRows(medicalRecordDefects){
	for(var index in medicalRecordDefects){
		var medicalRecordDefect = medicalRecordDefects[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden");
		add_content.attr("id",medicalRecordDefect.coding_error_code);
		add_content.find("span[class='coding_error_code']").html(medicalRecordDefect.coding_error_code);
		add_content.find("span[class='coding_error_name']").html(medicalRecordDefect.coding_error_name);
		add_content.find("span[class='count']").html(medicalRecordDefect.count);
		$("#query_show_table tbody").append(add_content);
    } 
	
}

/*清空列表和分页控件*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
}


function defectDetailShow(obj){
	var trOjb = $(obj).parent().parent();
	var codingErrorCode =  trOjb.attr("id");
	var basePath = $("#basePath").val();
	var outHospitalStartDate = $("input[name='outHospitalStartDate']").val();
	var outHospitalEndDate = $("input[name='outHospitalEndDate']").val();
	if(!outHospitalStartDate || outHospitalStartDate == null ){
		return;
	}
	if(!outHospitalEndDate || outHospitalEndDate == null ){
		return;
	}
	//页面层
	var add_content=$("#template_layer_div").clone();
	add_content.find("#template_layer_record").attr("id","layer_record");
	add_content.find("#template_layer_defect").attr("id","layer_defect");
	add_content.find("#template_layer_defect_tr").attr("id","layer_defect_tr");
	layer.open({
	  type: 1,
	  title:'缺陷详情',
	  skin: 'layui-layer-rim', //加上边框
	  area: ['930px', '450px'], //宽高
	  content: add_content.html(),
	  success: function(layero, index){
		  $("#layer_record #codingErrorCode").val(trOjb.find(".coding_error_code").html());
		  $("#layer_record #codingErrorName").val(trOjb.find(".coding_error_name").html());
		  $("#layer_record #count").val(trOjb.find(".count").html());
		  $.ajax({
				url: basePath + "medical_record_coding/query_coding_defect_detail",
				type: "GET",
				dataType: "json",
				data:{codingDefectCode:codingErrorCode,outHospitalStartDate:outHospitalStartDate,outHospitalEndDate:outHospitalEndDate},
				success: function( datas ) {
					var success = datas['success'];
					if(success){
						var defectDetails =  datas['data'];
						for(var index in defectDetails){
							var defectDetail = defectDetails[index];
							var add_content=$("#template_defect_tr").clone();
							add_content.removeAttr("hidden").removeAttr("id");
							add_content.find("span[class='mr_id']").html(defectDetail.mr_id);
							add_content.find("span[class='visit_number']").html(defectDetail.visit_number);
							add_content.find("span[class='patient_name']").html(defectDetail.patient_name);
							add_content.find("span[class='id_number']").html(defectDetail.id_number);
							add_content.find("span[class='out_dept_name']").html(defectDetail.out_dept_name);
							add_content.find("span[class='out_hospital_date']").html(defectDetail.out_hospital_date);
							add_content.find("span[class='coder_name']").html(defectDetail.last_error_coder_name);
							$("#layer_defect tbody").append(add_content);
					    } 
					}else{
						layer.msg("操作错误，请重试！");
					}
					console.log(datas);
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					layer.msg("未知错误，请联系管理员");
				},
				complete:function(XMLHttpRequest, textStatus){
					layer.closeAll('loading');
				}
			});
		  
		  
	  }
	});
}
