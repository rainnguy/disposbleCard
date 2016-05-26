var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			hide : true
		}, {
			colkey : "code",
			name : "卡号"
		}, {
			colkey : "value",
			name : "金额"
		}, {
			colkey : "issuedStation",
			name : "发卡站点"
		}, {
			colkey : "issuedDate",
			name : "发卡日期"
		}, {
			colkey : "useAbledStation",
			name : "限制站点"
		}, {
			colkey : "indate",
			name : "截止日期"
		}, {
			colkey : "status",
			name : "状态"
		}, {
			colkey : "usedStation",
			name : "消费站点"
		}, {
			colkey : "usedTime",
			name : "消费时间"
		} ],
		jsonUrl : rootPath + '/disposableCard/findSpecificationData.sxml',
		checkbox : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	// 编辑按钮
	$("#editCard").click("click", function() {
		editCard();
	});
	// 删除按钮
	$("#delCard").click("click", function() {
		delCard();
	});
});

//编辑
function editCard() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("请选中一项！");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/disposableCard/editCardPage.sxml?id=' + cbox.toString()
	});
}

//删除
function delCard() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/disposableCard/deleteCard.sxml';
		var s = CommnUtil.ajax(url, {
			cardIds : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			grid.loadData();
		} else if(s == "deletewrong") {
			layer.msg('删除出错');
		} else {
			layer.msg('删除失败');
		}
	});
}
