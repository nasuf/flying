<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/DateTools.js"></script>
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
	/* function processDate(){
		$("#td_all_registTime").html(processDateForDisplay($("#td_all_registTime").html()));
		$("#td_all_currentLoginTime").html(processDateForDisplay($("#td_all_currentLoginTime").html()));
		$("#td_all_lastLoginTime").html(processDateForDisplay($("#td_all_lastLoginTime").html()));
		$("#td_all_totalOnlineTime").html(processTotalOnlineTime($("#td_all_totalOnlineTime").html()));
		
		$("#td_onLine_registTime").html(processDateForDisplay($("#td_onLine_registTime").html()));
		$("#td_onLine_currentLoginTime").html(processDateForDisplay($("#td_onLine_currentLoginTime").html()));
		$("#td_onLine_lastLoginTime").html(processDateForDisplay($("#td_onLine_lastLoginTime").html()));
		$("#td_onLine_totalOnlineTime").html(processTotalOnlineTime($("#td_onLine_totalOnlineTime").html()));
		
		$("#td_his_registTime").html(processDateForDisplay($("#td_his_registTime").html()));
		$("#td_his_lastLoginTime").html(processDateForDisplay($("#td_his_lastLoginTime").html()));
		$("#td_his_totalOnlineTime").html(processTotalOnlineTime($("#td_his_totalOnlineTime").html()));
		
	} */
	
	/* $('#myModal').modal().css({
	    width: 'auto',
	    'margin-left': function () {
	       return -($(this).width() / 2);
	   }
	});
	 */
</script>

</head>
<body>
	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		<!-- ==========所有用户信息=========================== -->
	  <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingOne">
	      <h4 class="panel-title">
	        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	          	所有用户信息
	        </a>
	      </h4>
	    </div>
	    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
	      <div class="panel-body table-responsive">
	        <table class="table table-hover" id="tb_userInfo_allUser">
  				<tr>
  					<td>#</td>
  					<td>用户名</td>
  					<td>注册时间</td>
  					<td>本次登陆时间</td>
  					<td>上一次登陆时间</td>
  					<td>总在线时长</td>
  					<td>当前状态</td>
  					<td>积分</td>
  					<td>等级</td>
  					<td>操作</td>
  					<td>他/她的文章</td>
  				</tr>
  				<c:forEach var="user" items="${userList}">
  					<tr>
  						<td>${user.id }</td>
  						<td>${user.userName }</td>
  						<td>${user.registTime4Display }</td>
  						<td>${user.currentLoginTime4Display }</td>
  						<td>${user.lastLoginTime4Display }</td>
  						<td>${user.totalOnlineTime4Display }</td>
  						<c:if test="${user.status == 1 }">
  							<td><font color="green">在线</font></td>
  						</c:if>
  						<c:if test="${user.status == 0 }">
  							<td><font color="gray">离线</font></td>
  						</c:if>
  						<c:if test="${user.status == -1 }">
  							<td><font color="red">冻结</font></td>
  						</c:if>
  						<c:if test="${user.status == -2 }">
  							<td><font color="red">注销</font></td>
  						</c:if>
  						<td>${user.bonus }</td>
  						<td>${user.level }</td>
  						<td>
  							<a type="button" href="${pageContext.request.contextPath }/cms/user/userDetails.do?id=${user.id}" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
  								查看	
							</a>
						</td>
  						<td>
  							<a type="button" href="${pageContext.request.contextPath }/cms/user/userEssay.do?id=${user.id}" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
  								浏览	
							</a>
						</td>
  					</tr>
  				</c:forEach>
			</table>
	      </div>
	    </div>
	  </div>
	  <!-- ============当前在线用户========================= -->
	  <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingTwo">
	      <h4 class="panel-title">
	        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	          	当前在线用户
	        </a>
	      </h4>
	    </div>
	    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
	      <div class="panel-body table-responsive">
	        <table class="table table-hover" id="tb_userInfo_online">
  				<tr>
  					<td>#</td>
  					<td>用户名</td>
  					<td>注册时间</td>
  					<td>本次登陆时间</td>
  					<td>上一次登陆时间</td>
  					<td>在线时长</td>
  					<td>当前状态</td>
  					<td>积分</td>
  					<td>等级</td>
  					<td>操作</td>
  					<td>他/她的文</td>
  				</tr>
  				<c:forEach var="user" items="${userList}">
  					<c:if test="${user.status == 1 }">
  						<tr>
	  						<td>${user.id }</td>
	  						<td>${user.userName }</td>
	  						<td>${user.registTime4Display }</td>
	  						<td>${user.currentLoginTime4Display }</td>
	  						<td>${user.lastLoginTime4Display }</td>
	  						<td>${user.totalOnlineTime4Display }</td>
	  						<td><font color="green">在线</font></td>
	  						<td>${user.bonus }</td>
	  						<td>${user.level }</td>
	  						<td>
		  						<a type="button" href="${pageContext.request.contextPath }/cms/user/userDetails.do?id=${user.id}" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
	  								查看	
								</a>
							</td>
							<td>
	  							<a type="button" href="${pageContext.request.contextPath }/cms/user/userEssay.do?id=${user.id}" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
	  								浏览	
								</a>
							</td>
	  					</tr>
  					</c:if>
  					
  				</c:forEach>
			</table>
	      </div>
	    </div>
	  </div>
	  <!-- ===================================== -->
	  <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingThree">
	      <h4 class="panel-title">
	        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
	          	历史用户
	        </a>
	      </h4>
	    </div>
	    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
	      <div class="panel-body table-responsive">
	         <table class="table table-hover" id="tb_userInfo_logoff">
  				<tr>
  					<td>#</td>
  					<td>用户名</td>
  					<td>注册时间</td>
  					<td>上一次登陆时间</td>
  					<td>在线时长</td>
  					<td>当前状态</td>
  					<td>积分</td>
  					<td>等级</td>
  					<td>操作</td>
  					<td>他/她的文</td>
  				</tr>
  				<c:forEach var="user" items="${userList}">
  					<c:if test="${user.status == -2 }">
  						<tr>
	  						<td>${user.id }</td>
	  						<td>${user.userName }</td>
	  						<td>${user.registTime4Display }</td>
	  						<td>${user.lastLoginTime4Display }</td>
	  						<td>${user.totalOnlineTime4Display }</td>
	  						<td><font color="red">注销</font></td>
	  						<td>${user.bonus }</td>
	  						<td>${user.level }</td>
	  						<td>
	  							<a type="button" href="${pageContext.request.contextPath }/cms/user/userDetails.do?id=${user.id}" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
	  								查看	
								</a>
							</td>
							<td>
	  							<a type="button" href="${pageContext.request.contextPath }/cms/user/userEssay.do?id=${user.id}" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
	  								浏览	
								</a>
							</td>
	  					</tr>
  					</c:if>
  				</c:forEach>
			</table>
	      </div>
	    </div>
	  </div>
	  <!-- ===================================== -->
		<!-- 模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>