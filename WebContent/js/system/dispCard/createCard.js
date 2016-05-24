$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('生成新卡成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
							parent.layer.close(parent.pageii);
							return false;
						});
						$("#form")[0].reset();
					} else if (data == "insert0") {
						layer.alert('生成新卡失败！', 3);
					} else if (data == "nomoney") {
						layer.alert('请输入金额！', 3);
					} else if (data == "nonumber") {
						layer.alert('请输入数量！', 3);
					} else if (data == "wrongnumber") {
						layer.alert('数量请输入数字！', 3);
					} else if (data == "wrongmoney") {
						layer.alert('金额请输入数字！', 3);
					} else {
						layer.alert('生成新卡异常！', 3);
					}
				}
			});
		}
	});
});