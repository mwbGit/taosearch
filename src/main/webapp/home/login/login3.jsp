<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
<title>大兴区知识产权联合执法机制及信息系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/static/css/signin.css" rel="stylesheet">
<link href="${ctx }/static/bootstrap-3.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx }/static/jquery-1.11.1/jquery-1.11.1.min.js"></script>
<script src="${ctx }/static/bootstrap/2.1.1/js/jquery-html5Validate.js"></script>
<script type="text/javascript">
var $ctx ="${ctx }";
	self.moveTo(0, 0);
	self.resizeTo(screen.availWidth, screen.availHeight);

	$(document).ready(function() {
		$("#login-form").html5Validate(function() {
			login();
		});
	});
	
	function login(){
		$.ajax({
			url : $ctx + "/login/doLogin?" + $("#login-form").serialize(),
			type : "post",
			async : false,
			success : function(text){
				if(text){
					window.location.href= $ctx + "/home/index/index.jsp";
				}else{
					alert("用户名或密码错误");
				}
			},
			error : function(){
				alert("error");
			}
		});
	}
</script>
</head>
<body>
	<div class="container">
		<form class="form-signin" id='login-form' >
			<span style="text-align:center"><h2>请登陆</h2></span><input class="form-control" 
				value="" id="login_id" name="login_id" placeholder="请输入用户名"
				value=""><input class="form-control"  type="password"
				value="" id="user_password" name="user_password" placeholder="请输入密码"
				value=""><label class="checkbox"></label>
			<button id="sub" class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>
	</div>

</body>
</html>
