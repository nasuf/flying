<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	#menu{
		width: 15%;
		height: 100%;
		overflow:auto;
		float: left;
	}
	
	#main_frame{
		width: 85%;
		height: 100%;
		overflow:auto;
		float: right;
	}
</style>

<script type="text/javascript">
	function changeIframe(id){
		if(id == "sys_user"){
			$("#main_frame").attr("src","${pageContext.request.contextPath }/cms/user/allUsers");
		}
	}
	
	window.onload = function(){
		var main = document.getElementById("main_frame");
		var thisHeight = $(document).height()+300;
		//alert(thisHeight);
		$(main).height(thisHeight);
		};
</script>

</head>
<body>
	<div class="navbar navbar-duomi navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" id="logo">伶然后台管理系统
                </a>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row" >
        
            <div class="col-md-2" id="menu">
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                    <li class="active">
                        <a href="#">
                            <i class="glyphicon glyphicon-th-large"></i>首页         
                        </a>
                    </li>
                    <li>
                        <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-cog"></i>系统管理
                            <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                        </a>
                        <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="#" id="sys_user" onclick="changeIframe(this.id)"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-credit-card"></i> menu2       
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-globe"></i>menu3
                            <span class="label label-warning pull-right">0</span>
                        </a>
                    </li>
 
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-calendar"></i>menu4
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-fire"></i>关于系统
                        </a>
                    </li>
                </ul>
            </div>
              <iframe id="main_frame" class="col-md-10" frameborder="0" 
              onload="javascript:this.height=this.contentWindow.document.body.scrollHeight+30;">
	               		 主窗口
	        </iframe>
        </div>
	      
    </div>
</body>
</html>