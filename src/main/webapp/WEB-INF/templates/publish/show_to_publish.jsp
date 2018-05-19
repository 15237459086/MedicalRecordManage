<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}assets/js/pdfobject.min.js"></script>
<%-- <object classid="clsid:F08DF954-8592-11D1-B16A-00C0F0283628" id="Slider1" width="100" height="50">
          <param name="BorderStyle" value="1" />
          <param name="MousePointer" value="0" />
          <param name="Enabled" value="1" />
          <param name="Min" value="0" />
          <param name="Max" value="10" />
          <param name="SRC" value="${basePath}assets/images/01.jpg">
</object> --%>
<%-- <embed width="800" height="600" src="${basePath}medical_record_scan/printToPublish"> </embed>
 --%>
 <div id="example1"></div>
 <input type="hidden" id="basePath" value="${basePath }"/>
<script>
	var options = {

		    height: "400px",
		
		    pdfOpenParams: { scrollbars: '0', toolbar: '0', statusbar: '0'}              //禁用工具栏代码
		
		};
		var basePath = $("#basePath").val();
		    PDFObject.embed(basePath+"medical_record_scan/printToPublish", "#example1",options);
</script>