function initPage(baseInfoJson,operateInfoJson){
	var basePath = $("#basePath").val();
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});
	var incisionLevels = baseInfoJson['incisionLevels'];//切口等级
	$("select[name$='.incisionLevelCode']").each(function(){
		var options = "";
		for(var index in incisionLevels){
			var incisionLevel = incisionLevels[index];
	    	options+="<option value='"+incisionLevel.uniq_code+"'>"+incisionLevel.label+"</option>"
	    } 
       $(this).append(options);
	});
	var cicatrizeTypes = baseInfoJson['cicatrizeTypes'];//愈合类型
	$("select[name$='.cicatrizeTypeCode']").each(function(){
		var options = "";
		for(var index in cicatrizeTypes){
			var cicatrizeType = cicatrizeTypes[index];
	    	options+="<option value='"+cicatrizeType.uniq_code+"'>"+cicatrizeType.label+"</option>"
	    } 
       $(this).append(options);
	});
	
	var opsLevels = baseInfoJson['opsLevels'];//手术等级
	$("select[name$='.opsLevelCode']").each(function(){
		var options = "";
		for(var index in opsLevels){
			var opsLevel = opsLevels[index];
	    	options+="<option value='"+opsLevel.uniq_code+"'>"+opsLevel.label+"</option>"
	    } 
       $(this).append(options);
	});
	

	var anaesthesiaLevels = baseInfoJson['anaesthesiaLevels'];//麻醉分级

	$("select[name$='.anaesthesiaLevelCode']").each(function(){
		var options = "";
		for(var index in anaesthesiaLevels){
			var anaesthesiaLevel = anaesthesiaLevels[index];
	    	options+="<option value='"+anaesthesiaLevel.uniq_code+"'>"+anaesthesiaLevel.label+"</option>"
	    } 
       $(this).append(options);
	});
	
	
	var anaesthesiaWays = baseInfoJson['anaesthesiaWays'];//麻醉方式
	
	$("select[name$='.anaesthesiaTypeCode']").each(function(){
		var options = "";
		for(var index in anaesthesiaWays){
			var anaesthesiaWay = anaesthesiaWays[index];
	    	options+="<option value='"+anaesthesiaWay.uniq_code+"'>"+anaesthesiaWay.label+"</option>"
	    } 
       $(this).append(options);
	});
	
	/* 选项卡切换
    $.each($("#operate_record_ul li"),function(i,item) {
        $(item).on("click", function (){
            $(this).addClass("selected").siblings().removeClass("selected");
            $("#operation_record"+$(this).attr("data-index")).show().siblings().hide();
        });
    });*/
    
	var operateRecords = operateInfoJson['operateRecords'];//手术记录集合
	for(var index in operateRecords){
		var operateRecord = operateRecords[index];
		
		addOperateRecordByRecord(operateRecord);
	}
	
    if($("#operate_record_ul li").length <1){
    	addOperateRecord();
    }else{
    	$("#operate_record_ul li").first().click();
    }
    var validator = $("#operateInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			var url = $(form).attr("action");
			layer.load(1);
			/*if(url){
				return true;
			}*/
			return true;
		}
	});
	
}


function addOperateRecord(){

	var basePath = $("#basePath").val();
	var operationRecordCountOjb = $("#operationCount");
	var count = parseInt(operationRecordCountOjb.val());
	
    var addli=$("#template_operate_ui li:eq(0)").clone();
    addli.text("记录"+(count+1));
    addli.attr("data-index",count);
    $("#operate_record_ul").append(addli);
    addli.addClass("selected").siblings().removeClass("selected");
    addli.click(function(){
    	$(this).addClass("selected").siblings().removeClass("selected");
        $("#operation_record"+$(this).attr("data-index")).show().siblings().hide();
    });
    var add_content=$("#templateOperateRecord").clone();
    add_content.attr("id","operation_record"+count);
    
    $.each(add_content.find("input[name^='operateRecords[0].']"), function(i,item) {    
    	var inputName = $(item).attr("name");
    	var changeName = inputName.replace("operateRecords[0].","operateRecords["+count+"].");
        $(item).attr("name",changeName);
    });
    
    $.each(add_content.find("select[name^='operateRecords[0].']"), function(i,item) {    
    	var selectName = $(item).attr("name");
    	var changeName = selectName.replace("operateRecords[0].","operateRecords["+count+"].");
        $(item).attr("name",changeName);
        $(item).val("");
    });
    
    $.each(add_content.find("select[name$='.incisionLevelCode'],[name$='.cicatrizeTypeCode'],[name$='.opsLevelCode']" +
    		",[name$='.anaesthesiaLevelCode'],[name$='.anaesthesiaTypeCode']"), function(i,item) {    
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
    
    $.each(add_content.find("input[name$='.operateDescShow']"), function(i,item) {    
    	$(this).autocomplete({
    		source: function( request, response ) {
    			var eventObj = $(this.element);
    			var codeObjName = eventObj.attr("name").replace("DescShow", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val("");
				
				var nameObjName = eventObj.attr("name").replace("DescShow", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val("");
				
    			$.ajax({
    				url: basePath + "base_info/ajax_operate_by_query_name",
    				dataType: "json",
    				data:{
    					queryName: request.term
    				},
    				success: function( data ) {
    				response( $.map( data, function( item ) {
    					return {
    						label:item.uniq_code+'['+item.label+']',
							uniq_code:item.uniq_code,
							uniq_name:item.label
    					}
    				}));
    			}
    		});
    		},
    		minLength: 2,
    		select: function( event, ui ) {
				var eventObj = $(this);
				var item = ui.item;
				var codeObjName = eventObj.attr("name").replace("DescShow", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val(item.uniq_code);
				
				var nameObjName = eventObj.attr("name").replace("DescShow", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val(item.uniq_name);
			},search: function( event, ui ) {
				var eventObj = $(this);
				var codeObjName = eventObj.attr("name").replace("DescShow", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val("");
				
				var nameObjName = eventObj.attr("name").replace("DescShow", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val("");
				
			}
    	});
    });
    
    $.each(add_content.find("input[name$='.medicalWorkerName']"), function(i,item) {
    	$(this).autocomplete({
    		source: function( request, response ) {
    			var eventObj = $(this.element);
    			var basePath = $("#basePath").val();
    			var objCode = eventObj.attr("name").replace("Name", "Code");
    			eventObj.next("input[name='"+objCode+"']").val("");
    			$.ajax({
    				url: basePath + "base_info/ajax_medical_worker_by_query_name",
    				dataType: "json",
    				data:{
    					queryName: request.term
    				},
    				success: function( data ) {
    				response( $.map( data, function( item ) {
    					return {
    						label:item.label,
    						uniq_code:item.uniq_code
    					}
    				}));
    			}
    		});
    		},
    		minLength: 2,
    		select: function( event, ui ) {
    			var eventObj = $(this);
    			var item = ui.item;
    			var objCode = eventObj.attr("name").replace("Name", "Code");
    			eventObj.next("input[name='"+objCode+"']").val(item.uniq_code);
    		},search: function( event, ui ) {
    			var eventObj = $(this);
    			var objCode = eventObj.attr("name").replace("Name", "Code");
    			eventObj.next("input[name='"+objCode+"']").val("");
    		}
    	});
    });
    
    $("#operation_records").append(add_content);
    add_content.show().siblings().hide();
    operationRecordCountOjb.val(count+1);
    return add_content;
}


function addOperateRecordByRecord(operateRecord){
	var add_content = addOperateRecord();
	
	
	add_content.find("input[name$='.operateStartDate']").val(operateRecord.operateStartDate);
	add_content.find("input[name$='.operateEndDate']").val(operateRecord.operateEndDate);
	add_content.find("input[name$='.operateOriginalDesc']").val(operateRecord.operateOriginalDesc);
	add_content.find("input[name$='.operateOriginalCode']").val(operateRecord.operateOriginalCode);
	if(operateRecord.operateCode){
		add_content.find("input[name$='.operateDescShow']")
		.val(operateRecord.operateCode+'['+operateRecord.operateName+']');
	}
	add_content.find("input[name$='.operateCode']").val(operateRecord.operateCode);
	add_content.find("input[name$='.operateName']").val(operateRecord.operateName);
	
	if(operateRecord.incisionLevelCode){
		
		var selectOption = add_content.find("select[name$='.incisionLevelCode']").find("option[value='"+operateRecord.incisionLevelCode+"']")
		if(selectOption.length > 0){
			selectOption.attr("selected",true);
		}else{
			add_content.find("select[name$='.incisionLevelCode']").append("<option selected='selected' value='"+operateRecord.incisionLevelCode+"'>"+operateRecord.incisionLevelName+"</option>");
		}
	}
	add_content.find("input[name$='.incisionLevelName']").val(operateRecord.incisionLevelName);
	
	if(operateRecord.cicatrizeTypeCode){
		
		var selectOption = add_content.find("select[name$='.cicatrizeTypeCode']").find("option[value='"+operateRecord.cicatrizeTypeCode+"']")
		if(selectOption.length > 0){
			selectOption.attr("selected",true);
		}else{
			add_content.find("select[name$='.cicatrizeTypeCode']").append("<option selected='selected' value='"+operateRecord.cicatrizeTypeCode+"'>"+operateRecord.cicatrizeTypeName+"</option>");
		}
	}
	add_content.find("input[name$='.cicatrizeTypeName']").val(operateRecord.cicatrizeTypeName);
	
	if(operateRecord.opsLevelCode){
		
		var selectOption = add_content.find("select[name$='.opsLevelCode']").find("option[value='"+operateRecord.opsLevelCode+"']")
		if(selectOption.length > 0){
			selectOption.attr("selected",true);
		}else{
			add_content.find("select[name$='.opsLevelCode']").append("<option selected='selected' value='"+operateRecord.opsLevelCode+"'>"+operateRecord.opsLevelName+"</option>");
		}
	}
	add_content.find("input[name$='.opsLevelName']").val(operateRecord.opsLevelName);
	
	if(operateRecord.anaesthesiaLevelCode){
		
		var selectOption = add_content.find("select[name$='.anaesthesiaLevelCode']").find("option[value='"+operateRecord.anaesthesiaLevelCode+"']")
		if(selectOption.length > 0){
			selectOption.attr("selected",true);
		}else{
			add_content.find("select[name$='.anaesthesiaLevelCode']").append("<option selected='selected' value='"+operateRecord.anaesthesiaLevelCode+"'>"+operateRecord.anaesthesiaLevelName+"</option>");
		}
	}
	add_content.find("input[name$='.anaesthesiaLevelName']").val(operateRecord.anaesthesiaLevelName);
	
	if(operateRecord.anaesthesiaTypeCode){
		
		var selectOption = add_content.find("select[name$='.anaesthesiaTypeCode']").find("option[value='"+operateRecord.anaesthesiaTypeCode+"']")
		if(selectOption.length > 0){
			selectOption.attr("selected",true);
		}else{
			add_content.find("select[name$='.anaesthesiaTypeCode']").append("<option selected='selected' value='"+operateRecord.anaesthesiaTypeCode+"'>"+operateRecord.anaesthesiaTypeName+"</option>");
		}
	}
	add_content.find("input[name$='.anaesthesiaTypeName']").val(operateRecord.anaesthesiaTypeName);

	var operateWorkers = operateRecord.operateWorkers
	for(var index in operateWorkers){
		var operateWorker = operateWorkers[index];
		var professionTitleNameObj = add_content.find("input[value='"+operateWorker.professionTitleName+"']");
		professionTitleNameObj.nextAll("input[name$='medicalWorkerName']").val(operateWorker.medicalWorkerName);
		professionTitleNameObj.nextAll("input[name$='medicalWorkerCode']").val(operateWorker.medicalWorkerCode);
		
    } 
	add_content.find("input[name$='.operateSortIndex']").val(operateRecord.operateSortIndex);
	add_content.find("input[name$='.operatingLevel']").val(operateRecord.operatingLevel);

	
}
function deleteOperateRecord(){
    /*选项卡删除最后一项*/
	var delete_option = $("#operate_record_ul li[class='selected']");
	var delete_index = delete_option.attr("data-index");
	var operatingLevels = $("#operation_record"+delete_index+" input[name$='.operatingLevel']");
	if(operatingLevels.length > 0){
		var operatingLevel = operatingLevels.first();
		if("1" == operatingLevel.val()){
			alert("此手术不允许删除！！！");
		}else{
			delete_option.remove();
			
		    $("#operate_record_ul li:last").addClass("selected").siblings().removeClass("selected");
		    /*内容删除最后一项*/
		    $("#operation_record"+delete_index).remove();
		    $("#operation_records div:last").show().siblings().hide();
		}
		
		
	}else{
		
		
		delete_option.remove();
		
	    $("#operate_record_ul li:last").addClass("selected").siblings().removeClass("selected");
	    /*内容删除最后一项*/
	    $("#operation_record"+delete_index).remove();
	    $("#operation_records div:last").show().siblings().hide();
	}
	
}