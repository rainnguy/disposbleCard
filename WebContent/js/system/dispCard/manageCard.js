var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "code",
			name : "卡号"
		}, {
			colkey : "value",
			name : "面值"
		}, {
			colkey : "issuedStation",
			name : "发卡的站"
		}, {
			colkey : "issuedDate",
			name : "发卡日期"
		}, {
			colkey : "useAbledStation",
			name : "可使用的站"
		}, {
			colkey : "indate",
			name : "有效期"
		}, {
			colkey : "status",
			name : "状态"
		}, {
			colkey : "usedStation",
			name : "消费的站"
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
