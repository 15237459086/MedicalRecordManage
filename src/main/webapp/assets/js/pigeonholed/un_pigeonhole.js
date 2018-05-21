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
		url: basePath + "medical_record/ajax_query_un_pigeonhole",
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
		/* add_content.find("span[class='out_dept_name']").html(medicalRecord.out_dept_name); */
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
function pigeonholeSubmit(){
	
	var basePath = $("#basePath").val();
	var visitGuid = $("#layerPigeonholeTable input[name='visitGuid']").val();
	var pigeonholeDateTime = $("#layerPigeonholeTable input[name='pigeonholeDateTime']").val();
	if(!pigeonholeDateTime){
		layer.msg("归档日期不能为空！");
		return;
	}
	var treatmentSignId = $("#layerPigeonholeTable input[name='treatmentSignId']").is(':checked');
	var submitData="visitGuid="+visitGuid+ 
					"&pigeonholeDateTime="+pigeonholeDateTime;
	layer.load(1);	
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
}


function pigeonholeFormShow(obj){
	
	var add_content=$("#pigeonhole_table").clone();
	add_content.find("table").attr("id","layerPigeonholeTable");
	pigeonholeFormIndex = layer.open({
		  type: 1,
		  title:'病案归档',
		  skin: 'layui-layer-rim', //加上边框
		  area: ['420px', '520px'], //宽高
		  content: add_content.html(),
		  success: function(layero, index){
			  var content = $(obj).parent().parent();
			  $("#layerPigeonholeTable input[name='visitGuid']").val(content.attr("id"));
			  $("#layerPigeonholeTable input[name='patientName']").val(content.find(".patient_name").html());
			  $("#layerPigeonholeTable input[name='idNumber']").val(content.find(".id_number").html());
			  $("#layerPigeonholeTable input[name='mrId']").val(content.find(".mr_id").html());
			  $("#layerPigeonholeTable input[name='visitNumber']").val(content.find(".visit_number").html());
			  
			  $("#layerPigeonholeTable input[name='outDeptName']").val(content.find(".out_dept_name").html());
			  $("#layerPigeonholeTable input[name='outHospitalDateTime']").val(content.find(".out_hospital_date").html());
			  $("#layerPigeonholeTable input[name='outHospitalTypeName']").val(content.find(".out_hospital_type_name").html());
			  var currentDate = new Date();
			  var now =formatDate(currentDate);
			  $("#layerPigeonholeTable input[name='pigeonholeDateTime']").val(now);
		  }
		});
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
		  
		  $("#addForm input,select").each(function() {
			  $(this).attr("id",$(this).attr("name"));
		  })
		  
		  var validator = $("#addForm").validate({
				errorElement: "title",
				ignoreTitle: true,
				submitHandler : function(){
					addFormSubmit();
				},
				rules:{
					onlyId: {
		                minlength: 6,
		                maxlength: 20,
		            },
					patientName: {
		                minlength: 2,
		                maxlength: 8,
		                required: true
		            },
		            idNumber: {
		                idCard:true
		            },
					mrId: {
		                minlength: 6,
		                maxlength: 20,
		                required: true
		            },
		            visitNumber: {
		            	range:[1,2000],
		            	digits:true,
		                required: true
		            },
		            outDeptCode: {
		                required: true
		            },
		            outHospitalDateTime: {
		                required: true,
		                date:true
		            },
		            outHospitalTypeCode: {
		                required: true
		            }
		            
				},
				
			});
	  }
	});
}
function clickAddFormSubmitBtn(){
	$('#addForm').submit();
}

function addFormSubmit(){
	var submitData = $('#addForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
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
					$("#queryForm input[name='mrId']").val($("#addForm input[name='mrId']").val());
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
}
