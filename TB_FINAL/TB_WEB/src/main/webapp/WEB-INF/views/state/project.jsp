<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../include/meta.jsp"%>
<title>프로젝트</title>
<link rel="stylesheet" media="screen" type="text/css"
	href="/resources/css/style.css">
<script src="/resources/js/project.js"></script>
<script src="/resources/js/script.js"></script>
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
        obj.src="/resources/img/arrow-141-16.png";
        
    } 
    else 
    {   
        obj.src="/resources/img/arrow-204-16.png"; 
    }
}
</script>


</head>
<body class="homepage">
	<div id="page-wrapper">
		<!-- Header -->
		<div id="header">
			<div class="container">
				<!-- logo -->
				<div id="logo">
					<h1>
						<a href="/bctt/command/resource.do"> <img
							src="/resources/img/logo2.png" alt="자동화 시험 도구"
							style="width: 43px; height: 48px; border: 0;">
						</a>
					</h1>
				</div>
			</div>
		</div>

		<!-- main -->
		<div id="main">
			<!-- nav -->

			<nav id="nav" class="vertical">
				<ul>
					<li><label for="home">프로젝트</label> <input type="radio"
						name="verticalMenu" id="home" checked="checked" />
						<div>
							<ul>
								<li id="menu7"><a href="/bctt/command/hlf.do">테스트
										프로파일(HLF)</a></li>
								<li id="menu7"><a href="/bctt/command/ethereum.do">테스트
										프로파일(Ethereum)</a></li>
							</ul>
						</div></li>
					<li><label for="management">관리</label> <input type="radio"
						name="verticalMenu" id="management"/>
						<div>
							<ul>
								<li id="menu3"><a href="/bctt/command/instance.do">Instance</a></li>
								<li id="menu4"><a href="/bctt/command/image.do">Image</a></li>
								<li id="menu5"><a href="/bctt/command/flavor.do">Flavor</a></li>
								<li id="menu3"><a href="/bctt/command/network.do">Network</a></li>
								<li id="menu3"><a href="/bctt/command/router.do">Router</a></li>
							</ul>
						</div></li>
					<li><label for="system_info">시스템 정보</label> <input
						type="radio" name="verticalMenu" id="system_info" />
						<div>
							<ul>
								<li id="menu1"><a href="/bctt/command/resource.do">하이퍼바이저</a></li>
								<li id="menu1"><a href="/bctt/state/basic.do">기본</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>

			<section class="container">
			<div class="row">
				<section class="col-12">
				<div class="location_wrapper">
					<ol>
						<li>프로젝트</li>
						<li>프로젝트</li>
					</ol>
				</div>
				<div class="x_title">
					<h2>프로젝트</h2>
				</div>

				<div id="project" style="display: block;"></div>
				</section>
			</div>
			</section>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>