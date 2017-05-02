<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
		$('#datetime_occur_time').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});
	});
</script>
<!-- 提示框 -->
<!-- 危险提示-->
<div id="modal_danger" class="modal fade" tabindex="-1" role="dialog" aria-hidden="false" style="display: none">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content">
			<div class="modal-header danger">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h1>警报</h1>
			</div>
			<div class="modal-body" id="alert_dialog_danger"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 信息提示 -->
<div id="modal_info" class="modal fade" tabindex="-1" role="dialog" aria-hidden="false" style="display: none">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content">
			<div class="modal-header info">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h1>信息</h1>
			</div>
			<div class="modal-body info" id="alert_dialog_info"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 成功提示 -->
<div id="modal_success" class="modal fade" tabindex="-1" role="dialog" aria-hidden="false" style="display: none">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h1 style="color: white">
					<b>成功</b>
				</h1>
			</div>
			<div class="modal-body info" id="alert_dialog_success"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 警告提示 -->
<div class="container-fluid">
	<div id="modal_warning" class="modal fade" tabindex="-1" role="dialog" aria-hidden="false" style="display: none;">
		<div class="modal-dialog modal-alert modal-login">
			<div class="modal-content ">
				<div class="modal-header warning">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h1>警告</h1>
				</div>
				<div class="modal-body info" id="alert_dialog_warning"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 基本的提示 -->
<div id="modal_primary" class="modal fade" tabindex="-1" role="dialog" aria-hidden="false" style="display: none">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h1>提示</h1>
			</div>
			<div class="modal-body info" id="alert_dialog_primary"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="button_primary_sure">是</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">否</button>
			</div>
		</div>
	</div>
</div>

<!-- 数据审批窗口 -->
<div class="modal fade" id="approvalCaseModal" tabindex="-1" role="dialog" aria-labelledby="approvalCaseModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>数据处理</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="approvalCase_form" method="post" enctype="multipart/form-data" action="#">
					<input id="case_id" name="case_id" type="hidden" />
					<table class="table">
						<tr>
							<td>检查对象：</td>
							<td colspan='3'><input id="object_name" name="object_name" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>经营位置：</td>
							<td colspan='3'><input id="case_pos_desc" name="object_pos" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>检查品类：</td>
							<td><input id="case_btype_name" name="case_btype_name" class="form-control" readonly></td>
							<td>检查细类：</td>
							<td><input id="case_stype_name" name="case_stype_name" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>专利号：</td>
							<td><textarea id="case_desc" name="case_desc" class="form-control" readonly></textarea></td>
							<td>附件信息：</td>
							<td>
								<!-- 								<ul id="attachment" class="list-group"></ul> --> <span>共<b id="attachmentnumber"></b>张
							</span>
								<div id="myCarousel" class="carousel slide" style="max-width: 130px; max-height: 80px; text-align: center; margin-left: 30px">
									<div class="carousel-inner" id="attachment"></div>
									<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a> <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
								</div>
							</td>
						</tr>
						<tr>
							<td>联系人：</td>
							<td><input id="transferman_id" name="contact_name" class="form-control" readonly /></td>
							<td>联系电话：</td>
							<td><input id="rptor_contact_phone" name="contact_tel" class="form-control" readonly />
						</tr>
						<tr>
							<td>鉴别：</td>
							<td><select name="dic_item_id" id="caseregister_result_id" class="form-control" onchange="selectchange('caseregister_result_id','caseregister_desc')">
							</select></td>
							<td>处置结果</td>
							<td><textarea id="caseregister_desc" name="dic_result" class="form-control"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-primary" id="approvalButton" onclick="approvalCase()">提交</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 知识产权查看详情窗口 -->
<div class="modal fade" id="showCaseModal" tabindex="-1" role="dialog" aria-labelledby="showCaseModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>数据详细信息</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="showCase_form" method="post" enctype="multipart/form-data" action="/booksmanager/booksController/addBooks">
					<input id="showCase_case_id" name="case_id" type="hidden" />
					<table class="table">
						<tr>
							<td>案卷号：</td>
							<td><input id="showCaseForcase_code" name="case_code" class="form-control" readonly></td>
							<td>产品名称：</td>
							<td><input id="showCaseForcase_name" name="case_name" class="form-control"></td>
						</tr>
						<tr>
							<td>检查对象：</td>
							<td colspan='3'><input id="showCaseForobject_name" name="object_name" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>经营位置：</td>
							<td colspan='3'><input id="showCaseForcase_pos_desc" name="object_pos" class="form-control"></td>
						</tr>
						<tr>
							<td>生产厂家：</td>
							<td><input id="showCaseFordefault_rep_depart_desc" name="manuf_name" class="form-control"></td>
							<td>出厂日期：</td>
							<td>
								<div id="datetime_occur_time" class="input-append date">
									<input id="showCaseForoccur_time" name="manuf_date" type="text" class="add-on form-control" data-format="yyyy-MM-dd hh:mm:ss">
								</div>
							</td>
						</tr>
						<tr>
							<td>检查品类：</td>
							<td><select id="showCaseForcase_btype_id" name="case_btype_id" class="form-control" onchange="initType(22)"></select></td>
							<td>检查细类：</td>
							<td><select id="showCaseForcase_stype_id" name="case_stype_id" class="form-control"></select></td>
						</tr>
						<tr>
							<td>上报人：</td>
							<td><input id="showCaseForrpt_user_name" name="rpt_user_name" class="form-control" readonly></td>
							<td>检查日期</td>
							<td><input id="showCaseForrecord_time" name="rpt_time" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>专利号：</td>
							<td><textarea id="showCaseForcase_desc" name="case_desc" class="form-control"></textarea></td>
							<td>附件信息：</td>
							<td><span>共<b id="showCaseForattachmentnumber"></b>张
							</span>
								<div id="showCaseFormyCarousel" class="carousel slide" style="max-width: 130px; max-height: 80px; text-align: center; margin-left: 30px">
									<div class="carousel-inner" id="showCaseForattachment"></div>
									<!-- 轮播（Carousel）导航 -->
									<a class="carousel-control left" href="#showCaseFormyCarousel" data-slide="prev">&lsaquo;</a> <a class="carousel-control right" href="#showCaseFormyCarousel" data-slide="next">&rsaquo;</a>
								</div></td>
						</tr>
						<tr>
							<td>联系人：</td>
							<td><input id="showCaseFortransferman_id" name="contact_name" class="form-control" /></td>
							<td>联系电话：</td>
							<td><input id="showCaseForrptor_contact_phone" name="contact_tel" class="form-control" />
						</tr>
						<tr>
							<td>鉴别：</td>
							<td><select name="dic_item_id" id="showCaseForcaseregister_result_id" class="form-control" onchange="selectchange('showCaseForcaseregister_result_id','showCaseForcaseregister_desc')">
							</select></td>
							<td>处置结果</td>
							<td><textarea id="showCaseForcaseregister_desc" name="dic_result" class="form-control"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-primary" id="showButton" onclick="updateCase()">保存</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 食盐检查查看详情窗口 -->
<div class="modal fade" id="showSYJCCaseModal" tabindex="-1" role="dialog" aria-labelledby="showSYJCCaseModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>数据详细信息</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="showSYJCCase_form" method="post" enctype="multipart/form-data" action="/booksmanager/booksController/addBooks">
					<input id="showSYJCCase_case_id" name="case_id" type="hidden" />
					<table class="table">
						<tr>
							<td>案卷号：</td>
							<td colspan='3'><input id="showSYJCCaseForcase_code" name="case_code" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>检查对象：</td>
							<td colspan='3'><input id="showSYJCCaseForobject_name" name="object_name" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>经营位置：</td>
							<td colspan='3'><input id="showSYJCCaseForcase_pos_desc" name="object_pos" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>罚没斤数：</td>
							<td><input id="showSYJCCaseForcase_level_id" name="num" class="form-control" readonly></td>
							<td>罚款额：</td>
							<td><input id="showSYJCCaseFordamg_level_id" name="amount" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>检查人员：</td>
							<td><input id="showSYJCCaseForrpt_user_name" name="rpt_user_name" class="form-control" readonly></td>
							<td>检查日期</td>
							<td><input id="showSYJCCaseForrecord_time" name="rpt_time" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><textarea id="showSYJCCaseForremark" name="remark" class="form-control"></textarea></td>
							<td>附件信息：</td>
							<td><span>共<b id="showSYJCCaseForattachmentnumber"></b>张
							</span>
								<div id="showSYJCCaseFormyCarousel" class="carousel slide" style="max-width: 130px; max-height: 80px; text-align: center; margin-left: 30px">
									<div class="carousel-inner" id="showSYJCCaseForattachment"></div>
									<!-- 轮播（Carousel）导航 -->
									<a class="carousel-control left" href="#showSYJCCaseFormyCarousel" data-slide="prev">&lsaquo;</a> <a class="carousel-control right" href="#showSYJCCaseFormyCarousel" data-slide="next">&rsaquo;</a>
								</div></td>
						</tr>
						<tr>
							<td>联系人：</td>
							<td><input id="showSYJCCaseFortransferman_id" name="contact_name" class="form-control" readonly /></td>
							<td>联系电话：</td>
							<td><input id="showSYJCCaseForrptor_contact_phone" name="contact_tel" class="form-control" readonly />
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 用户管理账号详情  -->
<div class="modal fade" id="showUserModal" tabindex="-1" role="dialog" aria-labelledby="showUserModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>用户详细信息</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="showUser_form" method="post" enctype="multipart/form-data" action="/booksmanager/booksController/addBooks">
					<input id="show_user_id" name="user_id" type="hidden" />
					<table class="table">
						<tr>
							<td>用户名：</td>
							<td><input id="showUserForusername" name="username" class="form-control" readonly></td>
						</tr>
						<tr>
							<td>联系电话：</td>
							<td><input id="showUserFortel" name="tel" class="form-control"></td>
						</tr>
						<tr>
							<td>所在小组：</td>
							<td><select id="showUserForteam_id" name="team_id" class="form-control"></select></td>
						</tr>
						<tr>
							<td>设置角色：</td>
							<td><select id="showUserForadmin" name="admin" class="form-control">
								<option class="form-control" value="1">录单员</option>
								<option class="form-control" value="0">管理员</option>
								<option class="form-control" value="4">审核员</option>
								<option class="form-control" value="5">财务</option>
							</select></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-primary" id="showUserButton" onclick="updateUser()">保存</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 用户管理账号新增 -->
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>新增用户</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="addUser_form" method="post" enctype="multipart/form-data" action="#">
					<table class="table">
						<tr>
							<td>用户名：</td>
							<td>
								<div id="userName_div">
									<input id="addUserForusername" name="username" onblur="checkUserName()" class="form-control" placeholder="用户名不许为空，不允许重复"><span id="userName_span"></span>
								</div> <span style="color: #cc0001" id="userName_err_span"></span>
							</td>
						</tr>
						<tr>
							<td>联系电话：</td>
							<td><input id="addUserFortel" name="tel" class="form-control"></td>
						</tr>
						<tr>
							<td>所在小组：</td>
							<td><select id="addUserForteam_id" name="team_id" class="form-control"></select></td>
						</tr>
						<tr>
							<td>设置角色：</td>
							<td><select id="showUserForadmin" name="admin" class="form-control">
								<option class="form-control" value="1">录单员</option>
								<option class="form-control" value="0">管理员</option>
								<option class="form-control" value="4">审核员</option>
								<option class="form-control" value="5">财务</option>
							</select></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-primary" id="addUserButton" onclick="saveUser()" disabled>提交</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 用户小组详情  -->
<div class="modal fade" id="showTeamModal" tabindex="-1" role="dialog" aria-labelledby="showTeamModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>小组详细信息</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="showTeam_form" method="post" enctype="multipart/form-data" action="/booksmanager/booksController/addBooks">
					<input id="show_team_id" name="team_id" type="hidden" />
					<table class="table">
						<tr>
							<td>小组名称：</td>
							<td><input id="showTeamForteam_name" name="team_name" class="form-control" readonly></td>
						</tr>
						<div class="form-inline" id="team_leader_div">
							<label for="team_leader_select" class="form-control-static">小组组长：</label> <select name="team_leader" id="team_leader_select" class="selectpicker show-tick form-control" data-live-search="true">
							</select>
						</div>
						<tr>
							<td>小组备注：</td>
							<td><textarea id="showTeamForteam_remark" name="team_remark" class="form-control"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-primary" id="showUserButton" onclick="updateTeam()">保存</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 用户小组新增 -->
<div class="modal fade" id="addTeamModal" tabindex="-1" role="dialog" aria-labelledby="addTeamModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>新增小组</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="addTeam_form" method="post" enctype="multipart/form-data" action="#">
					<table class="table">
						<tr>
							<td>小组名称：</td>
							<td>
								<div id="team_name_div">
									<input id="addTeamForteam_name" name="team_name" onblur="checkTeamName()" class="form-control" placeholder="小组名不许为空，不允许重复"><span id="team_name_span"></span>
								</div> <span style="color: #cc0001" id="team_name_err_span"></span>
							</td>
						</tr>
						<tr>
							<td>小组备注：</td>
							<td><textarea id="addTeamForteam_remark" name="team_remark" class="form-control"></textarea></td>
						</tr>
						<div class="form-inline" id="add_team_leader_div">
							<label for="add_team_leader_select" class="form-control-static">小组组长：</label> <select name="team_leader" id="add_team_leader_select" class="selectpicker show-tick form-control" data-live-search="true">
							</select>
						</div>
						<tr>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-primary" id="addTeamButton" onclick="saveTeam()" disabled>提交</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 修改用户密码 -->
<div class="modal fade" id="updateUserPWDModal" tabindex="-1" role="dialog" aria-labelledby="updateUserPWDModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-alert modal-login">
		<div class="modal-content" style="width: 640px">
			<div class="modal-header primary">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="color: white;">
					<b>修改用户密码</b>
				</h4>
			</div>
			<div class="modal-body">
				<form id="updateUserPWDform" method="post" enctype="multipart/form-data" action="#">
				<input id="updateUserPWdForUserid" name="user_id" type="hidden" >
					<table class="table">
						<tr>
							<td>新的密码：</td>
							<td><input type="password" id="newpwd1" name="pwd1" class="form-control" placeholder="请输入新的密码"></td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td><div id="newpwd_div">
									<input type="password" id="newpwd2" name="pwd2" onblur="checkpwd()" class="form-control" placeholder="请再输入一次密码"><span id="newpwd_span"></span>
								</div> <span style="color: #cc0001" id="newpwd_err_span"></span></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: right">
								<button type="button" class="btn btn-primary" id="updatepwdButton" onclick="updteUserPwd()" disabled>提交</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
