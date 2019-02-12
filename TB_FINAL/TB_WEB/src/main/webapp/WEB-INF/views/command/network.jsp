<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String login_id = (String)session.getAttribute("login_id");
	
	if(login_id==null||login_id.equals("")){                            
		response.sendRedirect("/bctt/state/login.do");    // 로그인 페이지로 리다이렉트 한다.
	}
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../include/meta.jsp" %>
	<title>Network</title>
	<link rel="stylesheet" type="text/css" href="/bctt/resources/css/style.css">
	<script src="/bctt/resources/js/network.js"></script>
</head>

<body class="homepage">
<div id="page-wrapper">
	<div id="header">
		<div class="container">
			<!-- logo -->
			<div id="logo">
				<h1>
						<a href="/bctt/command/resource.do">
  							<img class="resize" src="/bctt/resources/img/wordmark.png" alt="자동화 시험 도구" style="border:0;">
						</a>
					</h1>
					<h1 class="logo_name">블록체인 자동화 테스트베드</h1>
			</div>
			<div style="float: right; margin-top: 20px; margin-right: 5px;">
					<h3>
						<a href ="/bctt/state/logout.do">로그아웃</a>
					</h3> 
				</div>
		</div>
	</div>
		
	<div id="main">
		<!-- nav -->
		<nav id="nav" class="vertical">
			<ul>
				<li>
					<label for="home">프로젝트</label> 
					<input type="radio" name="verticalMenu" id="home" />
					<div>
						<ul>
							<li id="menu7"><a href="/bctt/command/hlf.do">테스트
										프로파일(HLF)</a></li>
								<li id="menu7"><a href="/bctt/command/ethereum.do">테스트
										프로파일(Ethereum)</a></li>
						</ul>
					</div>
				</li>
				<li>
					<label for="management">관리</label>
					<input type="radio" name="verticalMenu" id="management"  checked="checked"/>
					<div>
						<ul>
							<li id="menu3"><a href="/bctt/command/instance.do">Instance</a></li>
							<li id="menu4"><a href="/bctt/command/image.do">Image</a></li>
							<li id="menu5"><a href="/bctt/command/flavor.do">Flavor</a></li>
							<li id="menu3"><a href="/bctt/command/network.do">Network</a></li>
							<li id="menu3"><a href="/bctt/command/router.do">Router</a></li>
						</ul>
					</div>
				</li>
				<li>
					<label for="system_info">시스템 정보</label>
					<input type="radio" name="verticalMenu" id="system_info"/>
					<div>
						<ul>
							<li id="menu1"><a href="/bctt/command/resource.do">하이퍼바이저</a></li>
							<li id="menu1"><a href="/bctt/state/basic.do">기본</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</nav>
		  
		<section class="container">
			<div class="row">
				<section class="col-12">
				<div class="location_wrapper">
					<ol>
						<li>관리</li>
						<li>Network</li>
					</ol>
				</div>
				<div class="x_title">
					<h2>Network</h2>
				</div>
		
				<div id="public" class="table_wrapper">
				</div>
				
				</section>
			</div>
		</section>
	</div>
	<%@include file="../include/footer.jsp" %>
</div>
</body>
</html>