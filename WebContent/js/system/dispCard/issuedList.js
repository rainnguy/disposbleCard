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
			colkey : "orgName",
			name : "发卡站点"
		}, {
			colkey : "issuedDate",
			name : "发卡日期",
			renderData : function(rowindex, data, rowdata, column) {
//				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				return data;
			}
		} ],
		jsonUrl : rootPath + '/cardReport/findIssuedData.sxml',
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
