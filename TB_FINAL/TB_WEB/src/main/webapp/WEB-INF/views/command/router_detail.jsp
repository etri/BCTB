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
<%@include file="../include/meta.jsp"%>
<title>Router</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">

<script type="text/javascript">
		var router_id = ${param.router_id};
		var router_name = ${param.router_name};
	</script>
	
	<script language=javascript>
		$(document).ready(function(){
			
			$("#router_name").html("<h2>" + router_name + "</h2>");
			
			$.ajax({
				url : "getRouterDetail.do",
				type:"get",
				data:{
					router_id: router_id
				},
				dataType:"json",
				success : function(data) {
					var temp="";
					
					if(data.result!="fail"){
						
						$("#name").html(data.data[0].name);
						$("#id").html(data.data[0].id);
						$("#description").html(data.data[0].description);
						$("#project_id").html(data.data[0].project_id);
						$("#status").html(data.data[0].status);
						$("#admin_state_up").html(data.data[0].admin_state_up);
						$("#availability_zones").html(data.data[0].availability_zones);

					}else{
					}
					
				},
			});
		});
	</script>
	
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
						<li>Router</li>
					</ol>
				</div>
				<div id="router_name" class="x_title">
					<h2>Router</h2>
				</div>

				<div id="router" class="table_wrapper">
					<table class="router_table">
					
						<thead>
							<tr>
								<td colspan="2">
									<h4>개요</h4>
								</td>
							</tr>
						</thead>
						<tr>
							<th width="20%">이름</th>
							<td width="80%" id="name"></td>
						</tr>
						<tr>
							<th width="20%">ID</th>
							<td width="80%" id="id"></td>
						</tr>
						<tr>
							<th width="20%">설명</th>
							<td width="80%" id="description"></td>
						</tr>
						<tr>
							<th width="20%">프로젝트 ID</th>
							<td width="80%" id="project_id"></td>
						</tr>
						<tr>
							<th width="20%">Status</th>
							<td width="80%" id="status"></td>
						</tr>
						<tr>
							<th width="20%">관리 상태</th>
							<td width="80%" id="admin_state_up"></td>
						</tr>
						<tr>
							<th width="20%">가용성 존</th>
							<td width="80%" id="availability_zones"></td>
						</tr>
					</table>	
				</div>

				</section>
			</div>
			</section>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>