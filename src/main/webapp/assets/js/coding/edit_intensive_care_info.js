function initPage(baseInfoJson,intensiveCareInfoJson){
	var basePath = $("#basePath").val();
	var ICUTypes = baseInfoJson['ICUTypes'];//	ICU类型
	
	
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});
	
	$(".ICUType").each(function(){
		var options = "";
		for(var index in ICUTypes){
			var ICUType = ICUTypes[index];
	    	options+="<option value='"+ICUType.uniq_code+"'>"+ICUType.label+"</option>"
	    } 
       $(this).append(options);
	});
	
	var intensiveCareRecords = intensiveCareInfoJson['intensiveCareRecords'];//重症监控明细集合
	for(var index in intensiveCareRecords){
		var intensiveCareRecord = intensiveCareRecords[index];
		var add_content= addIntensiveCareRecord();
		add_content.find("input[name$='.inIcuDateTime']").val(intensiveCareRecord.inIcuDateTime);
		add_content.find("input[name$='.outIcuDateTime']").val(intensiveCareRecord.outIcuDateTime);
		add_content.find("input[name$='.apacheScore']").val(intensiveCareRecord.apacheScore);
		var reIcuComment = intensiveCareRecord.reIcuComment;
		if(reIcuComment != null){
			add_content.find("textarea[name$='.reIcuComment']").text(reIcuComment);
		}
		
		if(intensiveCareRecord.icuTypeCode){
			var selectOption = add_content.find("select[name$='icuTypeCode']").find("option[value='"+intensiveCareRecord.icuTypeCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				$(item).append("<option selected='selected' value='"+intensiveCareRecord.icuTypeCode+"'>"+intensiveCareRecord.icuTypeName+"</option>");
			}
			
		};
		add_content.find("input[name$='.icuTypeName']").val(intensiveCareRecord.icuTypeName);
		if(intensiveCareRecord.reIcuPlanCode){
			
			add_content.find("input[name$='.reIcuPlanCode'][value='"+intensiveCareRecord.reIcuPlanCode+"']").attr("checked",true);
			
		}
		add_content.find("input[name$='.reIcuPlanName']").val(intensiveCareRecord.reIcuPlanName);
		if(intensiveCareRecord.icuDeathCode){
			
			add_content.find("input[name$='.icuDeathCode'][value='"+intensiveCareRecord.icuDeathCode+"']").attr("checked",true);
			
		}
		add_content.find("input[name$='.icuDeathName']").val(intensiveCareRecord.icuDeathName);
	} 
	
	
	
	if(intensiveCareRecords.length <1){
		addIntensiveCareRecord();
	}else{
		$("#intensive_care_ul li").first().click();
	}
	
	var validator = $("#intensiveCareInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			var url = $(form).attr("action");
			layer.load(1);
			/*if(url){
				return true;
			}*/
			/*return checkBasicInfoData();*/
			return true;
		},
		rules:{
			icuNurseDayNumber: {
            	digits:true,
            	range:[1,200]
            },
            ccuNurseDayNumber: {
            	digits:true,
            	range:[1,200]
            },
		}
	});
}

function addIntensiveCareRecord(){
	var basePath = $("#basePath").val();
	var intensiveCareCountOjb = $("#intensiveCareRecordCount");
	var count = parseInt(intensiveCareCountOjb.val());
	var addli=$("#template_intensive_care_ul li:eq(0)").clone();
    addli.text("记录"+(count+1));
    addli.attr("data-index",count);
    $("#intensive_care_ul").append(addli);
    addli.addClass("selected").siblings().removeClass("selected");
    addli.click(function(){
    	$(this).addClass("selected").siblings().removeClass("selected");
        $("#intensiveCareRecord"+$(this).attr("data-index")).show().siblings().hide();
    });
	var add_content=$("#templateIntensiveCare").clone();
	add_content.attr("id","intensiveCareRecord"+count);
	
	add_content.find("input[name^='intensiveCareRecords[0].']").each(function(){
		var eventObj = $(this);
		eventObj.attr("name",eventObj.attr("name").replace("intensiveCareRecords[0]", "intensiveCareRecords["+count+"]"));
   		
	});
	
	add_content.find("select[name^='intensiveCareRecords[0].']").each(function(){
		var eventObj = $(this);
		eventObj.attr("name",eventObj.attr("name").replace("intensiveCareRecords[0]", "intensiveCareRecords["+count+"]"));
	});
	
	add_content.find("textarea[name^='intensiveCareRecords[0].']").each(function(){
		var eventObj = $(this);
		eventObj.attr("name",eventObj.attr("name").replace("intensiveCareRecords[0]", "intensiveCareRecords["+count+"]"));
	});
	
	add_content.find("select[class='ICUType']").each(function(){
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
	
	add_content.find("input[name$='.reIcuPlanCode']").each(function(){
		 $(this).click(function(){
			 var reIcuPlanName = "";
			 if($(this).val()=="1"){
				 reIcuPlanName = "是";
			 }else if($(this).val()=="0"){
				 reIcuPlanName = "否";
			 }
			 $(this).nextAll("input[name='"+$(this).attr("name").replace("reIcuPlanCode", "reIcuPlanName")+"']").val(reIcuPlanName);
		 });
	});
	
	add_content.find("input[name$='.icuDeathCode']").each(function(){
		 $(this).click(function(){
			 var icuDeathName = "";
			 if($(this).val()=="1"){
				 icuDeathName = "是";
			 }else if($(this).val()=="0"){
				 icuDeathName = "否";
			 }
			 $(this).nextAll("input[name='"+$(this).attr("name").replace(".icuDeathCode", ".icuDeathName")+"']").val(icuDeathName);
		 });
	});
	
	$("#intensiveCareRecords").append(add_content);
	add_content.show().siblings().hide();
	intensiveCareCountOjb.val(count +1);
	return add_content;
}

function deleteIntensiveCareRecord(){
	if($("#intensive_care_ul li").length > 1){
		var delete_option = $("#intensive_care_ul li[class='selected']");
		var delete_index = delete_option.attr("data-index");
		delete_option.remove();
		
	    $("#intensive_care_ul li:last").addClass("selected").siblings().removeClass("selected");
	    /*内容删除最后一项*/
	    $("#intensiveCareRecords"+delete_index).remove();
	    $("#intensiveCareRecords div:last").show().siblings().hide();
	}
	
}