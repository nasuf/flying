<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >
<title>entrance</title>

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
					alert("登陆成功，即将跳转到首页");
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
	         <li class="active"><a href="${pageContext.request.contextPath}/user/toLogin.do">登陆</a></li>
	         <li><a href="${pageContext.request.contextPath}/user/toRegist.do">注册</a></li>
	      </ul>
	   </div>
	</nav>
	
	
	<!-- ============================ -->
	<div align="center">
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#login_modal">
		  login
		</button>
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#regist_modal">
		  regist
		</button>
	</div>
	
	<!--login_Modal -->
	<div class="modal fade" id="login_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">please input your information</h4>
	      </div>
	      <div class="modal-body">
	        <div style="padding: 100px 100px 10px;" id="userInfo_div"  align="center">
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
			      
			   </form>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="checkUserInfo();">enter</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!--regist_Modal -->
	<div class="modal fade" id="regist_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">please input your information</h4>
	      </div>
	      <div class="modal-body">
	        <div style="padding: 100px 100px 10px;" id="userInfo_div"  align="center">
			   <form id="registForm" class="bs-example bs-example-form" role="form" action="${pageContext.request.contextPath}/user/doRegist.do" method="post">
			      <div class="input-group col-xs-3">
			         <span class="input-group-addon">用户名</span>
			         <input name="userName" id="username_input" type="text" style="width:200px" class="form-control" placeholder="请输入用户名" onblur="checkUserName(this.value)">
			      </div>
			      <br>
			
			      <div class="input-group col-xs-3">
			         <span class="input-group-addon">密码</span>
			         <input name="password" id="password_input" type="password" style="width:200px" class="form-control" placeholder="请输入密码,6~12位">
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
			         <input name="age" id="age_input" type="text" style="width:200px" class="form-control" placeholder="请输入年龄">
			      </div>
			      <br>
			      
			      <div class="input-group col-xs-3">
			         <span class="input-group-addon">电话</span>
			         <input name="phone" id="phone_input" type="text" style="width:200px" class="form-control" placeholder="请输入电话">
			      </div>
			      <br>
			      
			      <div class="input-group col-xs-3">
			         <span class="input-group-addon">城市</span>
			         <input name="city" id="city_input" type="text" style="width:200px" class="form-control" placeholder="请输入所在城市">
			      </div>
			      <br>
			      
			      <div class="input-group col-xs-3">
			         <span class="input-group-addon">学历</span>
			         <input name="degree" id="degree_input" type="text" style="width:200px" class="form-control" placeholder="请输入学历">
			      </div>
			      <br>
			      
			   </form>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="submitForm();">enter</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
</body>
</html>