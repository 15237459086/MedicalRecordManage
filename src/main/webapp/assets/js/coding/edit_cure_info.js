function initPage(baseInfoJson,cureInfo){
	var basePath = $("#basePath").val();
	$("select").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
	});
	var bloodTypes = baseInfoJson['bloodTypes'];//ABO血型
	
	
	$("select[name='bloodTypeCode']").each(function(){
		var options = "";
		for(var index in bloodTypes){
			var bloodType = bloodTypes[index];
	    	options+="<option value='"+bloodType.uniq_code+"'>"+bloodType.label+"</option>"
	    } 
       $(this).append(options);
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
       
       if(cureInfo.bloodTypeCode){
    		
	   		var selectOption = $(this).find("option[value='"+cureInfo.bloodTypeCode+"']")
	   		if(selectOption.length > 0){
	   			selectOption.attr("selected",true);
	   		}else{
	   			$(this).append("<option selected='selected' value='"+cureInfo.bloodTypeCode+"'>"+cureInfo.bloodTypeName+"</option>");
	   		}
	   		
	   }
      $(this).next("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(cureInfo.bloodTypeName);
	   
	});
	
	var rhBloodTypes = baseInfoJson['rhBloodTypes'];//RH血型
	
	$("select[name='rhBloodTypeCode']").each(function(){
		var options = "";
		for(var index in rhBloodTypes){
			var rhBloodType = rhBloodTypes[index];
	    	options+="<option value='"+rhBloodType.uniq_code+"'>"+rhBloodType.label+"</option>"
	    } 
       $(this).append(options);
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
       
       if(cureInfo.rhBloodTypeCode){
     		
	   		var selectOption = $(this).find("option[value='"+cureInfo.rhBloodTypeCode+"']")
	   		if(selectOption.length > 0){
	   			selectOption.attr("selected",true);
	   		}else{
	   			$(this).append("<option selected='selected' value='"+cureInfo.rhBloodTypeCode+"'>"+cureInfo.rhBloodTypeName+"</option>");
	   		}
	   		
	   }
       $(this).next("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(cureInfo.rhBloodTypeName);
	   
	});
	
	$("input[name='followUpClinicLimitCode']").each(function(){
		 $(this).click(function(){
			 
			 $(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val($(this).attr("title"));
		 });
		 $(this).dblclick(function(){
			$(this).removeAttr("checked");
			var title = $('input:radio[name="followUpClinicLimitCode"]:checked').attr("title");
			$(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(title);
		});
	});
	
	if(cureInfo.followUpClinicLimitCode){
		$("input[name='followUpClinicLimitCode'][value='"+cureInfo.followUpClinicLimitCode+"']").attr("checked",true);
	}
	$("input[name='followUpClinicLimitName']").val(cureInfo.followUpClinicLimitName);
	
	
	$("input[name='isFirstCaseCode']").each(function(){
		 $(this).click(function(){
			 
			 $(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val($(this).attr("title"));
		 });
		 $(this).dblclick(function(){
			$(this).removeAttr("checked");
			var title = $('input:radio[name="isFirstCaseCode"]:checked').attr("title");
			$(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(title);
		});
	});
	
	if(cureInfo.isFirstCaseCode){
		$("input[name='isFirstCaseCode'][value='"+cureInfo.isFirstCaseCode+"']").attr("checked",true);
	}
	$("input[name='isFirstCaseName']").val(cureInfo.isFirstCaseName);
	
	$("input[name='isTeachingCaseCode']").each(function(){
		 $(this).click(function(){
			 
			 $(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val($(this).attr("title"));
		 });
		 $(this).dblclick(function(){
			$(this).removeAttr("checked");
			var title = $('input:radio[name="isTeachingCaseCode"]:checked').attr("title");
			$(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(title);
		});
	});
	
	if(cureInfo.isTeachingCaseCode){
		$("input[name='isTeachingCaseCode'][value='"+cureInfo.isTeachingCaseCode+"']").attr("checked",true);
	}
	$("input[name='isTeachingCaseName']").val(cureInfo.isTeachingCaseName);
	
	
	
	var cureWorkers = cureInfo['cureWorkers'];//治疗医师集合
	console.log(cureInfo);
	
	
	
	$("select[name='rehospitalAimOf31Code']").each(function(){
		var options = "";
		options+="<option value='0'>是</option>";
		options+="<option value='1'>否</option>";
		$(this).append(options);
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
		
		if(cureInfo.rehospitalAimOf31Code){
     		
	   		var selectOption = $(this).find("option[value='"+cureInfo.rehospitalAimOf31Code+"']")
	   		if(selectOption.length > 0){
	   			selectOption.attr("selected",true);
	   		}else{
	   			$(this).append("<option selected='selected' value='"+cureInfo.rehospitalAimOf31Code+"'>"+cureInfo.rehospitalAimOf31Name+"</option>");
	   		}
	   		
	   }
       $(this).next("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(cureInfo.rehospitalAimOf31Name);
	   
       
	});
	
	$("select[name='malignantTumorFirstCureTypeCode']").each(function(){
		var options = "";
		options+="<option value='1'>手术</option>";
		options+="<option value='2'>化疗</option>";
		options+="<option value='3'>放疗</option>";
		
		options+="<option value='5'>介入</option>";
		options+="<option value='6'>内分泌</option>";
		options+="<option value='7'>重要</option>";
		$(this).append(options);
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
		
		if(cureInfo.malignantTumorFirstCureTypeCode){
     		
	   		var selectOption = $(this).find("option[value='"+cureInfo.malignantTumorFirstCureTypeCode+"']")
	   		if(selectOption.length > 0){
	   			selectOption.attr("selected",true);
	   		}else{
	   			$(this).append("<option selected='selected' value='"+cureInfo.malignantTumorFirstCureTypeCode+"'>"+cureInfo.malignantTumorFirstCureTypeName+"</option>");
	   		}
	   		
	   }
       $(this).next("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(cureInfo.malignantTumorFirstCureTypeName);
	   
       
	});
	
	
	$("select[name='malignantTumorHighestDiagBasisCode']").each(function(){
		var options = "";
		options+="<option value='0'>仅有死亡证明书</option>";
		options+="<option value='1'>临床</option>";
		options+="<option value='2'>X光，CT</option>";
		options+="<option value='3'>手术</option>";
		options+="<option value='4'>生化</option>";
		options+="<option value='5'>细胞学</option>";
		options+="<option value='6'>病理（维发）</option>";
		options+="<option value='7'>病理（原发）</option>";
		$(this).append(options);
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
       
	if(cureInfo.malignantTumorHighestDiagBasisCode){
     		
	   		var selectOption = $(this).find("option[value='"+cureInfo.malignantTumorHighestDiagBasisCode+"']")
	   		if(selectOption.length > 0){
	   			selectOption.attr("selected",true);
	   		}else{
	   			$(this).append("<option selected='selected' value='"+cureInfo.malignantTumorHighestDiagBasisCode+"'>"+cureInfo.malignantTumorHighestDiagBasisName+"</option>");
	   		}
	   		
	   }
       $(this).next("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(cureInfo.malignantTumorHighestDiagBasisName);
	   
		
	});
	

	var diagAccordTypes = baseInfoJson['diagAccordTypes'];//诊断对比符合类型
	
	var diagAccordRecords = cureInfo['diagAccordRecords'];//诊断对比符合记录集合
	
	$("select[data-type='diagAccord']").each(function(){
		var options = "";
		for(var index in diagAccordTypes){
			var diagAccordType = diagAccordTypes[index];
	    	options+="<option value='"+diagAccordType.uniq_code+"'>"+diagAccordType.label+"</option>"
	    } 
       $(this).append(options);
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
	for(var index in diagAccordRecords){
		var diagAccordRecord = diagAccordRecords[index];
		var diagCompareNameObj = $("input[value='"+diagAccordRecord.diagCompareName+"']");
		diagCompareNameObj.nextAll("select[data-type='diagAccord']").each(function(){
			if(diagAccordRecord.diagAccordCode){
	     		
		   		var selectOption = $(this).find("option[value='"+diagAccordRecord.diagAccordCode+"']")
		   		if(selectOption.length > 0){
		   			selectOption.attr("selected",true);
		   		}else{
		   			$(this).append("<option selected='selected' value='"+diagAccordRecord.diagAccordCode+"'>"+diagAccordRecord.diagAccordName+"</option>");
		   		}
		   		
		   }
			$(this).next("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(diagAccordRecord.diagAccordName);
		});
    } 
	
	$("select[data-type='hivCheckResult']").each(function(){
		var options = "";
		options+="<option value='0'>未做</option>";
		options+="<option value='1'>阴性</option>";
		options+="<option value='2'>阳性</option>";
		$(this).append(options);
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
	
	var hbsAgeResultSelect = $("select[name='hbsAgeResultCode']");
	if(cureInfo.hbsAgeResultCode){
		
   		var selectOption = hbsAgeResultSelect.find("option[value='"+cureInfo.hbsAgeResultCode+"']")
   		if(selectOption.length > 0){
   			selectOption.attr("selected",true);
   		}else{
   			hbsAgeResultSelect.append("<option selected='selected' value='"+cureInfo.hbsAgeResultCode+"'>"+cureInfo.hbsAgeResultName+"</option>");
   		}
   		
	}
	hbsAgeResultSelect.next("input[name='"+hbsAgeResultSelect.attr("name").replace("Code", "Name")+"']").val(cureInfo.hbsAgeResultName);
 	
	var hcvAbResultSelect = $("select[name='hcvAbResultCode']");
	if(cureInfo.hcvAbResultCode){
		
   		var selectOption = hcvAbResultSelect.find("option[value='"+cureInfo.hcvAbResultCode+"']")
   		if(selectOption.length > 0){
   			selectOption.attr("selected",true);
   		}else{
   			hcvAbResultSelect.append("<option selected='selected' value='"+cureInfo.hcvAbResultCode+"'>"+cureInfo.hcvAbResultName+"</option>");
   		}
   		
	}
	hcvAbResultSelect.next("input[name='"+hcvAbResultSelect.attr("name").replace("Code", "Name")+"']").val(cureInfo.hcvAbResultName);
 	

	var hivAbResultSelect = $("select[name='hivAbResultCode']");
	if(cureInfo.hivAbResultCode){
		
   		var selectOption = hivAbResultSelect.find("option[value='"+cureInfo.hivAbResultCode+"']")
   		if(selectOption.length > 0){
   			selectOption.attr("selected",true);
   		}else{
   			hivAbResultSelect.append("<option selected='selected' value='"+cureInfo.hivAbResultCode+"'>"+cureInfo.hivAbResultName+"</option>");
   		}
   		
	}
	hivAbResultSelect.next("input[name='"+hivAbResultSelect.attr("name").replace("Code", "Name")+"']").val(cureInfo.hivAbResultName);
 	
	
	for(var index in cureWorkers){
		var cureWorker = cureWorkers[index];
		var professionTitleNameObj = $("input[value='"+cureWorker.professionTitleName+"']");
		professionTitleNameObj.nextAll("input[name$='.medicalWorkerName']").each(function(){
			$(this).val(cureWorker.medicalWorkerName);
			$(this).next("input[name='"+$(this).attr("name").replace("Name", "Code")+"']").val(cureWorker.medicalWorkerCode);
			
		});
    } 
	
	$("input[name$='.medicalWorkerName']").autocomplete({
		source: function( request, response ) {
			var eventObj = $(this.element);
			
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
		minLength: 1,
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
	
	
	var validator = $("#cureInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			layer.load(1);
			return true;
		},
		rules:{
			bloodTypeCode: {
				required: true
            },
            rhBloodTypeCode: {
            	required: true
            },
            babyBirthWeight: {
            	number:true,
            	range:[1,20000]
            },
            babyInHospitalWeight: {
            	number:true,
            	range:[1,20000]
            },
            rescueNumber: {
            	digits:true,
            	range:[1,200]
            },
            rescueSucceedNumber: {
            	digits:true,
            	range:[1,200]
            },
            inConsultationNumber: {
            	digits:true,
            	range:[1,200]
            },
            outConsultationNumber: {
            	digits:true,
            	range:[1,200]
            },
            infusionTimes: {
            	digits:true,
            	range:[1,200]
            },
            infusionReactTimes: {
            	digits:true,
            	range:[1,200]
            },
            followUpClinicDayNumber: {
            	number:true,
            	range:[1,500]
            },dayOfBeforeInHospitalComa: {
            	digits:true,
            	range:[1,200]
            },hourOfBeforeInHospitalComa: {
            	digits:true,
            	range:[1,200]
            },minuteOfBeforeInHospitalComa: {
            	digits:true,
            	range:[1,200]
            }
            ,dayOfAfterInHospitalComa: {
            	digits:true,
            	range:[1,200]
            },hourOfAfterInHospitalComa: {
            	digits:true,
            	range:[1,200]
            },minuteOfAfterInHospitalComa: {
            	digits:true,
            	range:[1,200]
            }/*,
            medicalRecordQualityName: {
				required: true
            },
            qualityControlDateTime: {
				required: true
            },
            finishCatalogDateTime: {
				required: true
            }*/
		}
	});
	
}




function validateDate(){
	//科主任不能为空
	var headDepartment=$("input[name='cureWorkers[0].medicalWorkerName']").val();
	if(!headDepartment){
		$("input[name='cureWorkers[0].medicalWorkerName']").css({"border":"1px solid red"}).attr("data-content","科主任不能为空!").popover({placement:'right'});
		redIframe();
	}
	
	//主治医师为空
	var physician = $("input[name='cureWorkers[3].medicalWorkerName']").val();
	if(!physician){
		$("input[name='cureWorkers[3].medicalWorkerName']").css({"border":"1px solid red"}).attr("data-content","主治医师不能为空!").popover({placement:'right'});
		redIframe();
	}
	//住院医师为空
	var chiefPhysician =$("input[name='cureWorkers[10].medicalWorkerName']").val();
	if(!chiefPhysician){
		$("input[name='cureWorkers[10].medicalWorkerName']").css({"border":"1px solid red"}).attr("data-content","住院医师不能为空!").popover({placement:'right'});
		redIframe();
	}
	
	//质控医师为空
	var qualityPhysician =$("input[name='cureWorkers[8].medicalWorkerName']").val();
	if(!qualityPhysician){
		$("input[name='cureWorkers[8].medicalWorkerName']").css({"border":"1px solid red"}).attr("data-content","质控医师不能为空!").popover({placement:'right'});
		redIframe();
	}
	
	//质控护士为空
	var qualityNurse =$("input[name='cureWorkers[9].medicalWorkerName']").val();
	if(!qualityNurse){
		$("input[name='cureWorkers[9].medicalWorkerName']").css({"border":"1px solid red"}).attr("data-content","质控护士不能为空!").popover({placement:'right'});
		redIframe();
	}
	
	//HBS选择为空,要选择未做、阴性或阳性之一
	var HBS =$("select[name='hbsAgeResultCode']").val();
	if(!HBS){
		$("select[name='hbsAgeResultCode']").css({"border-color":"red"}).attr("data-content","患者HBSAg选择为空,要选择未做、阴性或阳性之一!").popover({placement:'right'});;
		redIframe();
	}
	
	//HIV
	var HIV =$("select[name='hivAbResultCode']").val();
	if(!HIV){
		$("select[name='hivAbResultCode']").css({"border-color":"red"}).css({"border-color":"red"}).attr("data-content","患者HIV-Ab选择为空,要选择未做、阴性或阳性之一!").popover({placement:'top'});
		redIframe();
	}
	
	//HBS
	var HCV =$("select[name='hcvAbResultCode']").val();
	if(!HCV){
		$("select[name='hcvAbResultCode']").css({"border-color":"red"}).attr("data-content","患者HCV-Ab选择为空,要选择未做、阴性或阳性之一!").popover({placement:'right'});;
		redIframe();
	}
	
	//门诊出院诊断
	var hospitalOutpatientDiagnosis =$("select[name='diagAccordRecords[0].diagAccordCode']").val();
	if(!hospitalOutpatientDiagnosis){
		$("select[name='diagAccordRecords[0].diagAccordCode']").css({"border-color":"red"}).attr("data-content","门诊和出院诊断不能为空!").popover({placement:'right'});
		redIframe();
	}
	
	
	
	
	//质控时间为空
	/*var qualityControlDateTime =$("input[name='qualityControlDateTime']").val();
	if(!qualityControlDateTime){
		$("input[name='qualityControlDateTime']").css({"border":"1px solid red"});
	}*/
	
/*	//编码员
	var coder =$("input[name='cureWorkers[7].medicalWorkerName']").val();
	if(!coder){
		$("input[name='cureWorkers[7].medicalWorkerName']").css({"border":"1px solid red"});
	}*/
	
	//病案质量未空
	/*var medicalRecordQualityName =$("input[name='medicalRecordQualityName']").val();
	if(!medicalRecordQualityName){
		$("input[name='medicalRecordQualityName']").css({"border":"1px solid red"});
	}*/
}



function redIframe(){
	parent.$(".tc_crue a").css({"color":"red"});

}