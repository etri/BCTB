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
	<title>Subnet</title>
	<link rel="stylesheet" type="text/css" href="/bctt/resources/css/style.css">
	<script type="text/javascript">
		var subnet_id = ${param.subnet_id};
		var subnet_name = ${param.subnet_name};
	</script>
	
	<script language=javascript>
		$(document).ready(function(){
			
			$("#subnet_name").html("<h2>" + subnet_name + "</h2>");
			
			
			$.ajax({
				url : "getSubnet.do",
				type:"get",
				data:{
					subnet_id: subnet_id
				},
				dataType:"json",
				success : function(data) {
					var temp="";
					
					if(data.result!="fail"){
						
						$("#s_name").html(data.data[0].name);
						$("#s_id").html(data.data[0].id);
						$("#network_name").html(data.data[0].network_name);
						$("#network_id").html(data.data[0].network_id);
						$("#subnet_pool").html(data.data[0].subnetpool_id);
						$("#ip_version").html(data.data[0].ip_version);
						$("#cidr").html(data.data[0].cidr);
						$("#alloc_pool").html(data.data[0].allocation_pools);
						$("#gateway_ip").html(data.data[0].gateway_ip);
						$("#enable_dhcp").html(data.data[0].enable_dhcp);
						$("#additional_root").html(data.data[0].segment_id);
						$("#dns_nameserver").html(data.data[0].dns_nameservers);

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
						<li>Network</li>
						<li>서브넷</li>
					</ol>
				</div>
				<div id="subnet_name" class="x_title">
					<h2>서브넷 아이디</h2>
				</div>
		
				<div id="subnet" class="table_wrapper">
					<table class="subnet_table">
					
						<thead>
							<tr>
								<td colspan="2">
									<h4>개요</h4>
								</td>
							</tr>
						</thead>
						
						<tr>
							<th width="20%">이름</th>
							<td width="80%" id="s_name"></td>
						</tr>
						<tr>
							<th width="20%">ID</th>
							<td width="80%" id="s_id"></td>
						</tr>
						<tr>
							<th width="20%">네트워크 이름</th>
							<td width="80%" id="network_name"></td>
						</tr>
						<tr>
							<th width="20%">네트워크 ID</th>
							<td width="80%" id="network_id"></td>
						</tr>
						<tr>
							<th width="20%">서브넷 풀</th>
							<td width="80%" id="subnet_pool"></td>
						</tr>
						<tr>
							<th width="20%">IP 버전</th>
							<td width="80%" id="ip_version"></td>
						</tr>
						<tr>
							<th width="20%">CIDR</th>
							<td width="80%" id="cidr"></td>
						</tr>
						<tr>
							<th width="20%">IP 할당 풀</th>
							<td width="80%" id="alloc_pool"></td>
						</tr>
						<tr>
							<th width="20%">게이트웨이 IP</th>
							<td width="80%" id="gateway_ip"></td>
						</tr>
						<tr>
							<th width="20%">DHCP 활성화됨</th>
							<td width="80%" id="enable_dhcp"></td>
						</tr>
						<tr>
							<th width="20%">추가적인 경로</th>
							<td width="80%" id="additional_root"></td>
						</tr>
						<tr>
							<th width="20%">DNS 네임 서버</th>
							<td width="80%" id="dns_nameserver"></td>
						</tr>
					</table>	
				</div>
				
				</section>
			</div>
		</section>
	</div>
	<%@include file="../include/footer.jsp" %>
</div>
</body>
</html>