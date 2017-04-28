<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
<title>员工后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/static/css/login.css" rel="stylesheet" type="text/css" />
<%-- <link href="${ctx }/static/css/signin.css" rel="stylesheet"> --%>
<link href="${ctx }/static/bootstrap-3.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx }/static/jquery-1.11.1/jquery-1.11.1.min.js"></script>
<script src="${ctx }/static/bootstrap/2.1.1/js/jquery-html5Validate.js"></script>
<script type="text/javascript">
var $ctx ="${ctx }";
// 	self.moveTo(0, 0);
// 	self.resizeTo(screen.availWidth, screen.availHeight);
	$(document).ready(function() {
		$("#login-form").html5Validate(function() {
			login();
		});
	});
	function login(){
		var data= $("#login-form").serialize();
		$.ajax({
			url : $ctx + "/login/doLogin",
			type : "post",
			data:data,
			async : false,
			success : function(text){
				console.log(text);
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

</head>

<body id="page-login">
    <header class="logo">
		<img src="${ctx }/static/image/login/logo.jpg" alt=""
			 style="width: 300px;height: 100px">
	</header>
    <section class="login-wrap">
        <div class="form-wrap">
            <form id="login-form">
                <label>
                    <input name="login_id" type="text" required placeholder="用户名"  class="inputTxt" id="login_id">
                </label>
                <label>
                    <input type="password" placeholder="密码"  class="inputTxt" id="user_password" name="user_password"/>
            </label>
                <input type="submit" value="登录系统" id="sub">
            </form>
        </div>
    </section>
    <footer class="copyright">
        &copy;CopyRight 2017 员工后台管理系统
    </footer>
</body>
</html>
