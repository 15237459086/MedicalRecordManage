function initPage(baseInfoJson,diseaseDiagInfoJson){
	var basePath = $("#basePath").val();
	var diagTypes = baseInfoJson['diagTypes'];//诊断类型
	
	var diseaseStates = baseInfoJson['inHospitalizationDiseaseStates'];//治疗结果
	
	var treatmentResults = baseInfoJson['treatmentResults'];//治疗结果
	
	var diseaseDiagRecords = diseaseDiagInfoJson['diseaseDiagRecords'];//诊断记录集合
	
	$("select").each(function(){
		$(this).append("<option value=''>--请选择--</option>");
		
	});
	
	$("select[name$='.diagTypeCode']").each(function(){
		var options = "";
		for(var index in diagTypes){
			var diagType = diagTypes[index];
	    	options+="<option value='"+diagType.uniq_code+"'>"+diagType.label+"</option>"
	    } 
       $(this).append(options);
       
	});
	
	$("select[name$='.inHospitalDiseaseStateCode']").each(function(){
		var options = "";
		for(var index in diseaseStates){
			var diseaseState = diseaseStates[index];
	    	options+="<option value='"+diseaseState.uniq_code+"'>"+diseaseState.label+"</option>";
	    } 
       $(this).append(options);
       
	});
	
	$("select[name$='.treatResultCode']").each(function(){
		var options = "";
		for(var index in treatmentResults){
			var treatmentResult = treatmentResults[index];
	    	options+="<option value='"+treatmentResult.uniq_code+"'>"+treatmentResult.label+"</option>";
	    } 
       $(this).append(options);
       
	});
	
	
	$.each($("input[data-type='diseaseDiag']"),function(i,item) {
		$(item).autocomplete({
			source: function( request, response ) {
				var eventObj = $(this.element);
				
				var codeObjName = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val("");
				
				var nameObjName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val("");
				
				$.ajax({
					url: basePath + "base_info/ajax_diseases_by_query_name",
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
			minLength: 1,
			select: function( event, ui ) {
				var eventObj = $(this);
				var item = ui.item;
				var codeObjName = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val(item.uniq_code);
				
				var nameObjName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val(item.uniq_name);
			},search: function( event, ui ) {
				var eventObj = $(this);
				var codeObjName = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val("");
				
				var nameObjName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val("");
				
			}
		});
	});
	
	
	for(var index in diseaseDiagRecords){
		var diseaseDiagRecord = diseaseDiagRecords[index];
		addDiseaseDiagRecordByDiag(diseaseDiagRecord);
    } 
	
	if($("#diseaseDiagTable tbody tr").length == 0){
		addDiseaseDiagRecord();
	}
	
	var validator = $("#diseaseDiagInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			var url = $(form).attr("action");
			layer.load(1);
			/*if(url){
				return true;
			}*/
			return true;
		},
		rules:{
			"mainDiagRecord.diseaseDiagShow": {
				required: true,
				minlength: 2
            }
		}
	});
	
};

function addDiseaseDiagRecord(){
	var basePath = $("#basePath").val();
	var diseaseDiagCountOjb = $("#diseaseDiagCount");
	var count = parseInt(diseaseDiagCountOjb.val());
	var add_content=$("#templateDiseaseDiagTr").clone();
	add_content.removeAttr("id");
	
	 $.each(add_content.find("input[name^='diseaseDiagRecords[0].']"), function(i,item) {    
    	var inputName = $(item).attr("name");
    	var changeName = inputName.replace("diseaseDiagRecords[0].","diseaseDiagRecords["+count+"].");
        $(item).attr("name",changeName);
    });
    
    $.each(add_content.find("select[name^='diseaseDiagRecords[0].']"), function(i,item) {    
    	var selectName = $(item).attr("name");
    	var changeName = selectName.replace("diseaseDiagRecords[0].","diseaseDiagRecords["+count+"].");
        $(item).attr("name",changeName);
        $(item).val("");
    });
	
	
	$.each(add_content.find("select[name$='.diagTypeCode']"),function(i,item) {
		$(item).change(function(){
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
	
	$.each(add_content.find("input[data-type='diseaseDiag']"),function(i,item) {
		$(item).autocomplete({
			source: function( request, response ) {
				var eventObj = $(this.element);
				
				var objCode = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+objCode+"']").val("");
				
				var objName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+objName+"']").val("");
				
				$.ajax({
					url: basePath + "base_info/ajax_diseases_by_query_name",
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
				
				var objCode = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+objCode+"']").val(item.uniq_code);
				
				var objName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+objName+"']").val(item.uniq_name);
				
			},search: function( event, ui ) {
				var eventObj = $(this);
				var objCode = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+objCode+"']").val("");
				
				var objName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+objName+"']").val("");
				
			}
		});
	});
	
	$.each(add_content.find("select[name$='.inHospitalDiseaseStateCode']"),function(i,item) {
		$(item).change(function(){
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
	
	
	$.each(add_content.find("select[name$='.treatResultCode']"),function(i,item) {
		$(item).change(function(){
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
	
	$("#diseaseDiagTable tbody").append(add_content);
	diseaseDiagCountOjb.val(count +1);
}

function addDiseaseDiagRecordByDiag(diseaseDiagRecord){
	var basePath = $("#basePath").val();
	var diseaseDiagCountOjb = $("#diseaseDiagCount");
	var count = parseInt(diseaseDiagCountOjb.val());
	var add_content=$("#templateDiseaseDiagTr").clone();
	add_content.removeAttr("id");
	
	$.each(add_content.find("input[name^='diseaseDiagRecords[0].']"), function(i,item) {    
    	var inputName = $(item).attr("name");
    	var changeName = inputName.replace("diseaseDiagRecords[0].","diseaseDiagRecords["+count+"].");
        $(item).attr("name",changeName);
    });
    
    $.each(add_content.find("select[name^='diseaseDiagRecords[0].']"), function(i,item) {    
    	var selectName = $(item).attr("name");
    	var changeName = selectName.replace("diseaseDiagRecords[0].","diseaseDiagRecords["+count+"].");
        $(item).attr("name",changeName);
        $(item).val("");
    });
	
    
    $.each(add_content.find("select[name$='.diagTypeCode']"),function(i,item) {
		$(item).change(function(){
	   		var eventObj = $(this);
	   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
	   		
	   		if(eventObj.val()!=""){
	   			var name=eventObj.find("option:selected").text();
	   			eventObj.next("input[name='"+nameObjName+"']").val(name);
	   		}else{
	   			eventObj.next("input[name='"+nameObjName+"']").val("");
	   		}
	   	});
		if(diseaseDiagRecord.diagTypeCode){
			
			var selectOption = $(item).find("option[value='"+diseaseDiagRecord.diagTypeCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				$(item).append("<option selected='selected' value='"+diseaseDiagRecord.diagTypeCode+"'>"+diseaseDiagRecord.diagTypeName+"</option>");
			}
			
		}
		;
		$(item).next("input[name='"+$(item).attr("name").replace("Code", "Name")+"']").val(diseaseDiagRecord.diagTypeName);
	});
    add_content.find("input[name$='.diseaseDiagOriginalDesc']").val(diseaseDiagRecord.diseaseDiagOriginalDesc);
	add_content.find("input[name='.diseaseDiagOriginalCode']").val(diseaseDiagRecord.diseaseDiagOriginalCode);
	
	if(diseaseDiagRecord.diseaseDiagCode){
		add_content.find("input[name$='.diseaseDiagShow']")
		.val(diseaseDiagRecord.diseaseDiagCode+'['+diseaseDiagRecord.diseaseDiagName+']');
		
	}
	
	add_content.find("input[name$='.diseaseDiagCode']").val(diseaseDiagRecord.diseaseDiagCode);
	add_content.find("input[name$='.diseaseDiagName']").val(diseaseDiagRecord.diseaseDiagName);
	
	$.each(add_content.find("input[data-type='diseaseDiag']"),function(i,item) {
		$(item).autocomplete({
			source: function( request, response ) {
				var eventObj = $(this.element);
				
				var codeObjName = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val("");
				
				var nameObjName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val("");
				
				$.ajax({
					url: basePath + "base_info/ajax_diseases_by_query_name",
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
			minLength: 1,
			select: function( event, ui ) {
				var eventObj = $(this);
				var item = ui.item;
				var codeObjName = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val(item.uniq_code);
				
				var nameObjName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val(item.uniq_name);
			},search: function( event, ui ) {
				var eventObj = $(this);
				var codeObjName = eventObj.attr("name").replace("Show", "Code");
				eventObj.nextAll("input[name='"+codeObjName+"']").val("");
				
				var nameObjName = eventObj.attr("name").replace("Show", "Name");
				eventObj.nextAll("input[name='"+nameObjName+"']").val("");
				
			}
		});
	});
	
	$.each(add_content.find("select[name$='.inHospitalDiseaseStateCode']"),function(i,item) {
		$(item).change(function(){
	   		var eventObj = $(this);
	   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
	   		
	   		if(eventObj.val()!=""){
	   			var name=eventObj.find("option:selected").text();
	   			eventObj.next("input[name='"+nameObjName+"']").val(name);
	   		}else{
	   			eventObj.next("input[name='"+nameObjName+"']").val("");
	   		}
	   	});
		if(diseaseDiagRecord.inHospitalDiseaseStateCode){
			
			var selectOption = $(item).find("option[value='"+diseaseDiagRecord.inHospitalDiseaseStateCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				$(item).append("<option selected='selected' value='"+diseaseDiagRecord.inHospitalDiseaseStateCode+"'>"+diseaseDiagRecord.inHospitalDiseaseStateName+"</option>");
			}
			
		}
		;
		$(item).next("input[name='"+$(item).attr("name").replace("Code", "Name")+"']").val(diseaseDiagRecord.inHospitalDiseaseStateName);
	});
	
	$.each(add_content.find("select[name$='.treatResultCode']"),function(i,item) {
		$(item).change(function(){
	   		var eventObj = $(this);
	   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
	   		
	   		if(eventObj.val()!=""){
	   			var name=eventObj.find("option:selected").text();
	   			eventObj.next("input[name='"+nameObjName+"']").val(name);
	   		}else{
	   			eventObj.next("input[name='"+nameObjName+"']").val("");
	   		}
	   	});
		if(diseaseDiagRecord.treatResultCode){
			
			var selectOption = $(item).find("option[value='"+diseaseDiagRecord.treatResultCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				$(item).append("<option selected='selected' value='"+diseaseDiagRecord.treatResultCode+"'>"+diseaseDiagRecord.treatResultName+"</option>");
			}
			
		}
		;
		$(item).next("input[name='"+$(item).attr("name").replace("Code", "Name")+"']").val(diseaseDiagRecord.treatResultName);
	});
	add_content.find("input[name$='.confirmedDateTime']").val(diseaseDiagRecord.confirmedDateTime);
	add_content.find("input[name$='.treatDayNumber']").val(diseaseDiagRecord.treatDayNumber);
	add_content.find("input[name$='.diagSortIndex']").val(diseaseDiagRecord.diagSortIndex);
	
	$("#diseaseDiagTable tbody").append(add_content);
	diseaseDiagCountOjb.val(count +1);
	
}
function deleteDiseaseDiagRecord(obj){
	$(obj).parent().parent().remove();
}