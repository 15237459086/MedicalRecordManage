<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>病案管理</title>
<style>
#box {
	max-width:1366px;
	min-width:1024px;
	height: 400px;
	margin: 120px auto;
}
</style>

<link rel="stylesheet"
	href="${basePath}assets/bootstrap/css/bootstrap.css" />
	<link href="${basePath}assets/css/basic.css" rel="stylesheet">
<link href="${basePath}assets/css/list.css" rel="stylesheet" />
<link href="${basePath}assets/css/login.css" rel="stylesheet" />
<script type="text/javascript"
	src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>

<script type="text/javascript" src="${basePath}assets/jqueryValidate/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>

</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}" />
	<div id="box">
		<div id="div_login">

			<div class="login">
				<form action="${basePath}user/login" method="post" id="loginForm">
					<ul>
						<li>
							<p style="width: 65px; float: left;">用户名：</p>
							<p style="float: left; width: 100px;">
								<INPUT style="float: none;" name="loginName" id="loginName"
									type="text" class="textbox" required>
							</p>
						</li>

						<li>
							<p style="width: 65px; float: left;">密&nbsp;&nbsp; 码：</p>
							<p style="float: left; width: 100px;">
								<INPUT style="float: none;" name="password" id="password"
									type="password" class="textbox" value="" required>
							</p>
						</li>
						<li>验证码：&nbsp;&nbsp;<INPUT name="verificationCode"
							id="verificationCode" type="text" class="check" value="" required>
							&nbsp;&nbsp;&nbsp;
							<img id="img" src="<%=basePath%>captcha/verification_code" onclick="refresh()"></li>
						<li><a><INPUT class="L_up" id="btn_login"
								type="button" value="登&nbsp;&nbsp;录" onclick="login()"></a>
							<a><INPUT name="btnLogin" class="L_up2" id="closeBtn"
								type="reset" value="重&nbsp;&nbsp;置"></a></li>

					</ul>
				</form>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var basePath = $("#basePath").val();
		if(parent.document.getElementById("mainContent")){
			parent.location.href=basePath+"login";
		}
		$(document).keyup(function(event){
		  if(event.keyCode ==13){
		    $("#btn_login").trigger("click");
		  }
		});
		var validator = $("#loginForm").validate({
			errorElement: "title",
			submitHandler : function(){
				var basePath = $("#basePath").val();
				layer.load(1);
    			var submitData = $('#loginForm').serialize();
    			$.ajax({
					url: basePath + "login_check",
					type:"POST",
					dataType: "json",
					data:submitData,
					success: function( data ) {
						console.log(data);
						var success = data['success'];
						if(success){
							var stateCode = data['stateCode'];
							if(1==stateCode){
								layer.msg("登陆成功");
								window.location.replace(basePath+"index");
							}else{
								var resultData = data['data'];
								layer.msg(resultData);
							}
						}else{
							var resultData = data['data'];
							layer.msg(resultData);
						}
						
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						layer.msg("未知错误，请联系管理员");
					},
					complete:function(XMLHttpRequest, textStatus){
						layer.closeAll('loading');
					}
				});
			},
			messages: {
				loginName: {
					required: " (必需字段)"
				},
				password: {
					required: " (必需字段)"
				},verificationCode: {
					required: " (必需字段)",
					
				}
			}
		});
	});

	function refresh() {  
	    var url = $("#basePath").val() + "captcha/verification_code?number="+Math.random();  
	    $("#img").attr("src",url);  
	}
	
	function login(){
    	$("#loginForm").submit();
    }
	
    /* function login(){
    	var basePath = $("#basePath").val();
    	var userName= $("#loginName").val();
    	var password=$("#password").val();
    	if(!userName){
    		layer.msg("用户名不能为空!");
    		return;
    	}
    	if(!password){
    		layer.msg("密码不能为空!");
    		return;
    	}
    	var verificationCode=$("#verificationCode").val();
    	if(!verificationCode){
    		layer.msg("验证码不能为空!");
    		return;
    	}
    	layer.load(1);
    	var submitData = $('#loginForm').serialize();
    	$.ajax({
			url: basePath + "login_check",
			type:"POST",
			dataType: "json",
			data:submitData,
			success: function( data ) {
				console.log(data);
				var success = data['success'];
				if(success){
					var stateCode = data['stateCode'];
					if(1==stateCode){
						layer.msg("登陆成功");
						window.location.replace(basePath);
					}else{
						var resultData = data['data'];
						layer.msg(resultData);
					}
				}else{
					var resultData = data['data'];
					layer.msg(resultData);
				}
				
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				layer.msg("未知错误，请联系管理员");
			},
			complete:function(XMLHttpRequest, textStatus){
				layer.closeAll('loading');
			}
		});
    } */
</script>
</html>
