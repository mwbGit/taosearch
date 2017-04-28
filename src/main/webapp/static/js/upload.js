$(function(){
	$('#startupload').mouseup(function(){
		var result_flag=$(window.frames['aa'].document).find("#result_flag").val();
		if(result_flag=="nofile"){
			$('#startupload').append("<div style='color:red'>请选择上传文件！</div>");
		}else if(result_flag=="largefile"){
			$('#startupload').append("<div style='color:red'>文件过大！</div>");
		}else if(result_flag=="erroformat"){
			$('#startupload').append("<div style='color:red'>文件格式不对（请选择.png,.jpg格式）！</div>");
		}else if(result_flag=="uploadfail"){
			$('#startupload').append("<div style='color:red'>上传失败！</div>");
		}else if(result_flag=="uploadsuccess"){
			$('#startupload').append("<div style='color:red'>上传成功！</div>");
		}
	});
});