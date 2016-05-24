var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "code",
			name : "卡号"
		}, {
			colkey : "password",
			name : "密码"
		}, {
			colkey : "value",
			name : "金额"
		}, {
			colkey : "station",
			name : "生成站点"
		}, {
			colkey : "updateTime",
			name : "生成时间"
		}],
		jsonUrl : rootPath + '/disposableCard/searchInfo.sxml',
		checkbox : false
	});
	
	// 查询按钮
	$("#cardSearch").click("click", function() {// 绑定查询按扭
		var searchParams = $("#cardCreateForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	
	//生成按钮
	$("#cardCreate").click("click", function() {
		createCard();
	});
});

// 生成新卡
function createCard() {
	pageii = layer.open({
		title : "生成新卡",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/disposableCard/createCardPage.sxml'
	});
}
