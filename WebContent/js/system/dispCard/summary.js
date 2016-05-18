var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "issueTotal",
			name : "发卡数量"
		}, {
			colkey : "issueAmount",
			name : "发卡总金额"
		}, {
			colkey : "usedTotal",
			name : "消费数量"
		}, {
			colkey : "usedAmount",
			name : "消费总金额"
		}, {
			colkey : "useAbleTotal",
			name : "未消费数量"
		}, {
			colkey : "useAbleAmount",
			name : "未消费总金额"
		} , {
			colkey : "outDateTotal",
			name : "过期数量"
		} , {
			colkey : "outDateAmount",
			name : "过期总金额"
		} ],
		jsonUrl : rootPath + '/cardReport/findSummaryData.sxml',
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
