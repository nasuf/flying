<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >
<title>regist_page</title>

<!-- <style type="text/css">
	.btn{
		background-color:#222;
		border-color:#222;
	}
</style> -->

<script type="text/javascript">
	function submitForm(){
		var userName = $("#username_input").val();
		var password = $("#passwrod_input").val();
		document.getElementById("registForm").submit();
		/* if(userName == null || userName == "" || password == null || password == ""){
			alert("信息不完整，请重新输入");
		} else {
			
		} */
	}
	
	function checkUserName(value){
		var userNameValidation = null;
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '${pageContext.request.contextPath}/user/checkUserName.do?'
					+ Math.random(),
			Cache : false,
			data : {
				userName:value
			},
			dataType : 'json',
			success : function(data) {
				if (data.validated) {
					userNameValidation = "用户名可用";
					alert(userNameValidation);
					$("#username_input").css("color","green");
				} else {
					userNameValidation = "用户名不可用";
					alert(userNameValidation);
					$("#username_input").css("color","red");
				}
			},
			error : function() {
				//alert(data.failReason);
			}
		}); 
	}
	

</script>

</head>

<body>
	<nav class="navbar navbar-inverse" role="navigation">
	   <div class="navbar-header" style="margin-left:50px">
	      <a class="navbar-brand" href="#">伶然</a>
	   </div>
	   <div>
	      <ul class="nav navbar-nav" style="margin-left:50px">
	         <li><a href="#">首页</a></li>
	         <li><a href="${pageContext.request.contextPath}/user/toLogin.do">登陆</a></li>
	         <li class="active"><a href="#">注册</a></li>
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
	
	<div style="padding: 100px 100px 10px;" id="userInfo_div"  align="center">
	   <form id="registForm" class="bs-example bs-example-form" role="form" action="${pageContext.request.contextPath}/user/regist.do">
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">用户名</span>
	         <input name="userName" id="username_input" type="text" class="form-control" placeholder="请输入用户名" onblur="checkUserName(this.value)">
	      </div>
	      <br>
	
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">密码</span>
	         <input name="password" id="password_input" type="text" class="form-control" placeholder="请输入密码,6~12位">
	      </div>
	      <br>
	      
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">性别</span>
	         <div class="btn-group" data-toggle="buttons">
			  <label class="btn btn-primary active">
			    <input type="radio" name="gender" id="option1" autocomplete="off" value="1"> 男
			  </label>
			  <label class="btn btn-primary">
			    <input type="radio" name="gender" id="option2" autocomplete="off" value="0"> 女
			  </label>
			</div>
	      </div>
	      <br>
	      
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">年龄</span>
	         <input name="age" id="password_input" type="text" class="form-control" placeholder="请输入年龄">
	      </div>
	      <br>
	      
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">电话</span>
	         <input name="phone" id="password_input" type="text" class="form-control" placeholder="请输入电话">
	      </div>
	      <br>
	      
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">城市</span>
	         <input name="city" id="password_input" type="text" class="form-control" placeholder="请输入所在城市">
	      </div>
	      <br>
	      
	      <div class="input-group col-xs-3">
	         <span class="input-group-addon">学历</span>
	         <input name="degree" id="password_input" type="text" class="form-control" placeholder="请输入学历">
	      </div>
	      <br>
	      
	      <div align="center">
	      	<button type="button" class="btn btn-success" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/toLogin.do'">← 已注册？直接登陆</button>
	      	<button type="button" class="btn btn-info" onclick="submitForm();">注册</button>
	      	<h1><font color="green">${regist_result }</font></h1>
	      </div>
	   </form>
	</div>
	
</body>
</html>