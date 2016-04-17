<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/DateTools.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >
<title>userDetails</title>

<script type="text/javascript">
	/* function loadUserDetails(){
		var userId = $("#input_userId").val();
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '${pageContext.request.contextPath}/cms/user/checkUserDetail.do?'
					+ Math.random(),
			Cache : false,
			data : {
				id:userId
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					$("#myModalLabel").html("用户"+data.userInfo.userName);
					$("#user_id").html(data.userInfo.id);
					$("#user_registTime").html(data.userInfo.registTime);
					$("#user_currentLoginTime").html(data.userInfo.currentLoginTime);
					$("#user_lastLoginTime").html(data.userInfo.lastLoginTime);
					$("#user_totalOnlineTime").html(data.userInfo.totalOnlineTime);
					$("#user_status").html(data.userInfo.status);
					$("#user_bonus").html(data.userInfo.bonus);
					$("#user_level").html(data.userInfo.level);
				}
			},
			error : function() {
				//alert(data.failReason);
			}
		}); 
	}
	window.setTimeout(loadUserDetails(),200); */
	
	/* function processDate(){
		$("#td_registTime").html(processDateForDisplay($("#td_registTime").html()));
		$("#td_currentLoginTime").html(processDateForDisplay($("#td_currentLoginTime").html()));
		$("#td_lastLoginTime").html(processDateForDisplay($("#td_lastLoginTime").html()));
		$("#td_totalOnlineTime").html(processTotalOnlineTime($("#td_totalOnlineTime").html()));
	}
	window.setTimeout(processDate(),200); */
	
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
      <table class="table table-hover" id="tb_userInfo_allUser">
			<tr>
				<td>#</td>
				<td>注册时间</td>
				<td>上一次登陆时间</td>
				<td>本次登陆时间</td>
				<td>在线时长</td>
				<td>当前状态</td>
				<td>积分</td>
				<td>等级</td>
			</tr>
			<tr>
				<td id="td_id">${userInfo.id }</td>
				<td id="td_registTime">${userInfo.registTime4Display }</td>
				<td id="td_lastLoginTime">${userInfo.lastLoginTime4Display }</td>
				<td id="td_currentLoginTime">${userInfo.currentLoginTime4Display }</td>
				<td id="td_totalOnlineTime">${userInfo.totalOnlineTime4Display }</td>
				<td id="td_status">${userInfo.status }</td>
				<td id="td_bonus">${userInfo.bonus }</td>
				<td id="td_level">${userInfo.level }</td>
			</tr> 
  		</table>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      <button type="button" class="btn btn-primary">Modify</button>
    </div>
</body>
</html>