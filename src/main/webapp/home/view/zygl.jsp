<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
$(function(){
	var date= new Date();
	var year=(date.getFullYear()-1)+"年";
	var message="删除"+year+"以前的历史图片："
	console.log(message)
	$("#zygl_delete_image_label").html(message);
})
	function submit_upload_zygl_form1() {
		var options = {
			success : function(data) {
				console.log(data);
				var file = $("#update_file");
				file.after(file.clone().val(""));
				file.remove();
				console.log(data.length)
				if (data.length == 0) {
					$("#upload_err_span").html("上传成功！");
				} else {
					$("#upload_err_table").datagrid(
							{
								columns : [ [ {
									title : '单位名称',
									field : 'organization_name',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '经营地址',
									field : 'management_addr',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '负责人',
									field : 'contact_name',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '联系电话',
									field : 'contact_tel',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '属地',
									field : 'territorial',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '类型',
									field : 'organization_type',
									formatter : function(val) {
										if (val == 7) {
											return "菜市场";
										} else if (val == 8) {
											return "规模以上商业零售经营单位";
										} else if (val == 9) {
											return "集贸市场";
										} else if (val == 10) {
											return "农副产品市场";
										} else if (val == 11) {
											return "食品生产企业";
										} else {
											return "";
										}
									}
								}, {
									title : '错误描述',
									field : 'stat',
									formatter : function(val) {
										if (val == 9999) {
											return "数据重复";
										} else if (val == 9998) {
											return "更新失败";
										} else if (val == 8889) {
											return "数据不存在";
										} else if (val == 8888) {
											return "删除失败";
										} else {
											return "";
										}
									}
								} ] ],
								singleSelect : true,
								selectChange : function(selected, rowIndex,
										rowData, $row) {
									console.info($row)
									select_status = selected;
									onSelectRow = rowData;
								}
							// edit:true
							}).datagrid("loadData", {
						rows : data
					});
					$("#upload_err_div").show();
				}
			}
		};
		$("#upload_zygl_form1").ajaxForm(options);
	}
	function submit_upload_zygl_form2() {
		var options = {
			success : function(data) {
				console.log(data);
				var file = $("#del_file");
				file.after(file.clone().val(""));
				file.remove();
				if (data.length == 0) {
					$("#del_err_span").html("上传成功！");
				} else {

					$("#del_err_table").datagrid(
							{
								columns : [ [ {
									title : '单位名称',
									field : 'organization_name',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '经营地址',
									field : 'management_addr',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '负责人',
									field : 'contact_name',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '联系电话',
									field : 'contact_tel',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '属地',
									field : 'territorial',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								}, {
									title : '类型',
									field : 'organization_type',
									formatter : function(val) {
										if (val == 7) {
											return "菜市场";
										} else if (val == 8) {
											return "规模以上商业零售经营单位";
										} else if (val == 9) {
											return "集贸市场";
										} else if (val == 10) {
											return "农副产品市场";
										} else if (val == 11) {
											return "食品生产企业";
										} else {
											return "";
										}
									}
								}, {
									title : '错误描述',
									field : 'stat',
									formatter : function(val) {
										if (val == 9999) {
											return "数据重复";
										} else if (val == 9998) {
											return "更新失败";
										} else if (val == 8889) {
											return "数据不存在";
										} else if (val == 8888) {
											return "删除失败";
										} else {
											return "";
										}
									}
								} ] ],
								singleSelect : true,
								selectChange : function(selected, rowIndex,
										rowData, $row) {
									console.info($row)
									select_status = selected;
									onSelectRow = rowData;
								}
							// edit:true
							}).datagrid("loadData", {
						rows : data
					});
					$("#del_err_div").show();
				}
			}
		};
		$("#upload_zygl_form2").ajaxForm(options);
	}
	function delete_zygl_resources_image() {
		if (confirm("您确定要删除这些图片么？")) {
			$.ajax({
				url : $ctx + '/caseInfoController/deleteZYGLRImage',
				type : 'post',
				async : false,
				success : function(r) {
					if (r.flag) {
						$('#alert_dialog_success').html(r.msg);
						$('#modal_success').modal('show');
					} else {
						$('#alert_dialog_danger').html(r.msg);
						$('#modal_danger').modal('show');
					}
				}
			})
		}
	}
</script>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">

			<form id="upload_zygl_form1" action="${ctx }/caseInfoController/uploadZYGLExcel" enctype="multipart/form-data" method="post">
				<input name="type" value="1" type="hidden">
				<div class="form-inline">
					<label for="update_file" class="form-control-static">请选择要新增检查对象的Excel文件：</label> <input id="update_file" name="update_file" type="file">
					<button class="btn btn-primary form-control" onclick="submit_upload_zygl_form1()">
						<i class="glyphicon glyphicon-open"></i>导入新增
					</button>
				</div>

			</form>
			<span style="color: #cc0001" id="upload_err_span"></span>
			<div class="book_theme_flag1" id="upload_err_div" style="display: none">
				<label for="upload_err_table" class="form-control-static" id="upload_err_label">提交更新过程中出现问题的检查对象：</label>
				<hr />
				<table id="upload_err_table" class="table table-striped table-advance table-hover table-condensed">
				</table>
			</div>
			<hr />

			<form action="${ctx }/caseInfoController/uploadZYGLExcel" enctype="multipart/form-data" method="post" id="upload_zygl_form2">
				<input name="type" value="0" type="hidden">
				<div class="form-inline">
					<label for="del_file" class="form-control-static">请选择要删除检查对象的Excel文件：</label> <input id="del_file" name="del_file" type="file">
					<button class="btn btn-primary form-control" onclick="submit_upload_zygl_form2()">
						<i class="glyphicon glyphicon-open"></i>导入删除
					</button>
				</div>

			</form>
			<span style="color: #cc0001" id="del_err_span"></span>
			<div class="book_theme_flag1" id="del_err_div" style="display: none">
				<label for="del_err_table" class="form-control-static" id="del_err_label">提交删除过程中出现问题的检查对象：</label>
				<hr />
				<table id="del_err_table" class="table table-striped table-advance table-hover table-condensed">
				</table>
			</div>
			<hr />

			<div class="form-inline" id="tjcx_alone_div">
				<label for="backupA" class="form-control-static">请点击归档：</label> <a id="backupA" class="btn btn-primary form-control" href="${ctx}/caseInfoController/downloadZipFile"><i class="glyphicon glyphicon-floppy-disk"></i>图片归档</a>
			</div>
			<hr />
			<div class="form-inline" id="tjcx_alone_div">
				<label for="zygl_delete_image_button" class="form-control-static" id="zygl_delete_image_label"></label>
				<button id="zygl_delete_image_button" class="btn btn-primary form-control" onclick="delete_zygl_resources_image()">
					<i class="glyphicon glyphicon-trash"></i>删除图片
				</button>
			</div>

			<hr />
		</div>
	</div>
</div>
<div class="col-xs-12 col-sm-12 col-md-12">
	<!-- 			<button class="btn btn-success book_theme_flag2" type="button" data-toggle="modal" -->
	<!-- 				data-target="#addBooks" onclick=""> -->
	<!-- 				<i class="glyphicon glyphicon-plus"></i>新增 -->
	<!-- 			</button> -->
	<!-- 			<div class="book_theme_flag1 btn-group"> -->
	<!-- 			<button class="btn btn-success" type="button" data-toggle="modal" -->
	<!-- 				data-target="#addBooks" onclick=""> -->
	<!-- 				<i class="glyphicon glyphicon-plus"></i>新增 -->
	<!-- 			</button> -->
	<!-- 			<button class="btn btn-danger" type="button" onclick="beforeDelete(1)" -->
	<!-- 				id="button_delete"> -->
	<!-- 				<i class="glyphicon glyphicon-trash"></i>刪除 -->
	<!-- 			</button> -->
	<!-- 			<button class="btn btn-info" type="button" data-toggle="modal" -->
	<!-- 				onclick="beforeEdit()"> -->
	<!-- 				<i class="glyphicon glyphicon-edit"></i>编辑 -->
	<!-- 			</button> -->
	<!-- 			</div> -->
	<div class="book_theme_flag1">
		<table id="case_Info" class="table table-striped table-advance table-hover table-condensed">
			<%-- 			<caption class="text-center">数据信息</caption> --%>
		</table>
	</div>
	<!-- 	<div class="row-fluid book_theme_flag2"> -->
	<!-- 		<ul class="blog-images" id="books_images" style="padding-left:0"> -->
	<!-- 		</ul> -->
	<!-- 	</div> -->
	<div id="case_paginator" style="align: center; float: left; cursor: pointer;"></div>
	<!-- 	<div id="afterpagination" style="float:left;margin-top:20px"><ul class="pagination "><li class="disabled" ><a id="book_totalpageAndTotals"></a></li></ul></div> -->
</div>
