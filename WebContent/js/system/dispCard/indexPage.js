var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "id",
			name : "id",
			hide : true
		},{
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
	
	// 确认按钮
	$("#cardSearch").click("click", function() {// 绑定查询按扭
		var searchParams = $("#cardIndexform").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	
	// 消费按钮
	$("#cardConsume").click("click", function() {
		consumeCard();
	});
	
	// 发卡按钮
	$("#cardSold").click("click", function() {
		soldCard();
	});
	
});

// 消费
function consumeCard() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("请选其中一项！");
		return;
	}
	var url = rootPath + '/index/consumeCard.sxml';
	var s = CommnUtil.ajax(url, {
		cardId : cbox.toString(),
	}, "json");
	if (s == "success") {
		layer.msg('消费成功');
		grid.loadData();
	} else if (s == "updates0") {
		layer.msg('消费失败');
	} else if (s == "cannot") {
		layer.msg('该卡无法消费');
	} else {
		layer.msg('消费异常');
	}
}

// 发卡
function soldCard() {
	pageii = layer.open({
		title : "发卡",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/index/soldCard.sxml'
	});
}
	