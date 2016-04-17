<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >
<link rel="stylesheet" href="/css/left-menu.css" >
<title>manage</title>

<style type="text/css">
	.collapsed{
		font-size: 12px;
        font-weight: 600;
        color: #4A515B;
	}
</style>

<script type="text/javascript">
	
</script>

</head>
<body>
	<%-- <input type="hidden" value="${id }" id="input_userId"> --%>
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title" id="myModalLabel">
      </h4>
    </div>
    <div class="modal-body" id="myModalBody"> 
     	<table class="table table-hover" id="tb_essayList">
 				
 				<c:choose>
					<c:when test="${essayList == null }">
						该用户尚未增添任何文章
					</c:when>
					<c:otherwise>
						<tr>
		 					<td>#</td>
		 					<td>作者</td>
		 					<td>标题</td>
		 					<td>副标题</td>
		 					<td>保存时间</td>
		 					<td>推送时间</td>
		 					<td>类型</td>
		 					<td>状态</td>
		 					<td>操作</td>
		 				</tr>
						<c:forEach var="essay" items="${essayList}">
		 					<tr>
		 						<td>${essay.essayId }</td>
		 						<td>${essay.author }</td>
		 						<td>${essay.title }</td>
		 						<td>${essay.subTitle }</td>
		 						<td>${essay.saveDate }</td>
		 						<td>${essay.pushDate }</td>
		 						<td>${essay.type }</td>
		 						<td>${essay.status }</td>
		 						<td>
		 							<a type="button" href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
		 								查看	
									</a>
								</td>
		 					</tr>
	 					</c:forEach>
					</c:otherwise>
				</c:choose>
		</table>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      <button type="button" class="btn btn-primary">Save changes</button>
    </div>
</body>
</html>






