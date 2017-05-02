<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>
<c:set var="isAdmin" value="${sessionScope.userAuthorization.showUser}"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>淘客信息采集系统</title>
<link href="${ctx }/static/bootstrap-3.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/static/bootstrap-3.3.0/dist/css/dashbord.css" rel="stylesheet">
<link href="${ctx }/static/bootstrap-3.3.0/dist/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${ctx }/static/bootstrap/3.0.2/bootstrap-expand.css" rel="stylesheet">
<link href="${ctx }/static/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx }/static/ratingBar/css/ratingBar.css" rel="stylesheet">
<link href="${ctx }/static/bootstrap/2.1.1/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/static/autosuggest/css/autosuggest.css">
<link rel="stylesheet" href="${ctx }/static/bootstrap-select/css/bootstrap-select.css">
<script src="${ctx }/static/jquery-1.11.1/jquery-1.11.1.min.js"></script>
<script src="${ctx }/static/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script src="${ctx }/static/bootstrap-3.3.0/dist/js/bootstrap.min.js"></script>
<script src="${ctx }/static/bootstrap-3.3.0/js/carousel.js"></script>
<script src="${ctx }/static/bootstrap/2.1.1/js/jquery.bootstrap.js"></script>
<script src="${ctx }/static/bootstrap/2.1.1/js/bootstrap-paginator.min.js"></script>
<script src="${ctx }/static/bootstrap/2.1.1/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx }/static/bootstrap/2.1.1/js/jquery-html5Validate.js"></script>
<script src="${ctx }/static/ratingBar/jquery.barrating.js"></script>
<script src="${ctx }/static/js/echarts.js"></script>
<script src="${ctx }/static/autosuggest/js/autosuggest.js"></script>
<script src="${ctx }/static/bootstrap-select/js/bootstrap-select.js"></script>
<script src="${ctx }/static/form/jquery.form.js"></script>
<%-- <script src="${ctx }/static/ext/ext-all.js"></script> --%>
<%-- <script src="${ctx }/static/require/require.js" defer async="async"></script> --%>
<!--[if lt IE 9]>
<script src="${ctx }/static/js/ie8-responsesive-file-warning.js"></script>
<![endif]-->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="${ctx }/static/js/respond.min.js"></script>
<![endif]-->

</head>
<body>
	<%@include file="../../dialogs/alldialogs.jsp"%>
	<script type="text/javascript">
		var $ctx = "${ctx }";
		var book_theme_flag = GetRequest().book_theme_flag;
		var loadBooksType = 0;
		$(function() {
			_zscq();
		})
		function _zscq() {
			$('#_syjc').attr("class", "");
			$('#_zygl').attr("class", "");
			$('#_tjcx').attr("class", "");
			$('#_yhgl').attr("class", "");
			$('#_zscq').attr("class", "active");
			$('#showId').load("${ctx }/home/view/index2.jsp");
			$('#li_name').html("商品列表");
		}
		function _tjcx() {
			$('#_zygl').attr("class", "");
			$('#_syjc').attr("class", "");
			$('#_tjcx').attr("class", "active");
			$('#_yhgl').attr("class", "");
			$('#_zscq').attr("class", "");
			$('#showId').load("${ctx }/home/view/tjcx.jsp");
			$('#li_name').html("添加商品");
		}
		function _syjc() {
			$('#_zygl').attr("class", "");
			$('#_syjc').attr("class", "active");
			$('#_tjcx').attr("class", "");
			$('#_yhgl').attr("class", "");
			$('#_zscq').attr("class", "");
			$('#showId').load("${ctx }/home/view/financialStatements.jsp");
			$('#li_name').html("财务管理");
		}
		function _yhgl() {
			$('#_zygl').attr("class", "");
			$('#_syjc').attr("class", "");
			$('#_tjcx').attr("class", "");
			$('#_yhgl').attr("class", "active");
			$('#_zscq').attr("class", "");
			$('#showId').load("${ctx }/home/view/yhgl.jsp");
			$('#li_name').html("用户管理");
		}
		function _zygl() {
			$('#_syjc').attr("class", "");
			$('#_tjcx').attr("class", "");
			$('#_yhgl').attr("class", "");
			$('#_zscq').attr("class", "");
			$('#_zygl').attr("class", "active");
			$('#showId').load("${ctx }/home/view/zygl.jsp");
			$('#li_name').html("资源管理");
		}
		//获取url中的参数
		function GetRequest() {
			var url = location.search; //获取url中"?"符后的字串
			var theRequest = new Object();
			if (url.indexOf("?") != -1) {
				var str = url.substr(1);
				strs = str.split("&");
				for (var i = 0; i < strs.length; i++) {
					theRequest[strs[i].split("=")[0]] = unescape(strs[i]
							.split("=")[1]);
				}
			}
			return theRequest;
		}
	</script>
	<nav class="navbar  navbar-fixed-top " style="background-color: #2b669a;">
		<div>
			<div>
				<a class="navbar-brand" style="color: white; cursor: default" href="#"><b>淘客信息采集系统</b></a> <a class="navbar-brand" href="../login/login.jsp" style="color: white; cursor: pointer; float: right"><img title="注销登录" style="width: 25px; height: 25px" src="${ctx }/static/image/logout.png"></a>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class=" sidebar col-sm-2 col-md-1">
				<ul class="nav nav-sidebar">
					<li id="_zscq"><a href="#" onclick="_zscq()"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;商品列表</a></li>
					<li id="_tjcx"><a href="#" onclick="_tjcx()"><i class="glyphicon glyphicon-pencil"></i>&nbsp;&nbsp;&nbsp;添加商品</a></li>
					<li id="_syjc"><a href="#" onclick="_syjc()"><i class="glyphicon glyphicon-bookmark"></i>&nbsp;&nbsp;&nbsp;财务管理</a></li>
					<c:if test="${ua=='3'}">
					<c:if test="${isAdmin}">
					<li id="_yhgl"><a href="#" onclick="_yhgl()"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;&nbsp;用户管理</a></li>
					</c:if>
					</c:if>
					<!--  <li id="_zygl"><a href="#" onclick="_zygl()"><i class="glyphicon glyphicon-globe"></i>&nbsp;&nbsp;&nbsp;资源管理</a></li>  -->
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-1 main">
				<div class="row">
					<ol class="breadcrumb">
						<li><i class="glyphicon glyphicon-home"></i></li>
						<li class="active" id="li_name"></li>
					</ol>
				</div>
				<div id="showId"></div>
			</div>
		</div>
	</div>

</body>
</html>