var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "userName",
			name : "用户名",
			l_column : "3"
		}, {
			colkey : "accName",
			name : "账号"
		}, {
			colkey : "roleName",
			name : "所属角色",
		}, {
			colkey : "locked",
			name : "账号状态",
			width : '90px'
		}, {
			colkey : "description",
			name : "描述"
		}, {
			colkey : "updateTime",
			name : "时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			name : "操作",
			renderData : function( rowindex ,data, rowdata, colkeyn) {
				return "测试渲染函数";
			}
		} ],
		jsonUrl : rootPath + '/user/findByPage.sxml',
		checkbox : true,
		serNumber : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
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
	
	