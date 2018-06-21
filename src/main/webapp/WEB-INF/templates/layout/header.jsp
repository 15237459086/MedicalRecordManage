<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="header_top">
	<div class="header_top_ul">
		<img src="${basePath }assets/images/logo.png" />
		<div class="h_right">
			<ul>
				<li>&nbsp;<a href="http://localhost:8082/MedicalRecordDataBase/" target="_blank">基础数据</a>&nbsp;
					|&nbsp;<a href="http://localhost:8080/SystemAuthority/" target="_blank">系统权限</a>&nbsp;
					<shiro:authenticated>
						|&nbsp;<a>当前用户：${currentUser.user_name }</a>
						|&nbsp;<a href="${basePath }login_out">退出登陆</a>
					</shiro:authenticated>
				</li>
				<li>
					<form name="formsearch" action="">
						<input type="hidden" name="kwtype" value="1" /> <input name="q"
							type="text" class="search-keyword" id="search-keyword" value=""
							onFocus="if(this.value==''){this.value='';}"
							onblur="if(this.value==''){this.value='';}" />
						<button type="submit" class="search-submit"></button>
					</form>
				</li>
			</ul>
		</div>
	</div>
</div>

<!------导航条--------->
<div id="nav">
 <ul class="multiUl">
  <li><a href="${basePath }index">首页</a></li>
  <shiro:hasAnyRoles name="role_version_1,role_version_1.1,role_version_2,role_version_2.1">
  	<shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record/pigeonholed_index" id="demoBtn">病案归档</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_1,role_version_1.1,role_version_2,role_version_2.1">
  	<shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record/quality_control_index" id="demoBtn1">病案质控</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_1.1,role_version_2.1">
  	<shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record/scan_index" id="demoBtn2">病案扫描</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_2,role_version_2.1">
  	<shiro:hasAnyRoles name="role_admin,role_coder">
  	<li><a href="${basePath }medical_record/coding_index" id="demoBtn3">病案编码</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_3">
  	<li><a href="#" id="demoBtn4">病案管理</a></li>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_1.1,role_version_2.1">
  	<shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record/windows_index" id="demoBtn5">窗口服务</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_1,role_version_1.1,role_version_2,role_version_2.1">
  	<shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record/trace_index" id="demoBtn7">病案示踪</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
   <shiro:hasAnyRoles name="role_version_1.1,role_version_2.1">
   <shiro:hasAnyRoles name="role_admin,role_normal">
  	<li><a href="${basePath }medical_record/borrow_index" id="demoBtn6">病案借阅</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
   <shiro:hasAnyRoles name="role_version_2.1">
   <shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record_searching/medical_record_searching_index" id="demoBtn61">病案检索</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_2.1">
  <shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }statistics_analysis/statistics_analysis_index" id="demoBtn8">统计分析</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_2.1">
  <shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record/work_statistics_index" id="demoBtn9">工作统计</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="role_version_2,role_version_2.1">
  <shiro:hasAnyRoles name="role_admin">
  	<li><a href="${basePath }medical_record/data_manage_index" id="demoBtn10">数据管理</a></li>
  	</shiro:hasAnyRoles>
  </shiro:hasAnyRoles>
 </ul>
</div>