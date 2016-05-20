var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "code",
			name : "卡号"
		}, {
			colkey : "value",
			name : "金额"
		}, {
			colkey : "status",
			name : "消费状态"
		}, {
			colkey : "useAbledStation",
			name : "限制站点"
		}, {
			colkey : "issuedDate",
			name : "发卡日期"
		}, {
			colkey : "indate",
			name : "截止日期"
		}],
		jsonUrl : rootPath + '/index/indexInfo.sxml',
		checkbox : true
	});
	$("#check").click("click", function() {// 绑定查询按扭
		var searchParams = $("#cardIndexform").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
//	$("#addAccount").click("click", function() {
//		addAccount();
//	});
//	$("#editAccount").click("click", function() {
//		editAccount();
//	});
//	$("#delAccount").click("click", function() {
//		delAccount();
//	});
//	$("#permissions").click("click", function() {
//		permissions();
//	});
});

function editAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/user/editUI.sxml?userId=' + cbox
	});
}

function addAccount() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/user/addUI.sxml'
	});
}
function delAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/user/deleteEntity.sxml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			grid.loadData();
		} else {
			layer.msg('删除失败');
		}
	});
}
	