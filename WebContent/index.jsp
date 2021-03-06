<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="en" class="app">
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/user/updatePassword.js"></script>
<script type="text/javascript">
	$(function() {
    var winwidth = $("body").width();
    if(winwidth<770){
      $("#nav ul.lt li").click(function(){
        $("#nav").removeClass("nav-off-screen");
     });
    }

    var tb = $("#loadhtml");
		tb.html(CommnUtil.loadingImg());
		tb.load(rootPath+"/index/indexPage.sxml");
		$("[nav-n]").each(function () {
				$(this).bind("click",function(){
						var nav = $(this).attr("nav-n");
						tolaod(nav);
				});
			});
		});
	/***
	//需要三个参数, 目录,菜单,菜单连接
	例:
	nav="系统管理,用户列表,/user/list.sxml"; 	
	**/
	function tolaod(nav){
		var sn = nav.split(",");
		var html = '<li><i class="fa fa-home"></i>';
		html+='<a href="index.sxml">Home</a></li>';
		for(var i=0;i<2;i++){
			html+='<li><a href="javascript:void(0)">'+sn[i]+'</a></li>';
		}
		$("#topli").html(html);
		var tb = $("#loadhtml");
		tb.html(CommnUtil.loadingImg());
		tb.load(rootPath+sn[2]);
	}
</script>
</head>
<body class="" style="">
	<section class="vbox">
		<header class="bg-dark dk header navbar navbar-fixed-top-xs">
			<div class="navbar-header aside-md" >
				<a class="btn btn-link visible-xs"
					data-toggle="class:nav-off-screen,open" data-target="#nav,html">
					<i class="fa fa-bars"></i>
				</a> <a href="index.sxml#" class="navbar-brand"
					data-toggle="fullscreen"><img
					src="${ctx}/notebook/notebook_files/logo.png" class="m-r-sm">礼品卡优惠系统</a>
				<a class="btn btn-link visible-xs" data-toggle="dropdown"
					data-target=".nav-user"> <i class="fa fa-cog"></i>
				</a>
			</div>
			<ul class="nav navbar-nav hidden-xs">
				<li class="dropdown">
					<section
						class="dropdown-menu aside-xl on animated fadeInLeft no-borders lt">
						<div class="wrapper lter m-t-n-xs">
							<a href="index.html#" class="thumb pull-left m-r"> <img
								src="${ctx}/notebook/notebook_files/avatar.jpg"
								class="img-circle">
							</a>
							<div class="clear">
								<a href="index.html#"><span class="text-white font-bold">@Mike
										Mcalidek</span></a> <small class="block">Art Director</small> <a
									href="index.html#" class="btn btn-xs btn-success m-t-xs">Upgrade</a>
							</div>
						</div>
						<div class="row m-l-none m-r-none m-b-n-xs text-center">
							<div class="col-xs-4">
								<div class="padder-v">
									<span class="m-b-xs h4 block text-white">245</span> <small
										class="text-muted">Followers</small>
								</div>
							</div>
							<div class="col-xs-4 dk">
								<div class="padder-v">
									<span class="m-b-xs h4 block text-white">55</span> <small
										class="text-muted">Likes</small>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="padder-v">
									<span class="m-b-xs h4 block text-white">2,035</span> <small
										class="text-muted">Photos</small>
								</div>
							</div>
						</div>
					</section></li>
				<li>
					<div class="m-t m-l">
						<a href="index.sxml"
							class="dropdown-toggle btn btn-xs btn-primary" title="Upgrade"><i
							class="fa fa-long-arrow-up"></i></a>
					</div>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user">
				<li class="dropdown hidden-xs"><a href="index.html#"
					class="dropdown-toggle dker" data-toggle="dropdown"><i
						class="fa fa-fw fa-search"></i></a>
					<section class="dropdown-menu aside-xl animated fadeInUp">
						<section class="panel bg-white">
							<form role="search">
								<div class="form-group wrapper m-b-none">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="Search">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-info btn-icon">
												<i class="fa fa-search"></i>
											</button>
										</span>
									</div>
								</div>
							</form>
						</section>
					</section></li>
				<li class="dropdown"><a href="index.html#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="thumb-sm avatar pull-left"> <img
							src="${ctx}/notebook/notebook_files/avatar.jpg">
					</span> ${userFormBean.accName} <b class="caret"></b>
					</a>
					<ul class="dropdown-menu animated fadeInRight">
						<span class="arrow top"></span>
						<li><a href="index.sxml#">Settings</a></li>
						<li><a href="#" onclick="javascript:updatePasswordLayer();">密码修改</a></li>
						<li class="divider"></li>
						<li><a href="logout.sxml">Logout</a></li>
					</ul>
				</li>
			</ul>
		</header>
		<section>
			<section class="hbox stretch">
				<!-- .aside -->
				<aside class="bg-dark lter aside-md hidden-print hidden-xs" id="nav">
					<section class="vbox">
						<!-- <header class="header bg-primary lter text-center clearfix">
							<div class="btn-group">
							系统菜单
							</div>
						</header> -->
						<section class="w-f scrollable">
							<div class="slim-scroll" data-height="auto"
								data-disable-fade-out="true" data-distance="0" data-size="5px"
								data-color="#333333">
								<!-- nav -->
								<nav class="nav-primary hidden-xs">
									<ul class="nav">
										<c:forEach var="key" items="${list}" varStatus="s">
											<!-- <li class="active"> 某一项展开-->
											<li <c:if test="${s.index==0}">class="active"</c:if>><a
												href="javascript:void(0)"
												<c:if test="${s.index==0}">class="active"</c:if>> <c:if
														test="${s.index==0}">
														<i class="fa fa-dashboard icon"> <b class="bg-danger"></b>
														</i>
													</c:if> <c:if test="${s.index==1}">
														<i class="fa fa-pencil-square icon"> <b
															class="bg-warning"></b>
														</i>
													</c:if> <c:if test="${s.index==2}">
														<i class="fa fa-columns icon"> <b class="bg-primary"></b>
														</i>
													</c:if> <c:if test="${s.index==3}">
														<i class="fa fa-book icon"> <b class="bg-info"></b>
														</i>
													</c:if> <c:if test="${s.index==4}">
														<i class="fa fa-th-list icon"> <b class="bg-dark"></b>
														</i>
													</c:if> <span class="pull-right"> <i
														class="fa fa-angle-down text"></i> <i
														class="fa fa-angle-up text-active"></i>
												</span> <span>${key.menuName}</span>
											</a>

												<ul class="nav lt">
													<c:forEach var="kc" items="${key.children}">
														<li class="active"><a
															href="javascript:void(0)"
															class="active" nav-n="${key.menuName},${kc.menuName},${kc.menuUrl}?id=${kc.id}"> <i class="fa fa-angle-right"></i> <span>${kc.menuName}</span>
														</a></li>
													</c:forEach>
												</ul></li>
										</c:forEach>
									</ul>
								</nav>
								<!-- / nav -->
							</div>
						</section>
						<footer class="footer lt hidden-xs b-t b-dark">
							<a href="#nav" data-toggle="class:nav-xs"
								class="pull-right btn btn-sm btn-dark btn-icon"> <i
								class="fa fa-angle-left text"></i> <i
								class="fa fa-angle-right text-active"></i>
							</a>
						</footer>
					</section>
				</aside>
				<!-- /.aside -->
				<section id="content">
					<section id="id_vbox" class="vbox">
						<ul class="breadcrumb no-border no-radius b-b b-light" id="topli">
						</ul>
						<section class="scrollable" style="margin-top: 35px;">
						<div id="loadhtml"></div>
						</section>
					</section>
				</section>
				<aside class="bg-light lter b-l aside-md hide" id="notes">
					<div class="wrapper">Notification</div>
				</aside>
			</section>
		</section>
	</section>
	<!-- Bootstrap -->
	<div id="flotTip" style="display: none; position: absolute;"></div>


</body>
</html>