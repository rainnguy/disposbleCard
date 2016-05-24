<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/dispCard/create.js"></script>
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
$('#startDate').datetimepicker();
$('#endDate').datetimepicker();
</script>
<html>
	<div class="doc-buttons">
	<c:forEach items="${res}" var="key">
		${key.menuDesc}
	</c:forEach>
	</div>
<body>
	<section class="panel panel-default">
		<form id="cardCreateForm" name="cardCreateForm" class="form-inline" 
			action="${pageContext.servletContext.contextPath }/disposableCard/createCard.sxml" method="post">
			<div class="form-group">
				<label class="control-label"> <span
					class="h5 font-thin v-middle">金额:</span></label>
				<input class="input-medium ui-autocomplete-input" id="money"
					name="dispCardMap.money">
			</div>
			<div class="form-group">
				<input class="input-medium ui-autocomplete-input" id="startDate" placeholder="开始时间" name="dispCardMap.startDate" >
				<input class="input-medium ui-autocomplete-input" id="endDate" placeholder="结束时间" name="dispCardMap.endDate">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="cardSearch">查询</a>
			<a href="javascript:grid.exportData('/disposableCard/exportCreateInfoList.sxml')" class="btn btn-info" id="export">导出excel</a>
			<input type="button" class="btn btn-sm btn-default" id="cardCreate" value="生成新卡"/>
		</form>
	</section>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
	
</body>
</html>