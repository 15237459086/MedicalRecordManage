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



function initPage(baseInfoJson,basicInfo){
	var basePath = $("#basePath").val();
	$("select:not([data-address])").each(function(){
		$(this).append("<option value=''>---请选择---</option>");
		
	});
	
	/*$('#nativePlaceRegionalismDiv').distpicker({
		province: basicInfo.nativePlaceRegionalism.provinceName,
		city: basicInfo.nativePlaceRegionalism.cityName,
		district: basicInfo.nativePlaceRegionalism.countyName
	});*/
	var medicalPayMentTypesData = baseInfoJson['medicalPayMentTypes'];//付费方式
	$("select[name='medicalPayTypeCode']").each(function(){
		var options = "";
		for(var index in medicalPayMentTypesData){
			var medicalPayMentType = medicalPayMentTypesData[index];
	    	options+="<option value='"+medicalPayMentType.uniq_code+"'>"+medicalPayMentType.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.medicalPayTypeCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.medicalPayTypeCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.medicalPayTypeCode+"'>"+basicInfo.medicalPayTypeName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.medicalPayTypeName);
	});
	
	var idDocumentTypes=baseInfoJson['idDocumentTypes'];//证件类型
	
	//证件类型
	$("select[name='documentTypeCode']").each(function(){
		var options = "";
		for(var index in idDocumentTypes){
			var idDocumentType = idDocumentTypes[index];
	    	options+="<option value='"+idDocumentType.uniq_code+"'>"+idDocumentType.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.documentTypeCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.documentTypeCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.documentTypeCode+"'>"+basicInfo.documentTypeName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.documentTypeName);
	});

	var sexs=baseInfoJson['sexs'];//性别
	$("select[name='sexCode']").each(function(){
		var options = "";
		for(var index in sexs){
			var sex = sexs[index];
	    	options+="<option value='"+sex.uniq_code+"'>"+sex.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.sexCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.sexCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.sexCode+"'>"+basicInfo.sexName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.sexName);
	});
	
	var nationalitys=baseInfoJson['nationalitys'];//国籍
	$("select[name='nationalityCode']").each(function(){
		var options = "";
		for(var index in nationalitys){
			var nationality = nationalitys[index];
	    	options+="<option value='"+nationality.uniq_code+"'>"+nationality.label+"</option>"
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
       
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.nationalityCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.nationalityCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.nationalityCode+"'>"+basicInfo.nationalityName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.nationalityName);
	});
	
	var nations=baseInfoJson['nations'];//民族
	$("select[name='nationCode']").each(function(){
		var options = "";
		for(var index in nations){
			var nation = nations[index];
	    	options+="<option value='"+nation.uniq_code+"'>"+nation.label+"</option>"
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
       
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.nationCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.nationCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.nationCode+"'>"+basicInfo.nationName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.nationName);
	});
	
	var marriages=baseInfoJson['marriages'];//婚姻
	$("select[name='marriageCode']").each(function(){
		var options = "";
		for(var index in marriages){
			var marriage = marriages[index];
	    	options+="<option value='"+marriage.uniq_code+"'>"+marriage.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.marriageCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.marriageCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.marriageCode+"'>"+basicInfo.marriageName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.marriageName);
	});
	
	var professions=baseInfoJson['professions'];//职业
	$("select[name='professionCode']").each(function(){
		var options = "";
		for(var index in professions){
			var profession = professions[index];
	    	options+="<option value='"+profession.uniq_code+"'>"+profession.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.professionCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.professionCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.professionCode+"'>"+basicInfo.professionName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.professionName);
	});
	
	var relativeRelations=baseInfoJson['relativeRelations'];//与病人关系

	$("select[name='linkManRelativeRelationCode']").each(function(){
		var options = "";
		for(var index in relativeRelations){
			var relativeRelation = relativeRelations[index];
	    	options+="<option value='"+relativeRelation.uniq_code+"'>"+relativeRelation.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.linkManRelativeRelationCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.linkManRelativeRelationCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.linkManRelativeRelationCode+"'>"+basicInfo.linkManRelativeRelationName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.linkManRelativeRelationName);
	});
	
	var inHospitalTypes=baseInfoJson['inHospitalTypes'];//入院途径
	$("select[name='inHospitalTypeCode']").each(function(){
		var options = "";
		for(var index in inHospitalTypes){
			var inHospitalType = inHospitalTypes[index];
	    	options+="<option value='"+inHospitalType.uniq_code+"'>"+inHospitalType.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.inHospitalTypeCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.inHospitalTypeCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.inHospitalTypeCode+"'>"+basicInfo.inHospitalTypeName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.inHospitalTypeName);
	});
	
	var inHospitalStates=baseInfoJson['inHospitalStates'];//入院状况
	$("select[name='inHospitalStateCode']").each(function(){
		var options = "";
		for(var index in inHospitalStates){
			var inHospitalState = inHospitalStates[index];
	    	options+="<option value='"+inHospitalState.uniq_code+"'>"+inHospitalState.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.inHospitalStateCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.inHospitalStateCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.inHospitalStateCode+"'>"+basicInfo.inHospitalStateName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.inHospitalStateName);
	});
	
	var medicalDepts=baseInfoJson['medicalDepts'];//科室
	
	//入院科室
	$("select[name='inDeptCode']").each(function(){
		var options = "";
		for(var index in medicalDepts){
			var medicalDept = medicalDepts[index];
	    	options+="<option value='"+medicalDept.uniq_code+"'>"+medicalDept.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.inDeptCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.inDeptCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.inDeptCode+"'>"+basicInfo.inDeptName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.inDeptName);
	});
	
	//出院科室
	$("select[name='outDeptCode']").each(function(){
		var options = "";
		for(var index in medicalDepts){
			var medicalDept = medicalDepts[index];
	    	options+="<option value='"+medicalDept.uniq_code+"'>"+medicalDept.label+"</option>"
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
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.outDeptCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.outDeptCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.outDeptCode+"'>"+basicInfo.outDeptName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.outDeptName);
	});
	
	
	var outHospitalTypes=baseInfoJson['outHospitalTypes'];//离院方式
	$("select[name='outHospitalTypeCode']").each(function(){
		var options = "";
		for(var index in outHospitalTypes){
			var outHospitalType = outHospitalTypes[index];
	    	options+="<option value='"+outHospitalType.uniq_code+"'>"+outHospitalType.label+"</option>"
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
       
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.outHospitalTypeCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.outHospitalTypeCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.outHospitalTypeCode+"'>"+basicInfo.outHospitalTypeName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.outHospitalTypeName);
	});
	
	
	var rehospitalAims=baseInfoJson['rehospitalAims'];//在住院目的
	
	$("select[name='rehospitalAimCode']").each(function(){
		var options = "";
		for(var index in rehospitalAims){
			var rehospitalAim = rehospitalAims[index];
	    	options+="<option value='"+rehospitalAim.uniq_code+"'>"+rehospitalAim.label+"</option>"
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
       
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.rehospitalAimCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.rehospitalAimCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.rehospitalAimCode+"'>"+basicInfo.rehospitalAimName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.rehospitalAimName);
	});
	
	var hospitalDealthReasons=baseInfoJson['hospitalDealthReasons'];//死亡原因
	
	$("select[name='dealthReasonCode']").each(function(){
		var options = "";
		for(var index in hospitalDealthReasons){
			var hospitalDealthReason = hospitalDealthReasons[index];
	    	options+="<option value='"+hospitalDealthReason.uniq_code+"'>"+hospitalDealthReason.label+"</option>"
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
       
       var eventObj = $(this);
	   var nameObjName = eventObj.attr("name").replace("Code", "Name");
       if(basicInfo.dealthReasonCode){
    	   var selectOption = eventObj.find("option[value='"+basicInfo.dealthReasonCode+"']")
			if(selectOption.length > 0){
				selectOption.attr("selected",true);
			}else{
				eventObj.append("<option selected='selected' value='"+basicInfo.dealthReasonCode+"'>"+basicInfo.dealthReasonName+"</option>");
			}
       }
       eventObj.next("input[name='"+nameObjName+"']").val(basicInfo.dealthReasonName);
	});
	
	$("input[data-type='medicalWorker']" ).autocomplete({
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
			eventObj.val(item.code); 
		},search: function( event, ui ) {
			var eventObj = $(this);
			eventObj.val(""); 
			var objCode = eventObj.attr("name").replace("Name", "Code");
			eventObj.next("input[name='"+objCode+"']").val("");
		}
	});
	
	var validator = $("#basicInfoForm").validate({
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
			onlyId: {
                minlength: 6,
                maxlength: 20,
            },
            mrId: {
            	required: true,
                minlength: 6,
                maxlength: 10
            },
            visitNumber: {
            	required: true,
            	digits:true,
            	range:[1,2000]
            },
            medicalPayTypeCode: {
            	required: true
            },
            patientName: {
            	required: true,
                minlength: 2,
                maxlength: 10
            },
            documentTypeCode: {
            	required: true
            },
            idNumber: {
                idCard:true
            },
            sexCode: {
            	required:true
            },
            birthday: {
            	required:true
            },
            yearOfAge: {
            	required: true,
            	digits:true,
            	range:[1,200]
            },
            nationalityCode: {
            	required:true
            },
            nationCode: {
            	required:true
            },
            marriageCode: {
            	required:true
            },
            professionCode: {
            	required:true
            },telePhone: {
            	mobile:true
            },nativePlace: {
            	required: true,
                minlength: 6,
                maxlength: 200
            },linkManName: {
            	required: true,
                minlength: 2,
                maxlength: 10
            },linkManRelativeRelationCode: {
            	required:true
            },linkManPhone: {
            	required:true,
            	mobile:true
            },inHospitalTypeCode: {
            	required:true
            },inDeptCode: {
            	required:true
            },inHospitalDateTime: {
            	required:true
            },outDeptCode: {
            	required:true
            },outHospitalDateTime: {
            	required:true
            },inHospitalDayNumber: {
            	required:true,
            	number:true,
            	range:[1,500]
            },outHospitalTypeCode: {
            	required:true
            },
            rehospitalIntervalDayNumber: {
            	number:true,
            	range:[1,500]
            }
		}
	});
	
	$("input[name='autopsyCode']").each(function(){
		$(this).click(function(){
			 $(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val($(this).attr("title"));
		 });
		$(this).dblclick(function(){
			$(this).removeAttr("checked");
			var title = $('input:radio[name="autopsyCode"]:checked').attr("title");
			$(this).nextAll("input[name='"+$(this).attr("name").replace("Code", "Name")+"']").val(title);
		});
	});
	if(basicInfo.autopsyCode){
		$("input[name='autopsyCode'][value='"+basicInfo.autopsyCode+"']").attr("checked",true);
	}
	$("input[name='autopsyName']").val(basicInfo.autopsyName);
	
	$("input[type='submit']").removeAttr("disabled");
}

function checkBasicInfoData(){
	
	var inHospitalDateTimeStr = $("input[name='inHospitalDateTime']").val();
	var inHospitalDateTime = Date.parse(new Date(inHospitalDateTimeStr));
	
	var outHospitalDateTimeStr = $("input[name='outHospitalDateTime']").val();
	var outHospitalDateTime = Date.parse(new Date(outHospitalDateTimeStr));
	
	if(outHospitalDateTime > new Date()){
		layer.msg("出院日期应小于当前日期");
		return false;
	}
	
	if(inHospitalDateTime>outHospitalDateTime){
		layer.msg("入院日期应小于出院日期");
		return false;
	}
	var outHospitalTypeCode =$("select[name='outHospitalTypeCode']").val();
	if(outHospitalTypeCode){
		if(outHospitalTypeCode=='3'){
			if($('input:radio[name="autopsyCode"]:checked').length ==0){
				layer.msg("患者死亡离院，是否尸检未选择");
				return false;
			}
		}else{
			if($('input:radio[name="autopsyCode"]:checked').length > 0){
				layer.msg("患者非死亡离院，是否尸检不应被选择");
				return false;
			}
		}
	}
	
	var idNumber =$("input[name='idNumber']").val();
	if(idNumber){
		var birthday = $("input[name='birthday']").val();
		var reg=new RegExp("-","g"); //创建正则RegExp对象 
		var idNumberBirthday =birthday.replace(reg,"");
		if(idNumber.indexOf(idNumberBirthday) <= 0){
			layer.msg("出生日期和证件出生日期不一致");
			return false;
		}
		
	}
	return true;
}

var parseDateTime = function(fDate){  
    var fullDate = fDate.split("-");  
     
    return new Date(fullDate[0], fullDate[1]-1, fullDate[2], 0, 0, 0);  
} 

/*function homePageBasicDataSave(){
	$("[title='']").focus(function() { 
		$("[title='']").each(function() {
	  		$("[title='']").removeClass("input_list");
	  		
		});
 	});
	var num=0;//变量为1时 数据为空
   
    var notNull=false;//变量为true时提交数据
	$("[title='']").each(function() { //遍历元素对象   
      if ($(this).val()=="") { //判断元素对象的value值  
            $(this).addClass("input_list");  //添加css样式  
          num=1;
      }else{  
    	  notNull=true;
      }  

    }); 
    if(num==1){
	 	  alert("必选项不能为空!");
		  notNull=false;
		  return;
	}
	if(notNull==true){
		var age=$("#age").val();
		if(age!=null && age!=''){
			if(age<1 || age>150){
				alert("年龄范围不能小于0,或者大于150岁");
				return;
			}
		}
	}
	layer.load();
	$("#basicDataForm").submit();
	
}*/

