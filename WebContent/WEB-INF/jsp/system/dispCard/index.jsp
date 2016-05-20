<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<header class="header bg-white b-b b-light">
			<h2>欢迎光临礼品卡消费系统</h2>
	</header>
<body>
<section class="panel panel-default">
	<form id="cardIndexform" name="cardIndexform" class="form-vertical" action="${pageContext.servletContext.contextPath }/index/indexInfo.sxml" method="post">
		<div class="row wrapper">
			<div class="col-sm-3">
				<div class="input-group">
					
					<input type="text" class="input-sm form-control" name="dispCardMap.code"
						placeholder="卡号"> <span class="input-group-btn">
						<button class="btn btn-sm btn-default" type="submit">Go!</button>
					</span>
					
				</div>
			</div>
			<div class="col-sm-5 m-b-xs">
				<button class="btn btn-sm btn-default">消费</button>
			</div>
		</div>
	</form>
		<div class="table-responsive">
			<table class="table table-striped b-t b-light">
				<thead>
					<tr>
						<th width="20"><input type="checkbox"></th>
						<th>卡号</th>
						<th>金额</th>
						<th>消费状态</th>
						<th>限制站点</th>
						<th>发卡日期</th>
						<th>截止日期</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="indexInfo" var="value">
						<tr>
<%-- 							<td>${value.code}</td> --%>
<%-- 							<td>${value.value}</td> --%>
<%-- 							<td>${value.status}</td> --%>
<%-- 							<td>${value.issuedStation}</td> --%>
<%-- 							<td>${value.issuedDate}</td> --%>
<%-- 							<td>${value.indate}</td> --%>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</div>
	</section>
</body>
</html>