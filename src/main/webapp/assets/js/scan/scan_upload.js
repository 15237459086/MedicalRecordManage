
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

var addFormIndex;
var pigeonholeFormIndex;

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
		url: basePath + "medical_record_scan/ajax_query_scan",
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
		add_content.find("span[class='quality_control_status_name']").html(medicalRecord.quality_control_status_name);
		$("#query_show_table tbody").append(add_content);
    } 
	
}

/*清空列表和分页控件*/
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


function addFormSubmit(){
	var submitData = $('#addForm').serialize();
	var basePath = $("#basePath").val();
	
	$.ajax({
		url: basePath + "medical_record/ajax_add_medical_record",
		type: "POST",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				var stateCode = data['stateCode'];
				if("2" == stateCode){
					layer.msg("该病案已存在！");
					
				}else{
					layer.msg("添加成功！");
					layer.close(addFormIndex);
					$('#queryBtn').click();
				}
			}else{
				layer.msg("操作错误，请重试！");
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
	/*layer.close(addFormIndex);*/
}


function pigeonholeSubmit(){
	
	var basePath = $("#basePath").val();
	var visitGuid = $("#layerPigeonholeTable input[name='visitGuid']").val();
	var pigeonholeDateTime = $("#layerPigeonholeTable input[name='pigeonholeDateTime']").val();
	var treatmentSignId = $("#layerPigeonholeTable input[name='treatmentSignId']").is(':checked');
	var submitData="visitGuid="+visitGuid+ 
					"&pigeonholeDateTime="+pigeonholeDateTime;
					
	if(treatmentSignId){
		submitData = submitData+"&treatmentSignId="+ 1;
	}else{
		submitData = submitData+"&treatmentSignId="+ 0;
	}
	$.ajax({
		url: basePath + "medical_record/ajax_pigeonhole_medical_record",
		type: "POST",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				var stateCode = data['stateCode'];
				if("1" == stateCode){
					layer.msg("归档成功！");
					layer.close(pigeonholeFormIndex);
					$("#"+visitGuid +" td").last().html("已归档");
				}else{
					layer.msg("归档失败！");
				}
			}else{
				layer.msg("操作错误，请重试！");
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
	/*layer.close(addFormIndex);*/
}


function scanUploadFormShow(obj){
	
	$("#query_div").attr("hidden","hidden");
	$("#scan_uplaod_div").removeAttr("hidden");
	$("#btn_reset").click();
	 var content = $(obj).parent().parent();
	 var trOjb = $("#scan_upload_tb tbody tr");
	 trOjb.attr("id",content.attr("id"));
	 trOjb.find(".only_id").html(content.find(".only_id").html());
	 trOjb.find(".mr_id").html(content.find(".mr_id").html());
	 trOjb.find(".patient_name").html(content.find(".patient_name").html());
	 trOjb.find(".id_number").html(content.find(".id_number").html());
	 trOjb.find(".visit_number").html(content.find(".visit_number").html());
	 trOjb.find(".out_dept_name").html(content.find(".out_dept_name").html());
	 trOjb.find(".out_hospital_date").html(content.find(".out_hospital_date").html());
	 trOjb.find(".out_hospital_type_name").html(content.find(".out_hospital_type_name").html());
	 trOjb.find(".quality_control_status_name").html(content.find(".quality_control_status_name").html());
	
}

function btnRetreat(){
	
	$("#scan_uplaod_div").attr("hidden","hidden");
	$("#query_div").removeAttr("hidden");
	$("#btn_reset").click();
	 var trOjb = $("#scan_upload_tb tbody tr");
	 trOjb.removeAttr("id");
	 trOjb.find(".only_id").html("");
	 trOjb.find(".mr_id").html("");
	 trOjb.find(".patient_name").html("");
	 trOjb.find(".id_number").html("");
	 trOjb.find(".visit_number").html("");
	 trOjb.find(".out_dept_name").html("");
	 trOjb.find(".out_hospital_date").html("");
	 trOjb.find(".out_hospital_type_name").html("");
	 trOjb.find(".quality_control_status_name").html("");
}

function formatDate(myDate){
	
    //获取当前年
	var year = myDate.getFullYear();

	//获取当前月
	var month = myDate.getMonth() + 1;
	month = month < 10 ? '0' + month : month
    //获取当前日
	var date = myDate.getDate();
	date = date < 10 ? '0' + date : date
    var h = myDate.getHours(); //获取当前小时数(0-23)
	h = h < 10 ? '0' + h : h
    var m = myDate.getMinutes(); //获取当前分钟数(0-59)
	m = m < 10 ? '0' + m : m
    var s = myDate.getSeconds();
	s = s < 10 ? '0' + s : s

   //获取当前时间

   var now = year + '-' + month + "-" + date + " " + h + ':' + m + ":" + s;
   return now;
}
//日期时间处理
function conver(s) {
return s < 10 ? '0' + s : s;
}

function addFormShow(){
	
	//页面层
	var add_content=$("#new_table").clone();
	add_content.find("form").attr("id","addForm");
	
	addFormIndex = layer.open({
	  type: 1,
	  title:'新增病案',
	  skin: 'layui-layer-rim', //加上边框
	  area: ['420px', '450px'], //宽高
	  content: add_content.html(),
	  success: function(layero, index){
		  $("#addForm select[name='outDeptCode'],[name='outHospitalTypeCode']").each(function(){
			$(this).change(function(){
		   		var eventObj = $(this);
		   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
		   		
		   		if(eventObj.val()!=""){
		   			var name=eventObj.find("option:selected").text();
		   			eventObj.next("input[name='"+nameObjName+"']").val(name);
		   		}else{
		   			eventObj.next("input[name='"+nameObjName+"']").val("");
		   		}
		   });
		 });
	  }
	});
}
