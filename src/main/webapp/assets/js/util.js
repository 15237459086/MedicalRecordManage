//性别
function formatSex(sex){
 	switch(sex) {
 		case 1: 
 			return "男";
 		break; 
 		case 2: 
 			return "女";
 		break; 
 		case 3: 
 			return "保密";
 		break; 
 		default: 
 			return "其他";
 		}
} 

//校验用户名
/*function regName(name){
	var pattern = /[A-Za-z0-9_\-\u4e00-\u9fa5]+/;
	return pattern.test(name);
}*/

//验证姓名
function checkChinese(name) {
	var re = /^[\u4e00-\u9fa5]{2,4}$/;
	if (!re.test(name)){
	return false; 
	}
	return true;
}; 


//校验身份证
function validateIdCard(idCard) {  
    //15位和18位身份证号码的正则表达式  
    var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;  
    //如果通过该验证，说明身份证格式正确，但准确性还需计算  
    if (regIdCard.test(idCard)) {  
        if (idCard.length == 18) {  
            var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里  
           var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组  
            var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和  
            for (var i = 0; i < 17; i++) {  
                idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];  
            }  
            var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置  
            var idCardLast = idCard.substring(17);//得到最后一位身份证号码  
           //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if (idCardMod == 2) {  
                if (idCardLast == "X" || idCardLast == "x") {   
                   return true; 
                } else {  
                    alert("身份证号码错误！");  
                    return false;  
                }  
            } else {  
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码  
                if (idCardLast == idCardY[idCardMod]) {   
                    return true;  
                } else {  
                    alert("身份证号码错误！");  
                    return false; 
                }  
            }  
        }  
    } else {  
        alert("身份证格式不正确!");  
        return false;  
    }  
}  

//这个脚本是 ie6和ie7 通用的脚本
function custom_close(){
if 
(confirm("您确定要关闭本页吗？")){
window.opener=null;
window.open('','_self');
window.close();
}
else{}
}


//验证身份证
function isCardNo(card)  
{  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(card) === false)  
   {  
       return  false;  
   } else{
   	return true;
   }
} 

function isint(num){ 
	 var  reg =/^\\d+$/;
		 return reg.test(num);
} 



//验证费用
function validateMoney(money){
	 alert(money);
	var reg =/^\d+(\.\d+)?$/;
	if(!reg.test(money)){
		alert(1);
	   return false;
	}else{
		alert(2);
		return true;
	}
}

//验证数字
function validateNum(num){  
    var reg = new RegExp("^[0-9]*$");  
 if(!reg.test(num)){  
     return false;  
 }else{
	  return true;
 }
}

//校验联系方式
function regPhone(phone){
	 var regPhone=/^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
	 if(!regPhone.test(phone)){
		 return false;
	 }
	 return true;
}
function  checkMobile(str) {

   var  re = /^1\d{10}$/;
   if (re.test(str)) {
       alert("正确");
   } else {
       alert("错误");
   }
}


//比较日期，时间大小  
function compareCalendar(startDate, endDate) {   
	 var start = startDate.replace("-","/");//替换字符，变成标准格式   
	 var end=endDate.replace("-","/");
	 var d2=new Date();//取今天的日期    
	 var d1 = new Date(Date.parse(start));    
	 var d2 = new Date(Date.parse(end));    
	 if(d1>d2){    
	   return false;
	 }  else{
		 return true;
	 }
}   

