<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" >
<link rel="stylesheet" href="/uploadify/uploadify.css" >
<title>index</title>
<style type="text/css">
	/* .body{
		background-color: #D1D8DF;
	} */

	/* i = 1;  
    j = 1;  
    $(document).ready(function(){  
          
        $("#btn_add2").click(function(){  
            document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';  
              j = j + 1;  
        });  
    });   */
    
	
</style>
<script type="text/javascript">

	i = 1;  
	j = 1;  
	$(document).ready(function(){  
	      
	    $("#btn_add2").click(function(){  
	        document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';  
	          j = j + 1;  
	    });  
	    
	}); 
	

	//检查浏览器或页面关闭事件后，清除cookie，执行退出
	window.onbeforeunload = onbeforeunload_handler;   
    function onbeforeunload_handler(){   
        var onLineUserName = $("#onLineUserName").val();
        $.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '${pageContext.request.contextPath}/user/logout.do?'
					+ Math.random(),
			Cache : false,
			data : {
				onLineUserName:onLineUserName
			},
			dataType : 'json',
			success : function(data) {
			},
			error : function() {
			}
		}); 
    } 
    
    function sendMsg(){
    	var sender = $("#sender").val();
    	var receptor = $("#recipient-name").val();
    	var msg = $("#message-text").val();
    	//alert("sender="+sender+" msg="+msg);
    	$.ajax({
 			type : 'GET',
 			contentType : 'application/json',
 			url : '${pageContext.request.contextPath}/user/sendMsg.do?'
 					+ Math.random(),
 			Cache : false,
 			data : {
 				receptor:receptor,
 				msg:msg,
 				sender:sender
 			},
 			dataType : 'json',
 			success : function(data) {
 				if(data.status == 200){
 					$("#msgList").text("");
 					$("#msgList").text(data.newMsg);
 					/* $("#msgList").fadeIn(3000).slideUp("slow"); */
 					$("#msgList").animate({
 						speed:"slow",
 					      top:'+=100px',
 					     opacity:'0.5'
 					    });
 				}
 			},
 			error : function() {
 			}
 		});  
    }
    
    
   
    $('#myModal').modal({
    	backdrop: 'static', 
    	keyboard: false
    });
    
    
</script>
</head>
<body class="body" >
<div class="cover">
    <div class="navbar">
        <div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			   <div class="navbar-header" style="margin-left:50px">
			      <a class="navbar-brand" href="#">伶然</a>
			   </div>
			   <div>
			      <ul class="nav navbar-nav" style="margin-left:50px">
			         <li class="active"><a href="#">首页</a></li>
			         <li><a href="#">社区</a></li>
			         <li><a href="#">新鲜事</a></li>
			      </ul>
			   </div>
			   <div class="collapse navbar-collapse" id="navbar-ex-collapse" style="margin-right:50px">
		           <ul class="nav navbar-nav navbar-right">
		               <li>
		               	   <input id="onLineUserName" style="display: none" value="${onLine_userName }"/>
		                   <a href="#">${onLine_userName }</a>
		               </li>
		               <li>
		                   <a href="${pageContext.request.contextPath }/user/logout.do?onLineUserName=${onLine_userName}">退出</a>
		               </li>
		               <li>
		               	   <!-- <img alt="" src="/imgs/icons/glyphicons-31-pencil.jpg" style="cursor:pointer" onclick="openMsgBox();"> -->
		               	   <a data-toggle="modal" data-target="#msg_modal" data-whatever="@getbootstrap" style="cursor:pointer">弹一下</a>
		               </li>
		               <li>
		               		<a type="button" href="${pageContext.request.contextPath }/file/toUpload.do?uploaderName=${onLine_userName}"  data-toggle="modal" data-target="#myModal">
								点我上传
							</a>
		               </li>
		               <li>
		               		<a type="button" href="${pageContext.request.contextPath }/file/checkFiles.do?repoName=${onLine_userName}&uploaderName=${onLine_userName}"  data-toggle="modal" data-target="#myModal">
								查看私人库
							</a>
		               </li>
		               <li>
		               		<a type="button" href="${pageContext.request.contextPath }/file/checkFiles.do?repoName=commonRepo&uploaderName=${onLine_userName}"  data-toggle="modal" data-target="#myModal">
								查看公共库
							</a>
		               </li>
		               <%-- <li>
		               		<a type="button" href="${pageContext.request.contextPath }/file/toDownloadFiles.do?operatorName=${onLine_userName}"  data-toggle="modal" data-target="#myModal">
								文件下载
							</a>
		               </li> --%>
		           </ul>
		       </div>
			</nav>
		</div>
	</div>
</div>
	
	<div class="section">
	
	<!-- 文件上传实现一 -->
	<%-- <form action="${pageContext.request.contextPath }/user/fileUpload.do" enctype="multipart/form-data" method="post">
		<div id="newUpload2">
			<input name="file_1" type="file"/><button id="btn_add2"/>
		</div>
		<input type="submit" value="提交" />
	</form> --%>
	
	
	
	
	<!-- ======================================= -->
	
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png" class="img-responsive">
                    </div>
                    <div class="col-md-6">
                        <h1 class="text-primary">A title</h1>
                        <h3>A subtitle</h3>
                        <p id="msgList" style="position:absolute;">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
                            ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis
                            dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies
                            nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.
                            Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In
                            enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum
                            felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus
                            elementum semper nisi.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1 class="text-primary">A title</h1>
                        <h3>A subtitle</h3>
                        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
                            ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis
                            dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies
                            nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.
                            Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In
                            enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum
                            felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus
                            elementum semper nisi.</p>
                    </div>
                    <div class="col-md-6">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png" class="img-responsive">
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center text-primary">Team</h1>
                        <p class="text-center">We are a group of skilled individuals.</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
                        <h3 class="text-center">John Doe</h3>
                        <p class="text-center">Developer</p>
                    </div>
                    <div class="col-md-4">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
                        <h3 class="text-center">John Doe</h3>
                        <p class="text-center">Developer</p>
                    </div>
                    <div class="col-md-4">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
                        <h3 class="text-center">John Doe</h3>
                        <p class="text-center">Developer</p>
                    </div>
                </div>
            </div>
        </div>
        
    <!--msg_Modal -->
	<div class="modal fade" id="msg_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <!-- <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">input you msg</h4>
	      </div> -->
	      <div class="modal-body">
	         <form id="form_msg" action="${pageContext.request.contextPath }/user/sendMsg.do">
	          <input id="sender" name="sender" style="display: none" value="${onLine_userName }"/>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">Recipient:</label>
	            <input type="text" name="receptor" class="form-control" id="recipient-name" placeholder="@him/her,none for everybody">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">Message:</label>
	            <textarea class="form-control" id="message-text" name="msg"></textarea>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendMsg();">Send</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	
		<!-- 模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      
		    </div>
		  </div>
		</div>
        
</body>

</html>