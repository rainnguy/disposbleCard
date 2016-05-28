<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/dispCard/editCard.js"></script>
<script type="text/javascript">
	function statusSelected(){
		var status="${status}";
		var obj=document.getElementById("status");
		for(var i=0;i<obj.options.length;i++){
			if(obj.options[i].value==status){
				obj.options[i].selected=true;
				break;
			}
		}
	}
</script>

<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}
</style>
</head>
<body onload="statusSelected()">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/disposableCard/editCard.sxml">
		<input type="hidden" class="form-control"
			value="${id}" name="dispCardMap.id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">卡号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" value="${code}"
						name="dispCardMap.code" id="code" readonly="readonly">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">金额</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" value="${value}"
						name="dispCardMap.value" id="value" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">状态:</label>
				<select id="status" class="input-large" name="dispCardMap.status">
					<option value="1">可消费</option>
					<option value="2">已消费</option>
					<option value="3">未发卡</option>
					<option value="4">已过期</option>
				</select>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group" id="selRole" data-url="/index/stationSelect.sxml?orgSelectMap.useAbledStation=${useAbledStation}"></div>
			<div class="line line-dashed line-lg pull-in"></div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入描述"
						value="${description}" name="dispCardMap.description" id="description">
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">保存</button>
		</footer> </section>
	</form>
	<script type="text/javascript">
	onloadurl();
</script>
</body>
</html>