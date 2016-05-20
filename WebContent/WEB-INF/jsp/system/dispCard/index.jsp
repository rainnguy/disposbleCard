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
		<form id="cardIndexform" name="cardIndexform" class="form-vertical" action="${pageContext.servletContext.contextPath }/index/indexInfo.sxml" method="post">
			<div class="row wrapper">
				<div class="col-sm-3">
					<div class="input-group">
						<input type="text" class="input-sm form-control" name="dispCardMap.code"
							placeholder="卡号"> <span class="input-group-btn">
							<a href="javascript:void(0)" class="btn btn-default" id="check">确认信息</a>
						</span>
					</div>
				</div>
				<div class="col-sm-5 m-b-xs">
					<button class="btn btn-sm btn-default">消费</button>
				</div>
			</div>
		</form>
	</section>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
	
	<div class="table-responsive">
		<div id="paging2" class="pagclass"></div>
	</div>
</body>
</html>