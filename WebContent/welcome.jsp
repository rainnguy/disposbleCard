<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<header class="header bg-white b-b b-light">
		<p>
			<big>首页</big>&nbsp;&nbsp;<small>礼品卡消费</small>
		</p>
	</header>
<body>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/disposableCard/pay.sxml">
		<div class="form-group">
			<label class="col-sm-3 control-label">卡号：</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入卡号"
					name="payInfoMap.code" id="code">
			</div>
		</div>
		<button type="submit" class="btn btn-success btn-s-xs">付款</button>
	</form>
	<form id="form2" name="form2" class="form-horizontal" method="post"
		action="${ctx}/disposableCard/issue.sxml">
		<button type="submit" class="btn btn-success btn-s-xs">发卡</button>
	</form>
</body>
</html>