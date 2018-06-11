function initPage(visitCostInfoJson){
	
	var costRecords = visitCostInfoJson['costRecords'];//费用集合
	for(var index in costRecords){
		var costRecord = costRecords[index];
		var medicalCostTypeNameObj = $("input[value^='"+costRecord.medicalCostTypeName+"']");
		medicalCostTypeNameObj.nextAll("input[name$='costMoney']").val(costRecord.costMoney);
		medicalCostTypeNameObj.val(costRecord.medicalCostTypeName);
		medicalCostTypeNameObj.prevAll("input[name$='medicalCostTypeCode']").val(costRecord.medicalCostTypeCode);
    } 
	
	var validator = $("#costInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			layer.load(1);
			return true;
		},
		rules:{
			
			totalCost: {
				number:true,
            	range:[1,500]
            },
            selfCost: {
				number:true,
            	range:[1,500]
            },
            'costRecords[0].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[1].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[2].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[3].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[4].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[5].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[6].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[7].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[8].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[9].costMoney': {
				number:true,
            	range:[1,500]
            },
            'costRecords[10].costMoney': {
				number:true,
            	range:[1,500]
            }
		}
	});
	
	
	
}