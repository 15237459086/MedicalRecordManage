
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
		url: basePath + "medical_record/ajax_query_trace",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var params = data['data'];
				var totalCounts = params['totalCounts'];
				if(totalCounts > 0){
					layer.msg("查询成功");
					var medicalRecords = params['queryDatas'];
					addRows(medicalRecords);
					var pageSize = params['pageSize'];
					var currentPage = params['currentPage'];
					initPage(parseInt(totalCounts),parseInt(pageSize),parseInt(currentPage));
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
function addRows(medicalRecords){
	var basePath = $("#basePath").val();
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
		$("#query_show_table tbody").append(add_content);
    } 
	
}

/*清空列表*/
function clearPage(){
	$("#template_tr").siblings("tr").remove();
	$("#page_plus").html("");
	$("#totalPage").html("0");
    $("#currentPage").html("0/0");
}

/*初始化分页控件*/
function initPage(totalCounts,pageSize,currentPage){
	 var visiblePages = 5;
	 var totalPages =  totalCounts%pageSize==0?(totalCounts/pageSize):(parseInt(totalCounts/pageSize)+1);
	 if(totalCounts < 1){
		$("#page_plus").html("");
		$("#totalPage").html("0");
        $("#currentPage").html("0/0");
	 }else{
		 $('#page_plus').jqPaginator({

	        totalCounts: totalCounts,
	        pageSize:pageSize,
	        visiblePages: visiblePages,
	        currentPage: currentPage,

	        first:'<li class="first"><a href="javascript:void(0);">首页</a></li>',
	        prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
	        next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
	        last: '<li class="last"><a href="javascript:void(0);">末页</a></li>',
	        page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
	        onPageChange: function (num,type) {

	     		if(type !='init'){
	     			$("input[name='currentPage']").val(num);
	     			queryFormSubmit();
				}
	            $("#totalPage").html(totalCounts);
	            $("#currentPage").html(num+"/"+totalPages);
	        }
	    });
	 }
}

function traceDetailShow(obj){
	var visitGuid =  $(obj).parent().parent().attr("id");
	var basePath = $("#basePath").val();
	
	//页面层
	var add_content=$("#template_layer_div").clone();
	add_content.find("#template_layer_record").attr("id","layer_record");
	add_content.find("#template_layer_trace").attr("id","layer_trace");
	add_content.find("#template_layer_trace_tr").attr("id","layer_trace_tr");
	layer.open({
	  type: 1,
	  title:'示踪明细',
	  skin: 'layui-layer-rim', //加上边框
	  area: ['930px', '450px'], //宽高
	  content: add_content.html(),
	  success: function(layero, index){
		  $.ajax({
				url: basePath + "medical_record/ajax_trace_detail",
				type: "GET",
				dataType: "json",
				data:{visitGuid:visitGuid},
				success: function( datas ) {
					var success = datas['success'];
					if(success){
						var data =  datas['data'];
						var medicalRecords = data['medicalRecords'];
						var medicalRecordTraces = data['medicalRecordTraces'];
						var medicalRecord = medicalRecords[0];
						$("#layer_record #layerMrId").val(medicalRecord.mr_id);
						$("#layer_record #layerOnlyId").val(medicalRecord.only_id);
						$("#layer_record #layerPatientName").val(medicalRecord.patient_name);
						$("#layer_record #layerIdNumber").val(medicalRecord.id_number);
						$("#layer_record #layerOutHospitalDate").val(medicalRecord.out_hospital_date);
						$("#layer_record #layerOutDeptName").val(medicalRecord.out_dept_name);
						for(var index in medicalRecordTraces){
							var medicalRecordTrace = medicalRecordTraces[index];
							var add_content=$("#layer_trace_tr").clone();
							add_content.removeAttr("hidden").removeAttr("id");
							add_content.find("span[class='trace_type_name']").html(medicalRecordTrace.trace_type_name);
							add_content.find("span[class='create_date_time_format']").html(medicalRecordTrace.create_date_time_format);
							add_content.find("span[class='create_user_name']").html(medicalRecordTrace.create_user_name);
							add_content.find("span[class='remark']").html(medicalRecordTrace.remark);
							$("#layer_trace tbody").append(add_content);
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

