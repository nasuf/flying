<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >

<title>Insert title here</title>
</head>
<body>
<div class="cover">
    <div class="navbar">
        <div class="container">
			<nav class="navbar navbar-fixed-top" role="navigation">
			   <div align="center">
			      <ul class="nav nav-tabs nav-justified">
			         <li class="active"><span class="glyphicon glyphicon-pencil"></span><a href="#">实名动态</a></li>
			         <li><a href="#">匿名八卦</a></li>
			      </ul>
			   </div>
			   <div class="collapse navbar-collapse" id="navbar-ex-collapse" style="margin-right:50px">
		           <ul class="nav navbar-nav navbar-right">
		               <li>
		               	   <input id="onLineUserName" style="display: none" value="${onLine_userName }"/>
		                   <a href="#">${onLine_userName }</a>
		               </li>
		             <%--   <li>
		                   <a href="${pageContext.request.contextPath }/user/logout.do?onLineUserName=${onLine_userName}">退出</a>
		               </li>
		               <li>
		               	   <!-- <img alt="" src="/imgs/icons/glyphicons-31-pencil.jpg" style="cursor:pointer" onclick="openMsgBox();"> -->
		               	   <a data-toggle="modal" data-target="#msg_modal" data-whatever="@getbootstrap" style="cursor:pointer">弹一下</a>
		               </li> --%>
		           </ul>
		       </div>
			</nav>
		</div>
	</div>
</div>
	
</body>
</html>