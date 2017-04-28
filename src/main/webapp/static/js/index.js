var getAllBooksForPage_url='/booksmanager/booksController/queryAllBooksForPage';
var rowForPage=4;
var page=1;
var onSelectRow;
var select_status=false;
$(function(){
	loadGrid(getAllBooksForPage_url,null);
	
});
function loadGrid(url,datas){
	var books_datas = getDatas(url+"?rows="+rowForPage+"&page="+page,datas);
	$('#books_Info').datagrid({
		columns:[[{
			field:'bookId',
			hidden:true,
		},{
			title:'状态',
			field:'bookStatus',
			formatter:function(val){
				if(val=='0'){
					return "<i class='light light-success'></i><input type='hidden' name='bookStatus' value="+val+" />";
				}
				else if(val=='1'){
					return "<i class='light light-danger'></i><input type='hidden' name='bookStatus' value="+val+" />";
				}
			}
		},{
			title:'书名',
			field:'bookName',
		},{
			title:'作者',
			field:'bookAuthor',
		},{
			title:'类别',
			field:'bookType',
		},{
			title:'简介',
			field:'bookDescribe',
		},{
			title:'查看',
			formatter:function(val){
				return "<a href='#'>查看详情</a>";
			}
		},{
			title:'操作',
			formatter:function(val){
				return "<button onclick='borrow_book()' class='btn btn-success btn-xs'>借书</button> <button onclick='return_book()' class='btn btn-primary btn-xs'>还书</button>";
			}
		}]],
		singleSelect: true,
		selectChange: function(selected, rowIndex, rowData, $row) {
			select_status=selected;
			onSelectRow=rowData;
		}
//		edit:true
	}).datagrid("loadData",{rows:books_datas.datas});
	
	$('#book_paginator').bootstrapPaginator({
		currentPage: 1,
		totalPages:books_datas.totalpage,
		onPageClicked: function(e,originalEvent,type,pages){
			$('#books_Info').datagrid("loadData", {rows:getDatas(url+"?rows="+rowForPage+"&page="+pages,datas).datas});
        }
	});
	$('#book_totalpageAndTotals').html("共 "+books_datas.totalpage+" 页   ,共 "+books_datas.totals+" 条记录");
}
function  getDatas(url,datas){
	var arr={};
	$.ajax({
		url:url,
		type:"post",
		data:datas,
		async:false,
		success:function(data){
			arr=data;
		}
	});
	return arr;
}

function getBooksByLike(){
	url ="/booksmanager/booksController/getBooksByLike";
	data =serializeObject($('#queryBooksInfo_form'));
	loadGrid(url,data);
}

function addBooks(){
	$('#addBooks').modal('hide');
	$.ajax({
		url:"/booksmanager/booksController/addBooks",
		type:"post",
		data:serializeObject($('#addBooksInfo_form')),
		cache:false,
		success:function(r){
			if(r.flag){
				$('#alert_dialog_success').html(r.msg);
				$('#modal_success').modal('show');
			}else{
				$('#alert_dialog_danger').html(r.msg);
				$('#modal_danger').modal('show');
			}
			loadGrid(getAllBooksForPage_url,null);
		},
	});
}
function editBooks(){
	$('#editBooks').modal('hide');
	$.ajax({
		url:"/booksmanager/booksController/updateBooks",
		type:"post",
		data:serializeObject($('#editBooksInfo_form')),
		cache:false,
		success:function(r){
			if(r.flag){
				$('#alert_dialog_success').html(r.msg);
				$('#modal_success').modal('show');
			}else{
				$('#alert_dialog_danger').html(r.msg);
				$('#modal_danger').modal('show');
			}
			loadGrid(getAllBooksForPage_url,null);
		},
	});
}
function deleteBooks(){
		$.ajax({
			url:"/booksmanager/booksController/deleteBooks",
			type:"post",
			data:"id="+onSelectRow.bookId,
			cache:false,
			success:function(r){
				if(r.flag){
					$('#alert_dialog_success').html(r.msg);
					$('#modal_success').modal('show');
				}else{
					$('#alert_dialog_danger').html(r.msg);
					$('#modal_danger').modal('show');
				}
				loadGrid(getAllBooksForPage_url,null);
				select_status=false;
			},
		});
}
function beforeEdit(){
	if(select_status){
		$('#editBooksInfo_form').form('load',onSelectRow);
		$('#editBooks').modal('show');
	}else{
		$('#alert_dialog_warning').html("请选择你要编辑的数据");
		$('#modal_warning').modal('show');
	}
}
function beforeDelete(){
	if (select_status) {
		$('#alert_dialog_primary').html("你确定要删除");
		$('#button_primary_sure').attr('onclick', 'deleteBooks()');
		$('#modal_primary').modal('show');
	} else {
		$('#alert_dialog_warning').html("请选择你要删除的数据");
		$('#modal_warning').modal('show');
	}
}
function beforeAdd(){
	
}
function borrow_book(){
	
}
function serializeObject(form){//将表单元素中的值序列化成对象
	var o = {};
	$.each(form.serializeArray(),function (index_){
		if(o[this['name']]){
			o[this['name']] = o[this['name']]+","+this['value'];
			
		}else {
			o[this['name']] = this['value'];
		}
	});
	return o;
}