$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('发卡成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
							parent.layer.close(parent.pageii);
							return false;
						});
						$("#form")[0].reset();
					} else if (data == "updates0") {
						layer.alert('发卡失败,请确认卡号和金额！', 3);
					} else if (data == "notUsableStation") {
						layer.alert('请选择可使用的站点！', 3);
					} else if (data == "notCode") {
						layer.alert('请输入卡号！', 3);
					} else {
						layer.alert('发卡异常！', 3);
					}
				}
			});
		}
	});
});
