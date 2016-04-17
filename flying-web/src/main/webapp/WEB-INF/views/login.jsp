<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >
<title>login_page</title>

<style type="text/css">
	/*黑色按钮*/
	/* .btn{
		background-color:#222;
		border-color:#222;
	} */
	
	.body{
		background-color: #D1D8DF;
	}
	
</style>

<script type="text/javascript">
	
	
	function checkUserInfo(){
		var username = $("#username_input").val();
		var password = $("#password_input").val();
		
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '${pageContext.request.contextPath}/user/doLogin.do?'
					+ Math.random(),
			Cache : false,
			data : {
				userName : username,
				password : password
			},
			dataType : 'json',
			success : function(data) {
				if (data.status == 200) {
					//alert("登陆成功，即将跳转到首页");
					window.location.href = '${pageContext.request.contextPath}/user/toIndex.do?userName='+username+'';
				} else if (data.status == 500){
					alert("登陆失败，请重新登陆");
					window.location.href = '${pageContext.request.contextPath}/user/toLogin.do';
				}
			},
			status:function(data){
				if(data.status == 200)
					alert("登陆成功status");
				else
					alert("登陆失败status");
			},
			error : function() {
			}
		}); 
	}
	
	function toRegistPage(){
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '${pageContext.request.contextPath}/user/doRegist.do?'
					+ Math.random(),
			Cache : false,
			data : {
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					loginResponseMsg = "登陆成功！"
					//alert(loginResponseMsg);
				}
			},
			error : function() {
				//alert(data.failReason);
			}
		}); 
	}
	
	$(document).ready(function(){
		  $(".info_div").fadeIn(1000);
		});

</script>

<style type="text/css">
	.navbar{
		opacity:0.9
	}
	.info_div{
		opacity:0.8
	}
</style>

</head>

<body class="body" style="background-image:url(/imgs/img_index_nav.jpg);background-position:center; background-repeat:repeat-y">
	<nav class="navbar navbar-inverse" role="navigation">
	   <div class="navbar-header" style="margin-left:50px">
	      <a class="navbar-brand" href="#">伶然</a>
	   </div>
	   <div>
	      <ul class="nav navbar-nav" style="margin-left:50px">
	         <li><a href="#">首页</a></li>
	         <li class="active"><a href="${pageContext.request.contextPath}/user/toLogin.do">登陆</a></li>
	         <li><a href="${pageContext.request.contextPath}/user/toRegist.do">注册</a></li>
	        <!--  <li class="dropdown">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	               	注册<b class="caret"></b>
	            </a>
	            <ul class="dropdown-menu">
	               <li><a href="#">jmeter</a></li>
	               <li><a href="#">EJB</a></li>
	               <li><a href="#">Jasper Report</a></li>
	               <li class="divider"></li>
	               <li><a href="#">分离的链接</a></li>
	               <li class="divider"></li>
	               <li><a href="#">另一个分离的链接</a></li>
	            </ul>
	         </li> -->
	      </ul>
	   </div>
	</nav>
	
	<div class="info_div" style="padding: 100px 100px 50px; margin-top: 80px;display: none/* background-image:url('/imgs/div_background.jpg');background-repeat: no-repeat;background-attachment: fixed;background-position: center; */" id="userInfo_div"  align="center">
	   <form class="bs-example bs-example-form" role="form">
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">用户名</span>
	         <input id="username_input" type="text" class="form-control" placeholder="请输入用户名">
	      </div>
	      <br>
	
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">密码</span>
	         <input id="password_input" type="password" class="form-control" placeholder="请输入密码">
	      </div>
	      <br>
	      
	      <div align="center">
	      	<button type="button" class="btn btn-success" onclick="checkUserInfo();">登陆</button>
	      	<button type="button" class="btn btn-warning">忘记密码？</button>
	      	<button type="button" class="btn btn-info" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/toRegist.do'">没有账号？先注册 → </button>
	      </div>
	   </form>
	</div>
</body>
</html>