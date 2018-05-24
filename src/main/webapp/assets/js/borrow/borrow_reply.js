jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile = /^1[34578]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写手机号码");
jQuery.validator.addMethod("idCard", function(value, element) {
    var length = value.length;
    //15位和18位身份证号码的正则表达式  
    var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;  
    //如果通过该验证，说明身份证格式正确，但准确性还需计算  
    if (regIdCard.test(value)) {  
        if (value.length == 18) {  
            var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里  
           var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组  
            var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和  
            for (var i = 0; i < 17; i++) {  
                idCardWiSum += value.substring(i, i + 1) * idCardWi[i];  
            }  
            var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置  
            var idCardLast = value.substring(17);//得到最后一位身份证号码  
           //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if (idCardMod == 2) {  
                if (idCardLast == "X" || idCardLast == "x") {   
                	return this.optional(element) || true; 
                } else {
                    return this.optional(element) || false;  
                }  
            } else {  
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码  
                if (idCardLast == idCardY[idCardMod]) {   
                	return this.optional(element) || true;  
                } else {
                    return this.optional(element) || false; 
                }  
            }  
        }  
    } else {   
        return this.optional(element) || false;  
    }  
}, "请正确填写身份证号码");
$(function(){
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});

	var basePath = $("#basePath").val();
	$.ajax({
		url: basePath + "base_info/ajax_borrow_base_data",
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

var addApplyFormIndex;

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
		url: basePath + "medical_record_borrow/ajax_query_borrow_apply",
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
		add_content.attr("id",medicalRecord.id);
		add_content.find("span[class='only_id']").html(medicalRecord.only_id);
		add_content.find("span[class='mr_id']").html(medicalRecord.mr_id);
		add_content.find("span[class='patient_name']").html(medicalRecord.patient_name);
		add_content.find("span[class='id_number']").html(medicalRecord.patient_code);
		add_content.find("span[class='visit_number']").html(medicalRecord.visit_number);
		add_content.find("span[class='out_dept_name']").html(medicalRecord.out_dept_name);
		add_content.find("span[class='out_hospital_date_format']").html(medicalRecord.out_hospital_date_format);
		add_content.find("span[class='borrower_name']").html(medicalRecord.borrower_name);
		add_content.find("span[class='borrower_date_format']").html(medicalRecord.apply_date_format);
		/*
		var borrowApplying = medicalRecord.applying;
		var borrowPermit = medicalRecord.permit;
		if(borrowApplying && borrowApplying==1){
			add_content.find("span[class='borrow_status']").html("借阅申请中");
			add_content.find("a").parent().html("借阅申请中");
		}else if(borrowPermit && borrowPermit==1){
			add_content.find("span[class='borrow_status']").html("借阅通过");
			add_content.find("a").html("病案预览");
		}else{
			add_content.find("span[class='borrow_status']").html("可借阅");
			
		}*/
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




function borrowReplySubmit(obj,replyCode,confirmMessage){
	var content = $(obj).parent().parent();
	var borrowReplyItemId = content.attr("id");
	var basePath = $("#basePath").val();
	if(!confirm("确定要"+confirmMessage+"吗？"))
	 {
		return false;
	 }
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record_borrow/ajax_borrow_reply",
		type: "POST",
		dataType: "json",
		data:{borrowReplyItemId:borrowReplyItemId,replyCode:replyCode},
		success: function( data ) {
			var success = data['success'];
			if(success){
				var stateCode = data['stateCode'];
				if("1" == stateCode){
					layer.msg("审批成功！");
					if(replyCode =='PERMIT'){
						content.find("span[class='borrow_status']").html("批复");
						content.find("a").parent().html("批复");
					}else{
						content.find("span[class='borrow_status']").html("拒批");
						content.find("a").parent().html("拒批");
					}
					
				}else{
					layer.msg("审批失败！");
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
}



