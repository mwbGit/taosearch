<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx }/static/bootstrap/3.0.2/bootstrap.min.css"
	rel="stylesheet">
<link href="${ctx }/static/bootstrap/3.0.2/bootstrap-theme.min.css"
	rel="stylesheet">
<script src="${ctx }/static/jquery/1.8.0/jquery.min.js"></script>
<script src="${ctx }/static/bootstrap/3.0.2/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>

	<div id="modal_warning" class="modal fade in" tabindex="-1"
		role="dialog" aria-hidden="false">
		<div class="modal-dialog modal-alert modal-login">
			<div class="modal-content col-lg-4 col-lg-offset-3">
				<div class="modal-header warning">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h1>警告</h1>
				</div>
				<div class="modal-body info" id="alert_dialog_warning"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="button_warning_sure">确定</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>