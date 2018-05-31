<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<link href="${basePath}assets/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
       
<script src="${basePath}assets/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="${basePath}assets/fileinput/js/fileinput_locale_zh.js" type="text/javascript"></script>
<div class="loc">
	<h3>病案导入</h3>
	<ul class="loc_loc"><li> 当前位置：病案归档> 病案导入</li>
	</ul>
</div>

<div id="query_div" class="list_con_table">
	<div class="kv-main">
		<input type="hidden" id="resultCode" value="${param.resultCode }"/>
          <form action="${basePath}medical_record/import_medical_record" method="post" enctype="multipart/form-data">
          	<input type="hidden" name="visitGuid" id="visitGuid"/>
          	<div style="overflow-y: scroll;max-height: 300px;">
              	<input id="file-0" class="file" style="width: 300px;" type="file" name="uploadFiles" data-min-file-count="1" accept=".xls">
              </div>
              <br>
              <button type="submit" class="btn btn-primary">提交</button>
              <button id="btn_reset" type="reset" class="btn btn-default">清空</button>
              <button type="button" class="btn btn-default" onclick="btnRetreat()">返回</button>
          </form>
      </div>	 

</div>
 
<script>
    $("#file-0").fileinput({
    	language: 'zh', //设置语言
        allowedFileExtensions : ['xls'],
        showUpload: false,
        showRemove:	false,
        showPreview: false
    });
    $(function(){
    	var resultCode= $("#resultCode").val();
    	if(resultCode){
    		if(resultCode == -1){
    			layer.msg("病案数据导入失败");
    		}else{
    			layer.msg("病案数据导入成功，导入数量为"+resultCode);
    		}
    	}
    });
</script>