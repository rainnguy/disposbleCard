<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/dispCard/indexPage.js"></script>
<html>
	<header class="header bg-white b-b b-light">
		<h2>欢迎使用礼品卡消费系统</h2>
	</header>
	<div class="doc-buttons">
		<c:forEach items="${res}" var="key">
			${key.menuDesc}
		</c:forEach>
	</div>
<body>
	<section class="panel panel-default">
		<form id="cardIndexform" name="cardIndexform" class="form-vertical" action="${pageContext.servletContext.contextPath }/index/consumeCard.sxml" method="post">
			<div class="row wrapper">
				<div class="col-sm-2">
					<div class="input-group">
						<input type="text" class="input-sm form-control" name="dispCardMap.code"
							placeholder="卡号">
					</div>
				</div>
				<div class="col-sm-3 m-b-xs">
					<input type="button" class="btn btn-sm btn-default" id="cardSearch" value="查询"/>
					<input type="button" class="btn btn-sm btn-default" id="cardConsume" value="消费"/>
					<input type="button" class="btn btn-sm btn-default" id="cardSold" value="发卡"/>
				</div>
			</div>
		</form>
	</section>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
	
</body>
</html>