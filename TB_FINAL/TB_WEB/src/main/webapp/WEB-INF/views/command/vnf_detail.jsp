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
<title>VNF</title>
<link rel="stylesheet" type="text/css" href="/bctt/resources/css/style.css">

<script type="text/javascript">
		var vnf_id = ${param.vnf_id};
	</script>
	
	<script language=javascript>
		$(document).ready(function(){
			
			$("#instance_name").html("<h2>VNF Details : " + vnf_id + "</h2>");
			
			$.ajax({
				url : "getVnfDetail.do",
				type:"get",
				data:{
					vnf_id: vnf_id
				},
				dataType:"json",
				success : function(data) {
					var temp="";
					
					if(data.result!="fail"){
						
						$("#name").html(data.name);
						$("#id").html(data.id);
						$("#tenant_id").html(data.project_id);
						$("#description").html(data.description);
						$("#status").html(data.status);
						$("#created_at").html(data.created_at);
						$("#stack_id").html(data.instance_id);
						$("#vnfd_id").html(data.vnfd_id);
						$("#vim_id").html(data.vim_id);
						$("#error_reason").html(data.error_reason);
						
						$("#vim_name").html(data.placement_attr);
						
						for(i=0; i<data.vdu_list.length; i++){
							temp += "<tr><th width='20%'>"+  data.vdu_list[i].vdu_name + "</th>";
							temp += "<td width='80%'>"+  data.vdu_list[i].vdu_ip + "</td>";
							temp += "</tr>";
						}
						
						$("#vdu_tbody").html(temp);
						
						
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
						<li>VNF</li>
					</ol>
				</div>
				<div id="instance_name" class="x_title">
					<h2>VNF</h2>
				</div>

				<div id="instance" class="table_wrapper">
				
					<table class="instance_table">
					
						<thead>
							<tr>
								<td colspan="2">
									<h4>VNF Information</h4>
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
							<th width="20%">Tenant ID</th>
							<td width="80%" id="tenant_id"></td>
						</tr>
						<tr>
							<th width="20%">설명</th>
							<td width="80%" id="description"></td>
						</tr>
						<tr>
							<th width="20%">Status</th>
							<td width="80%" id="status"></td>
						</tr>
						<tr>
							<th width="20%">생성됨</th>
							<td width="80%" id="created_at"></td>
						</tr>
						<tr>
							<th width="20%">Stack ID</th>
							<td width="80%" id="stack_id"></td>
						</tr>
						<tr>
							<th width="20%">VNFD ID</th>
							<td width="80%" id="vnfd_id"></td>
						</tr>
						<tr>
							<th width="20%">VIM ID</th>
							<td width="80%" id="vim_id"></td>
						</tr>
						<tr>
							<th width="20%">Error Reason</th>
							<td width="80%" id="error_reason"></td>
						</tr>
						
						<thead>
							<tr>
								<td colspan="2">
									<h4>Mgmt IP Address</h4>
								</td>
							</tr>
						</thead>
						
						<tbody id="vdu_tbody">
						</tbody>
						
						<thead>
							<tr>
								<td colspan="2">
									<h4>Placement Attributes</h4>
								</td>
							</tr>
						</thead>
						
						<tr>
							<th width="20%">Vim_Name</th>
							<td width="80%" id="vim_name"></td>
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