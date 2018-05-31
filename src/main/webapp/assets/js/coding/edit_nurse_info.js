
function initPage(){
	var validator = $("#nurseInfoForm").validate({
		errorElement: "title",
		submitHandler:function(form){
			layer.load(1);
			return true;
		},
		rules:{
			
            criticalDayNumber: {
            	range:[1,500]
            },
            sickDayNumber: {
            	range:[1,500]
            },
            specialNurseDayNumber: {
            	range:[1,500]
            },
            firstLevelNurseDayNumber: {
            	range:[1,500]
            },
            secondLevelNurseDayNumber: {
            	range:[1,500]
            },
            threeLevelNurseDayNumber: {
            	range:[1,500]
            }
		}
	});
}