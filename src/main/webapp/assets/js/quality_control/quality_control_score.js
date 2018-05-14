
$(function(){
	var basePath = $("#basePath").val();
	var visitGuid = $("#visitGuid").val();
	$.ajax({
		url: basePath + "medical_record/ajax_medical_record_quality_control",
		type: "GET",
		data:{visitGuid:visitGuid},
		dataType: "json",
		success: function( datas ) {
			console.log(datas);
			init(datas);
			
		}
	});
});

function init(datas){
	
	var data = datas['data'];
	var medicalRecords = data['medicalRecords'];
	if(medicalRecords.length > 0){
		var medicalRecord = medicalRecords[0];
		$("label[class='mr_id']").html(medicalRecord.mr_id);
		$("label[class='patient_name']").html(medicalRecord.patient_name);
		$("label[class='id_number']").html(medicalRecord.id_number);
		
		$("label[class='only_id']").html(medicalRecord.only_id);
		$("label[class='out_hospital_date']").html(medicalRecord.out_hospital_date);
		$("label[class='out_dept_name']").html(medicalRecord.out_dept_name);
		
		var qualityControlPoints = data['qualityControlPoints'];
		
		$("select[name='firstLevelCode']").each(function(){
			var options = "";
			for(var index in qualityControlPoints){
				var qualityControlPoint = qualityControlPoints[index];
		    	options+="<option title='"+(qualityControlPoint.score==undefined?"":qualityControlPoint.score)+"' value='"+qualityControlPoint.code+"'>"+qualityControlPoint.project+"</option>";
		    } 
	       $(this).append(options);
	       $(this).change(function(){
	    	   $("#addForm select[name='secondLevelCode'] option[value!='']").remove();
	    	   $("#addForm select[name='secondLevelCode']").change();
		   		var eventObj = $(this);
		   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
		   		
		   		if(eventObj.val()!=""){
		   			var name=eventObj.find("option:selected").text();
		   			eventObj.next("input[name='"+nameObjName+"']").val(name);
		   			fillSecondLevel(eventObj.val());
		   		}else{
		   			eventObj.next("input[name='"+nameObjName+"']").val("");
		   		}
		   		
		   });
		});
		var qualityControlItems = data['qualityControlItems'];
		addRows(qualityControlItems);
		iframeAutoHeight("mainContent");
	}
	
	
}

function fillSecondLevel(upOneLevelCode){
	var basePath = $("#basePath").val();
	$.ajax({
		url: basePath + "base_info/ajax_quality_control_point_by_up_level_code",
		type: "GET",
		data:{upOneLevelCode:upOneLevelCode},
		dataType: "json",
		success: function( datas ) {
			var qualityControlPoints = datas['data'];
			
			$("#addForm select[name='secondLevelCode']").each(function(){
				var options = "";
				for(var index in qualityControlPoints){
					var qualityControlPoint = qualityControlPoints[index];
			    	options+="<option title='"+(qualityControlPoint.score==undefined?"":qualityControlPoint.score)+"' value='"+qualityControlPoint.code+"'>"+qualityControlPoint.project+"</option>";
			    } 
		       $(this).append(options);
		       $(this).change(function(){
			   		var eventObj = $(this);
			   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
			   		$("#addForm select[name='thirdLevelCode'] option[value!='']").remove();
			    	$("#addForm select[name='thirdLevelCode']").change();
			   		if(eventObj.val()!=""){
			   			var name=eventObj.find("option:selected").text();
			   			eventObj.next("input[name='"+nameObjName+"']").val(name);
			   			fillThirdLevel(eventObj.val());
			   		}else{
			   			eventObj.next("input[name='"+nameObjName+"']").val("");
			   		}
			   		
			   });
			});
			
		}
	});
	
}


function fillThirdLevel(upOneLevelCode){
	var basePath = $("#basePath").val();
	$.ajax({
		url: basePath + "base_info/ajax_quality_control_point_by_up_level_code",
		type: "GET",
		data:{upOneLevelCode:upOneLevelCode},
		dataType: "json",
		success: function( datas ) {
			var qualityControlPoints = datas['data'];
			
			$("#addForm select[name='thirdLevelCode']").each(function(){
				var options = "";
				for(var index in qualityControlPoints){
					var qualityControlPoint = qualityControlPoints[index];
			    	options+="<option title='"+(qualityControlPoint.score==undefined?"":qualityControlPoint.score)+"' value='"+qualityControlPoint.code+"'>"+qualityControlPoint.project+"</option>";
			    } 
		       $(this).append(options);
		       $(this).change(function(){
			   		var eventObj = $(this);
			   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
			   		
			   		if(eventObj.val()!=""){
			   			var name=eventObj.find("option:selected").text();
			   			eventObj.next("input[name='"+nameObjName+"']").val(name);
			   			$("#addForm input[name='deduction']").val(eventObj.find("option:selected").first().attr("title"));
			   			var option=eventObj.find("option:selected").first().attr("title");
			   			var title = eventObj.attr("title");
			   		}else{
			   			eventObj.next("input[name='"+nameObjName+"']").val("");
			   			$("#addForm input[name='deduction']").val("");
			   		}
			   		
			   });
			});
			
		}
	});
	
}



/*提交新增质控*/
function addQualityControlFormSubmit(){
	
	var submitData = $('#addForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record/ajax_add_quality_control_item",
		type:"POST",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			console.log(data);
			var success = data['success'];
			if(success){
				var qualityControlItem = data['data'];
				$("#addForm select[name='firstLevelCode']").get(0).selectedIndex = 0;
				$("#addForm select[name='firstLevelCode']").first().change();
				$("#addForm input[name='deduction']").val("");
				$("#addForm input[name='remark']").val("");
				addNewRow(qualityControlItem);
				layer.msg("添加成功");
				iframeAutoHeight("mainContent");
				
			}else{
				layer.msg("添加失败");
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
function qualityControlFinishSubmit(){
	var basePath = $("#basePath").val();
	var visitGuid = $("#visitGuid").val();
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record/ajax_finish_quality_control",
		type:"POST",
		dataType: "json",
		data:{visitGuid,visitGuid},
		success: function( data ) {
			console.log(data);
			var success = data['success'];
			if(success){
				var stateCode = data['stateCode'];
				if(stateCode == 1){
					layer.msg("病案质控完成");
				}else if(stateCode == 2){
					layer.msg("此病案已质控完成，不需重复完成");
				}
				
			}else{
				layer.msg("病案质控完成添加失败");
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
/*添加列表行*/
function addRows(qualityControlItems){
	for(var index in qualityControlItems){
		var qualityControlItem = qualityControlItems[index];
		var add_content=$("#template_tr").clone();
		add_content.removeAttr("hidden");
		add_content.attr("id",qualityControlItem.id);
		var projectDesc = qualityControlItem.first_level_name+";"+qualityControlItem.second_level_name+";"+qualityControlItem.third_level_name;
		add_content.find("span[class='project']").html(projectDesc).attr("title",projectDesc);
		add_content.find("span[class='deduction']").html(qualityControlItem.deduction);
		add_content.find("span[class='remark']").html(qualityControlItem.remark);
		add_content.find("span[class='last_user_name']").html(qualityControlItem.last_user_name);
		add_content.find("span[class='last_date_format']").html(qualityControlItem.last_date_time_format);
		
		$("#data_show_table tbody").append(add_content);
    } 
	
}

/*添加列表行*/
function addNewRow(qualityControlItem){
	
	var add_content=$("#template_tr").clone();
	add_content.removeAttr("hidden");
	add_content.attr("id",qualityControlItem.id);
	var projectDesc = qualityControlItem.firstLevelName+";"+qualityControlItem.secondLevelName+";"+qualityControlItem.thirdLevelName;
	
	add_content.find("span[class='project']").html(projectDesc).attr("title",projectDesc);
	add_content.find("span[class='deduction']").html(qualityControlItem.deduction);
	add_content.find("span[class='remark']").html(qualityControlItem.remark);
	add_content.find("span[class='last_user_name']").html(qualityControlItem.lastUserName);
	add_content.find("span[class='last_date_format']").html(qualityControlItem.lastDateTime);
	
	$("#data_show_table tbody").append(add_content);
	
}

/*修改列表行*/
function updateRow(qualityControlItem){
	
	var add_content=$("#data_show_table tr[id='"+qualityControlItem.id+"']");
	var projectDesc = qualityControlItem.firstLevelName+";"+qualityControlItem.secondLevelName+";"+qualityControlItem.thirdLevelName;
	
	add_content.find("span[class='project']").html(projectDesc).attr("title",projectDesc);
	add_content.find("span[class='deduction']").html(qualityControlItem.deduction);
	add_content.find("span[class='remark']").html(qualityControlItem.remark);
	add_content.find("span[class='last_user_name']").html(qualityControlItem.lastUserName);
	add_content.find("span[class='last_date_format']").html(qualityControlItem.lastDateTime);
	
}

//清空列表
function clearPage(){
	$("#template_tr").siblings("tr").remove();
	$("#page_plus").html("");
	$("#totalPage").html("0");
    $("#currentPage").html("0/0");
}

var updateFormIndex = 0;

function updateFormShow(obj){
	var qualityControlItemId =  $(obj).parent().parent().attr("id");
	var basePath = $("#basePath").val();
	
	//页面层
	var add_content=$("#updateDiv").clone();
	add_content.find("form").attr("id","updateForm");
	
	updateFormIndex = layer.open({
	  type: 1,
	  title:'修改质控评分',
	  skin: 'layui-layer-rim', //加上边框
	  area: ['930px', '450px'], //宽高
	  content: add_content.html(),
	  success: function(layero, index){
		  
		  
		  $.ajax({
			url: basePath + "medical_record/ajax_medical_record_quality_control_item",
			type: "GET",
			dataType: "json",
			data:{qualityControlItemId:qualityControlItemId},
			success: function( datas ) {
				var success = datas['success'];
				if(success){
					var data =  datas['data'];
					var qualityControlItem = data['qualityControlItem'];
					$("#updateForm input[name='deduction']").val(qualityControlItem.deduction);
					$("#updateForm input[name='remark']").val(qualityControlItem.remark);
					var secondQualityControlPoints = data['secondQualityControlPoints'];
					var firstLeveIndex = $("#updateForm select[name='firstLevelCode'] option[value='"+qualityControlItem.firstLevelCode+"']").first().index();
					$("#updateForm select[name='firstLevelCode']").get(0).selectedIndex = firstLeveIndex;
					$("#updateForm input[name='firstLevelName']").val(qualityControlItem.firstLevelName);
					$("#updateForm select[name='firstLevelCode']").each(function(){
						  $(this).change(function(){
					    	   $("#updateForm select[name='secondLevelCode'] option[value!='']").remove();
					    	   $("#updateForm select[name='secondLevelCode']").change();
						   		var eventObj = $(this);
						   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
						   		
						   		if(eventObj.val()!=""){
						   			var name=eventObj.find("option:selected").text();
						   			eventObj.next("input[name='"+nameObjName+"']").val(name);
						   			fillUpdateFormSecondLevel(eventObj.val());
						   			
						   		}else{
						   			eventObj.next("input[name='"+nameObjName+"']").val("");
						   		}
						   });
					  });
					 
					$("#updateForm select[name='secondLevelCode']").each(function(){
						var options = "";
						for(var index in secondQualityControlPoints){
							var qualityControlPoint = secondQualityControlPoints[index];
					    	options+="<option title='"+(qualityControlPoint.score==undefined?"":qualityControlPoint.score)+"' value='"+qualityControlPoint.code+"'>"+qualityControlPoint.project+"</option>";
					    } 
				       $(this).append(options);
				       $(this).change(function(){
					   		var eventObj = $(this);
					   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
					   		$("#updateForm select[name='thirdLevelCode'] option[value!='']").remove();
					    	$("#updateForm select[name='thirdLevelCode']").change();
					   		if(eventObj.val()!=""){
					   			var name=eventObj.find("option:selected").text();
					   			eventObj.next("input[name='"+nameObjName+"']").val(name);
					   			fillUpdateFormThirdLevel(eventObj.val());
					   		}else{
					   			eventObj.next("input[name='"+nameObjName+"']").val("");
					   		}
					   		
					   });
					});
					var secondLeveIndex = $("#updateForm select[name='secondLevelCode'] option[value='"+qualityControlItem.secondLevelCode+"']").first().index();
					$("#updateForm select[name='secondLevelCode']").get(0).selectedIndex = secondLeveIndex;
					$("#updateForm input[name='secondLevelName']").val(qualityControlItem.secondLevelName);
					
					var thirdQualityControlPoints = data['thirdQualityControlPoints'];
					
					$("#updateForm select[name='thirdLevelCode']").each(function(){
						var options = "";
						for(var index in thirdQualityControlPoints){
							var qualityControlPoint = thirdQualityControlPoints[index];
					    	options+="<option title='"+(qualityControlPoint.score==undefined?"":qualityControlPoint.score)+"' value='"+qualityControlPoint.code+"'>"+qualityControlPoint.project+"</option>";
					    } 
				       $(this).append(options);
				       $(this).change(function(){
					   		var eventObj = $(this);
					   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
					   		
					   		if(eventObj.val()!=""){
					   			var name=eventObj.find("option:selected").text();
					   			eventObj.next("input[name='"+nameObjName+"']").val(name);
					   			$("#updateForm input[name='deduction']").val(eventObj.find("option:selected").first().attr("title"));
					   			var option=eventObj.find("option:selected").first().attr("title");
					   			var title = eventObj.attr("title");
					   		}else{
					   			eventObj.next("input[name='"+nameObjName+"']").val("");
					   			$("#updateForm input[name='deduction']").val("");
					   		}
					   		
					   });
					});
					var thirdLeveIndex = $("#updateForm select[name='thirdLevelCode'] option[value='"+qualityControlItem.thirdLevelCode+"']").first().index();
					$("#updateForm select[name='thirdLevelCode']").get(0).selectedIndex = thirdLeveIndex;
					$("#updateForm input[name='thirdLevelName']").val(qualityControlItem.thirdLevelName);
					$("#updateForm input[name='id']").val(qualityControlItemId);
					var visitGuid = $("#visitGuid").val();
					$("#updateForm input[name='visitGuid']").val(visitGuid);
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

function fillUpdateFormSecondLevel(upOneLevelCode){
	var basePath = $("#basePath").val();
	$.ajax({
		url: basePath + "base_info/ajax_quality_control_point_by_up_level_code",
		type: "GET",
		data:{upOneLevelCode:upOneLevelCode},
		dataType: "json",
		success: function( datas ) {
			var qualityControlPoints = datas['data'];
			
			$("#updateForm select[name='secondLevelCode']").each(function(){
				var options = "";
				for(var index in qualityControlPoints){
					var qualityControlPoint = qualityControlPoints[index];
			    	options+="<option title='"+(qualityControlPoint.score==undefined?"":qualityControlPoint.score)+"' value='"+qualityControlPoint.code+"'>"+qualityControlPoint.project+"</option>";
			    } 
		       $(this).append(options);
		       $(this).change(function(){
			   		var eventObj = $(this);
			   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
			   		$("#updateForm select[name='thirdLevelCode'] option[value!='']").remove();
			    	$("#updateForm select[name='thirdLevelCode']").change();
			   		if(eventObj.val()!=""){
			   			var name=eventObj.find("option:selected").text();
			   			eventObj.next("input[name='"+nameObjName+"']").val(name);
			   			fillUpdateFormThirdLevel(eventObj.val());
			   		}else{
			   			eventObj.next("input[name='"+nameObjName+"']").val("");
			   		}
			   		
			   });
			});
			
		}
	});
	
}

function fillUpdateFormThirdLevel(upOneLevelCode){
	var basePath = $("#basePath").val();
	$.ajax({
		url: basePath + "base_info/ajax_quality_control_point_by_up_level_code",
		type: "GET",
		data:{upOneLevelCode:upOneLevelCode},
		dataType: "json",
		success: function( datas ) {
			var qualityControlPoints = datas['data'];
			
			$("#updateForm select[name='thirdLevelCode']").each(function(){
				var options = "";
				for(var index in qualityControlPoints){
					var qualityControlPoint = qualityControlPoints[index];
			    	options+="<option title='"+(qualityControlPoint.score==undefined?"":qualityControlPoint.score)+"' value='"+qualityControlPoint.code+"'>"+qualityControlPoint.project+"</option>";
			    } 
		       $(this).append(options);
		       $(this).change(function(){
			   		var eventObj = $(this);
			   		var nameObjName = eventObj.attr("name").replace("Code", "Name");
			   		
			   		if(eventObj.val()!=""){
			   			var name=eventObj.find("option:selected").text();
			   			eventObj.next("input[name='"+nameObjName+"']").val(name);
			   			$("#updateForm input[name='deduction']").val(eventObj.find("option:selected").first().attr("title"));
			   			var option=eventObj.find("option:selected").first().attr("title");
			   			var title = eventObj.attr("title");
			   		}else{
			   			eventObj.next("input[name='"+nameObjName+"']").val("");
			   			$("#updateForm input[name='deduction']").val("");
			   		}
			   		
			   });
			});
			
		}
	});
	
}

/*提交修改质控*/
function updateQualityControlFormSubmit(){
	var submitData = $('#updateForm').serialize();
	var basePath = $("#basePath").val();
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record/ajax_update_quality_control_item",
		type:"POST",
		dataType: "json",
		data:submitData,
		success: function( data ) {
			console.log(data);
			var success = data['success'];
			if(success){
				var qualityControlItem = data['data'];
				updateRow(qualityControlItem);
				layer.msg("修改评价成功");
				layer.close(updateFormIndex);
				
			}else{
				layer.msg("修改评价失败");
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


/*删除质控评价*/
function deleteQualityControlSubmit(obj){
	var qualityControlItemRow = $(obj).parent().parent();
	
	var qualityControlItemId =  qualityControlItemRow.attr("id");
	var visitGuid = $("#visitGuid").val();
	var basePath = $("#basePath").val();
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record/ajax_delete_quality_control_item",
		type:"POST",
		dataType: "json",
		data:{visitGuid:visitGuid,qualityControlItemId,qualityControlItemId},
		success: function( data ) {
			console.log(data);
			var success = data['success'];
			if(success){
				qualityControlItemRow.remove();
				layer.msg("删除评价成功");
				
			}else{
				layer.msg("删除评价失败");
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

