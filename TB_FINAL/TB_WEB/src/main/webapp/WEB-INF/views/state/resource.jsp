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
<title>하이퍼바이저</title>
<link rel="stylesheet" media="screen" type="text/css" href="/bctt/resources/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css"/>

<script src="/bctt/resources/js/resource.js"></script>
<script src="/bctt/resources/js/script.js"></script>
<script src="https://d3js.org/d3.v3.min.js"></script>     
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>

<script type="text/javascript">
	
function kuisin(idMyDiv){
     var objDiv = document.getElementById(idMyDiv);

     if(objDiv.style.display=="none"){ 
    	 objDiv.style.display = "block";
     }
     else{ 
    	 objDiv.style.display = "none"; 
   	 }
}
var i = 0;
function toggle_object(post_id)
{   
    i = i + post_id;
    var obj = document.getElementById('test');   
    if(!obj) return;   
    
    if(i%2!=0)
    {   
        obj.src="/bctt/resources/img/arrow-141-16.png";
        
    } 
    else 
    {   
        obj.src="/bctt/resources/img/arrow-204-16.png"; 
    }
}
</script>

</head>
<body class="homepage">
	<div id="page-wrapper">
		<div id="header">
			<div class="container">
				<div id="logo">
					<h1>
						<a href="/bctt/command/resource.do">
  							<img class="resize" src="/bctt/resources/img/wordmark.png" alt="자동화 시험 도구" style="border:0;">
						</a>
					</h1>
					<h1 class="logo_name" id="ddd">블록체인 자동화 테스트</h1>
				</div>
				<div style="float: right; margin-top: 20px; margin-right: 5px;">
					<h3>
						<a href ="/bctt/state/logout.do">로그아웃</a>
					</h3> 
				</div>
			</div>
		</div>

		<!-- main -->
		<div id="main">
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
						<input type="radio" name="verticalMenu" id="management" />
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
						<input type="radio" name="verticalMenu" id="system_info" checked="checked"/>
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
						<li>시스템 정보</li>
						<li>하이퍼바이저</li>
					</ol>
				</div>
				<div class="x_title">
					<h2>모든 하이퍼바이저</h2>
				</div>
				
				<div class="chart_div">
					<div id="piechart1" style="height: 200px; width:200px; display: inline-block;">
					</div>
					
					<div id="piechart2" style="height: 200px; width:200px; display: inline-block;">
					</div>
				
					<div id="piechart3" style="height: 200px; width:200px; display: inline-block;">
					</div>
				</div>
				

				<div id="computenode0" style="display: block;"></div>
				</section>
			</div>
			</section>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>