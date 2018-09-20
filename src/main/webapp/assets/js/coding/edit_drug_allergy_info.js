
function initPage(baseInfoJson,drugAllergyInfoJson){
	var basePath = $("#basePath").val();
	
	
	$("select:not([data-address])").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
		
	});
	var allergyDrugTypes =  baseInfoJson['allergyDrugTypes'];
	$("select[name$='drugAllergyTypeCode']").each(function(){
		var options = "";
		for(var index in allergyDrugTypes){
			var allergyDrugType = allergyDrugTypes[index];
	    	options+="<option value='"+allergyDrugType.uniq_code+"'>"+allergyDrugType.label+"</option>"
	    }
		$(this).append(options);
	});
	
	
	var validator = $("#drugAllergyInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			layer.load(1);
			return true;
		},
		rules:{
			/*
			infusionBloodTimes: {
            	range:[1,500]
            },
            infusionBloodReactTimes: {
            	range:[1,500]
            }*/
		}
	});
	var drugAllergyRecords = drugAllergyInfoJson['drugAllergyRecords'];
	if(drugAllergyRecords == undefined || drugAllergyRecords.length ==0){
		addDrugAllergyRecord();
	}else{
		for(var index in drugAllergyRecords){
			var drugAllergyRecord = drugAllergyRecords[index];
			addDrugAllergyRecordByRecord(drugAllergyRecord);
	    }
	}
}

function addDrugAllergyRecord(){
	var basePath = $("#basePath").val();
	var drugAllergyRecordCountOjb = $("#drugAllergyRecordCount");
	var count = parseInt(drugAllergyRecordCountOjb.val());
	var add_content=$("#templateDrugAllergyTr").clone();
	add_content.removeAttr("id");
	
	add_content.find("input[name^='drugAllergyRecords[0].']").each(function(){
		var eventObj = $(this);
		eventObj.attr("name",eventObj.attr("name").replace("drugAllergyRecords[0]", "drugAllergyRecords["+count+"]"));
   		
	});
	
	add_content.find("select[name^='drugAllergyRecords[0].']").each(function(){
		var eventObj = $(this);
		eventObj.attr("name",eventObj.attr("name").replace("drugAllergyRecords[0]", "drugAllergyRecords["+count+"]"));
	});
	
	add_content.find("select[name$='.drugAllergyTypeCode']").each(function(){
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
	
	
	
	
	
	$("#drugAllergyRecords tbody").append(add_content);
	drugAllergyRecordCountOjb.val(count +1);
	return add_content;
}

function deleteDrugAllergyRecord(obj){
	$(obj).parent().parent().remove();
}

function addDrugAllergyRecordByRecord(drugAllergyRecord){
	var add_content = addDrugAllergyRecord();
	if(drugAllergyRecord.drugAllergyTypeCode){
		
		var selectOption = add_content.find("select[name$='.drugAllergyTypeCode']").find("option[value='"+drugAllergyRecord.drugAllergyTypeCode+"']")
		if(selectOption.length > 0){
			selectOption.attr("selected",true);
		}else{
			add_content.find("select[name$='.drugAllergyTypeCode']").append("<option selected='selected' value='"+drugAllergyRecord.drugAllergyTypeCode+"'>"+drugAllergyRecord.drugAllergyTypeName+"</option>");
		}
	}
	add_content.find("input[name$='.drugAllergyTypeName']").val(drugAllergyRecord.drugAllergyTypeName);
	
	add_content.find("input[name$='.drugAllergyName']").val(drugAllergyRecord.drugAllergyName);
	
}