<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	function loadContent(url) {
		$("#mainContent").attr("src", url);
	}
</script>
<div id="list_main">
	<div id="list_DH">
	  <div class="list_menu2">
	   <h3>病案借阅</h3>
	   	<h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_borrow/borrow_apply')">借阅申请</a></h4>
	  	<shiro:hasAnyRoles name="role_admin">
	   	<h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_borrow/borrow_reply')">借阅审批</a></h4>
	  	</shiro:hasAnyRoles>
	  	<h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_borrow/borrow_record')">借阅记录</a></h4>
	  	
	  
	  </div>
	 </div>
	<div id="list_NR">
		<iframe id="mainContent" style="border: 0px; overflow-x: hidden; min-height: 500px;" width="100%"  scrolling="no" src="">
				
		</iframe>
	</div>
</div>
