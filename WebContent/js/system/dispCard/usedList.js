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
			colkey : "issuedStaName",
			name : "发卡的站"
		}, {
			colkey : "usedStaName",
			name : "消费的站"
		}, {
			colkey : "usedTime",
			name : "消费时间"
		} ],
		jsonUrl : rootPath + '/cardReport/findUsedData.sxml',
		// checkbox : true,
		serNumber : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
});
