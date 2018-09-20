
function initPage(baseInfoJson,infusionBloodInfoJson){
	var basePath = $("#basePath").val();
	
	
	$("select:not([data-address])").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
		
	});
	var bloodTypes =  baseInfoJson['bloodTypes'];
	$("select[name$='infusionBloodTypeCode']").each(function(){
		var options = "";
		for(var index in bloodTypes){
			var bloodType = bloodTypes[index];
	    	options+="<option value='"+bloodType.uniq_code+"'>"+bloodType.label+"</option>"
	    }
		$(this).append(options);
	});
	
	
	var validator = $("#infusionBloodInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			layer.load(1);
			return true;
		},
		rules:{
			
			infusionBloodTimes: {
            	range:[1,500]
            },
            infusionBloodReactTimes: {
            	range:[1,500]
            }
		}
	});
	var infusionBloodRecords = infusionBloodInfoJson['infusionBloodRecords'];
	if(infusionBloodRecords == undefined || infusionBloodRecords.length ==0){
		addInfusionBloodRecord();
	}else{
		for(var index in infusionBloodRecords){
			var infusionBloodRecord = infusionBloodRecords[index];
	    	addInfusionBloodRecordByRecord(infusionBloodRecord);
	    }
	}
}

function addInfusionBloodRecord(){
	var basePath = $("#basePath").val();
	var infusionBloodCountOjb = $("#infusionBloodRecordCount");
	var count = parseInt(infusionBloodCountOjb.val());
	var add_content=$("#templateInfusionBloodTr").clone();
	add_content.removeAttr("id");
	
	add_content.find("input[name^='infusionBloodRecords[0].']").each(function(){
		var eventObj = $(this);
		eventObj.attr("name",eventObj.attr("name").replace("infusionBloodRecords[0]", "infusionBloodRecords["+count+"]"));
   		
	});
	
	add_content.find("select[name^='infusionBloodRecords[0].']").each(function(){
		var eventObj = $(this);
		eventObj.attr("name",eventObj.attr("name").replace("infusionBloodRecords[0]", "infusionBloodRecords["+count+"]"));
	});
	
	add_content.find("select[name$='.infusionBloodTypeCode']").each(function(){
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
	
	add_content.find("input[name$='.isBleedingReactionCode']").each(function(){
		$(this).click(function(){
			 
			 $(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val($(this).attr("title"));
		 });
		 $(this).dblclick(function(){
			$(this).removeAttr("checked");
			var title = $('input:radio[name="isBleedingReactionCode"]:checked').attr("title");
			$(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(title);
		});
	});
	
	
	
	
	
	$("#infusionBloodRecords tbody").append(add_content);
	infusionBloodCountOjb.val(count +1);
	return add_content;
}

function deleteInfusionBloodRecord(obj){
	$(obj).parent().parent().remove();
}

function addInfusionBloodRecordByRecord(infusionBloodRecord){
	var add_content = addInfusionBloodRecord();
	if(infusionBloodRecord.infusionBloodTypeCode){
		
		var selectOption = add_content.find("select[name$='.infusionBloodTypeCode']").find("option[value='"+infusionBloodRecord.infusionBloodTypeCode+"']")
		if(selectOption.length > 0){
			selectOption.attr("selected",true);
		}else{
			add_content.find("select[name$='.infusionBloodTypeCode']").append("<option selected='selected' value='"+infusionBloodRecord.infusionBloodTypeCode+"'>"+infusionBloodRecord.infusionBloodTypeName+"</option>");
		}
	}
	add_content.find("input[name$='.infusionBloodTypeName']").val(infusionBloodRecord.infusionBloodTypeName);
	if(infusionBloodRecord.isBleedingReactionCode){
		add_content.find("input[name$='.isBleedingReactionCode'][value='"+infusionBloodRecord.isBleedingReactionCode+"']").attr("checked",true);
	}
	add_content.find("input[name$='.isBleedingReactionName']").val(infusionBloodRecord.isBleedingReactionName);
	add_content.find("input[name$='.bleedingDateTime']").val(infusionBloodRecord.bleedingDateTime);
	var infusionBloodElements = infusionBloodRecord.infusionBloodElements
	for(var index in infusionBloodElements){
		var infusionBloodElement = infusionBloodElements[index];
		add_content.find("input[name$='.infusionBloodElements["+index+"].bloodElementVolume']").val(infusionBloodElement.bloodElementVolume);
		add_content.find("input[name$='.infusionBloodElements["+index+"].bloodElementCode']").val(infusionBloodElement.bloodElementCode);
		add_content.find("input[name$='.infusionBloodElements["+index+"].bloodElementName']").val(infusionBloodElement.bloodElementName);
		add_content.find("input[name$='.infusionBloodElements["+index+"].bloodElementUnitCode']").val(infusionBloodElement.bloodElementUnitCode);
		add_content.find("input[name$='.infusionBloodElements["+index+"].bloodElementUnitName']").val(infusionBloodElement.bloodElementUnitName);
    }
	add_content.find("input[name$='.operatorCode']").val(infusionBloodRecord.operatorCode);
	add_content.find("input[name$='.operatorName']").val(infusionBloodRecord.operatorName);
}