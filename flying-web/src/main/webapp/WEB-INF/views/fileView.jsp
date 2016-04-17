<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/jqueryFileTree/jqueryFileTree.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >
<link rel="stylesheet" href="/jqueryFileTree/jqueryFileTree.css" >

<style type="text/css">
/* .modal-body {
    -ms-overflow-style: scrollbar;
} */
</style>

<script type="text/javascript">

$(document).ready( function() {
    $('#fileDiv').fileTree({ 
    	//root:'/',
  		script: '/jqueryFileTree/connectors/jqueryFileTree.jsp',
  	    expandSpeed: 500,
  	    collapseSpeed: 500,
  	    multiFolder: false
    	}, function(file) {
        alert(file);
    });
});

	//对url中的中文文件名进行编码
	function setUrl(){
		var mapSize = $("#mapSize").val();
		for(var i=0;i<mapSize;i++){
			var fileFullName = encodeURI($("#fileFullName_"+i+"").val()); 
			var repoName = encodeURI($("#repoName_"+i+"").val());
			//alert(fileFullName+"---"+repoName);
			$("#downloadA_"+i+"").attr("href","${pageContext.request.contextPath }/file/downloadFiles.do?fileName="+fileFullName+"&repoName="+repoName+"");
		}
	}


</script>

</head>
<body>
	<input id="uploaderName" style="display:none" value="${uploaderName }">
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title" id="myModalLabel">
      	文件浏览 
      </h4>
    </div>
    <div class="modal-body" id="myModalBody"> 
    	<c:choose>
    		<c:when test="${fileNameMap == null}">您尚未上传任何文件...</c:when>
    		<c:otherwise>
    			<table class="table table-hover">
					<tr>
						<td>文件名</td>
						<td>操作</td>
					</tr>
					<c:forEach var="file" items="${fileNameMap}" varStatus="status">
						<tr>
							<input style="display:none" value="${file.key }" id="fileFullName_${status.index }">
							<input style="display:none" value="${repoName}" id="repoName_${status.index }">
							<input style="display:none" value="${fileNameMap.size()}" id="mapSize">
							<td>${file.value}</td>
							<td>
								<a id="downloadA_${status.index }" type="button" onclick="setUrl();" class=" btn btn-primary btn-xs">
									download	
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
    		</c:otherwise>
    	</c:choose>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
    </div>
</body>
</html>