
$(function(){
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});

	var basePath = $("#basePath").val();
	$.ajax({
		url: basePath + "base_info/ajax_print_base_data",
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
	
	
	var relativeRelations = baseInfo['relativeRelations'];
	$("select[name='claimerRelativeRelationCode']").each(function(){
		var options = "";
		for(var index in relativeRelations){
			var relativeRelation = relativeRelations[index];
	    	options+="<option value='"+relativeRelation.uniq_code+"'>"+relativeRelation.label+"</option>"
	    } 
       $(this).append(options);
       
	});
	
	
	var printerTypes = baseInfo['printerTypes'];
	$("select[name='applyPrinterTypeCode']").each(function(){
		var options = "";
		for(var index in printerTypes){
			var printerType = printerTypes[index];
	    	options+="<option value='"+printerType.uniq_code+"'>"+printerType.label+"</option>"
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
		url: basePath + "medical_record_print/ajax_query_print_apply",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			var success = data['success'];
			if(success){
				
				var params = data['data'];
				var totalCounts = params['totalCounts'];
				if(totalCounts > 0){
					layer.msg("查询成功");
					var printerApplys = params['queryDatas'];
					addRows(printerApplys);
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
function addRows(printerApplys){
	for(var index in printerApplys){
		var printerApply = printerApplys[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden");
		add_content.attr("id",printerApply.id);
		add_content.find("span[class='claimer_name']").html(printerApply.claimer_name);
		add_content.find("span[class='claimer_code']").html(printerApply.claimer_code);
		add_content.find("span[class='claimer_phone']").html(printerApply.claimer_phone);
		add_content.find("span[class='apply_date_format']").html(printerApply.apply_date_format);
		add_content.find("span[class='claimer_relative_relation_name']").html(printerApply.claimer_relative_relation_name);
		add_content.find("span[class='apply_remark']").html(printerApply.apply_remark);
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


function printerApplyItemShow(obj){
	var content = $(obj).parent().parent();
	var printerApplyId =  $(obj).parent().parent().attr("id");
	var basePath = $("#basePath").val();
	
	//页面层
	var add_content=$("#template_layer_div").clone();
	add_content.find("#template_layer_apply").attr("id","layer_apply");
	add_content.find("#template_layer_item").attr("id","layer_item");
	add_content.find("#template_layer_item_tr").attr("id","layer_item_tr");
	
	layer.open({
	  type: 1,
	  title:'打印项',
	  skin: 'layui-layer-rim', //加上边框
	  area: ['930px', '450px'], //宽高
	  content: add_content.html(),
	  success: function(layero, index){
		  
		  $("#layer_apply #layerClaimerName").val(content.find(".claimer_name").html());
		  $("#layer_apply #layerClaimerCode").val(content.find(".claimer_code").html());
		  $("#layer_apply #layerClaimerPhone").val(content.find(".claimer_phone").html());
		  $("#layer_apply #layerApplyDateTime").val(content.find(".apply_date_format").html());
		  $("#layer_apply #layerRelativeRelationName").val(content.find(".claimer_relative_relation_name").html());
		  $("#layer_apply #layerApplyRemark").val(content.find(".apply_remark").html());
		  
		  $.ajax({
				url: basePath + "medical_record_print/ajax_query_print_apply_item",
				type: "GET",
				dataType: "json",
				data:{printerApplyId:printerApplyId},
				success: function( datas ) {
					var success = datas['success'];
					if(success){
						var printerApplyItems =  datas['data'];
						for(var index in printerApplyItems){
							var printerApplyItem = printerApplyItems[index];
							var add_content=$("#layer_item_tr").clone();
							add_content.removeAttr("hidden").removeAttr("id");
							add_content.find("span[class='only_id']").html(printerApplyItem.only_id);
							add_content.find("span[class='mr_id']").html(printerApplyItem.mr_id);
							add_content.find("span[class='visit_number']").html(printerApplyItem.visit_number);
							add_content.find("span[class='patient_name']").html(printerApplyItem.patient_name);
							add_content.find("span[class='patient_code']").html(printerApplyItem.patient_code);
							add_content.find("span[class='out_hospital_date_format']").html(printerApplyItem.out_hospital_date_format);
							add_content.find("span[class='printer_type_name']").html(printerApplyItem.apply_printer_type_name);
							add_content.find("span[class='apply_copies']").html(printerApplyItem.apply_copies);
							
							
							$("#layer_item tbody").append(add_content);
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


