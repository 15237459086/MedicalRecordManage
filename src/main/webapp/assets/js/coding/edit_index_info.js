function codingFinish(visitGuid){
	var basePath = $("#basePath").val();
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record_coding/ajax_coding_finish",
		dataType: "json",
		type: "POST",
		data:{visitGuid:visitGuid},
		success: function( data ) {
			var success = data['success'];
			var resultMessage = data['data'];
			var stateCode = data['stateCode'];
			if(success && stateCode == 1){
				layer.msg("编码完成成功");
				
			}else{
				layer.msg(resultMessage);
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