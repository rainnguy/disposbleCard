<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/dispCard/manageCard.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/date/jquery.datetimepicker.css"/>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.datetimepicker.full.min.js"></script>
<style type="text/css">
.input::-ms-input-placeholder {
	text-align: center;
}
.input::-webkit-input-placeholder {
	text-align: center;
}
</style>
<script type="text/javascript">
$('#startDate').datetimepicker({
	lang:"ch",           //语言选择中文
    format:"Y-m-d",      //格式化日期
    timepicker:false    //关闭时间选项
});
$('#endDate').datetimepicker({
	lang:"ch",           //语言选择中文
    format:"Y-m-d",      //格式化日期
    timepicker:false    //关闭时间选项
});
$('#inDate').datetimepicker({
	lang:"ch",           //语言选择中文
    format:"Y-m-d",      //格式化日期
    timepicker:false    //关闭时间选项
});
</script>
	<div class="m-b-md"> 
		<form class="form-inline" role="form" id="searchForm" name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">发卡站点:</span></label>
				<input class="input-medium ui-autocomplete-input" id="issuedStaName"
					name="specInfoMap.issuedStaName">
			</div>
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">金额:</span></label>
				<input class="input-medium ui-autocomplete-input" id="money"
					name="specInfoMap.money">
			</div>
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">状态:</span></label>
				<input class="input-medium ui-autocomplete-input" id="status" placeholder="选择一种状态"
					name="specInfoMap.status">
			</div>
			<div class="form-group">
				<input class="input-medium ui-autocomplete-input" id="startDate" placeholder="发卡开始日期" name="specInfoMap.startDate" >
				<input class="input-medium ui-autocomplete-input" id="endDate" placeholder="发卡结束日期" name="specInfoMap.endDate">
				<input class="input-medium ui-autocomplete-input" id="inDate" placeholder="截止日期" name="specInfoMap.inDate">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:grid.exportData('/monitor/historyInfo.sxml')" class="btn btn-info" id="export">导出excel</a>
		</form>
	</div>
	<header class="panel-heading">
	<div class="doc-buttons">
		<c:forEach items="${res}" var="key">
			${key.menuDesc}
		</c:forEach>
	</div>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
	
	<div class="table-responsive">
		<div id="paging2" class="pagclass"></div>
	</div>