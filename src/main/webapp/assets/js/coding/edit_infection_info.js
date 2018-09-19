
function initPage(){
	var validator = $("#infectionInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			layer.load(1);
			return true;
		},
		rules:{
			
			infectionTotalTimes: {
            	range:[1,500]
            }
		}
	});
}