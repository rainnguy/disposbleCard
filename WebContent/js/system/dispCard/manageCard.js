var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
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
		checkbox : true,
		serNumber : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
});
