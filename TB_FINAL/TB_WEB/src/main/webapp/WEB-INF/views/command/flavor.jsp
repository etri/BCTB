<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String login_id = (String)session.getAttribute("login_id");
	
	if(login_id==null||login_id.equals("")){                            
		response.sendRedirect("/bctt/state/login.do");    // 로그인 페이지로 리다이렉트 한다.
	}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../include/meta.jsp"%>
<title>Flavor</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">
<script src="/bctt/resources/js/layer.js"></script>
<script src="/bctt/resources/js/flavor.js"></script>

<script type="text/javascript">
	function onlyNum(e) {
		var event = e || window.event;
		var keycode = event.keyCode || e.which;
		if (keycode == 8 || (keycode >= 35 && keycode <= 40)
			|| (keycode >= 46 && keycode <= 57)
			|| (keycode >= 96 && keycode <= 105)) {
			return true;
		} else {
			return false;
		}
	}
</script>

<script type="text/javascript">
	function flavorDelete(form){
	 var mylen = form.name.length;
	 var gourl;
	 
	 var check = false;
	 for(var i= 0; i < mylen; i++){
	 	if(form.name[i].checked==true){
	 		check = true;
	 	}
	 }
	 if(check == false){alert("아무거나라도 선택해주세요"); return}
	 
	 if(mylen == "undefined"){
		 mylen = 1;	 
	 }
	 
	for(var i= 0; i < mylen; i++){
		  if(form.name[i].checked == true){
			  
		<c:forEach items="${vlist}" var="vlist">
		if(form.name[i].value != "${vlist.flavor_name}"){
			gourl = form.name[i].value;
			//break;
		}
		else{
			alert('vnfd에서 사용중 입니다.');
			return;
		}
		</c:forEach>
		}
	}
	
	 document.rvForm.action = "deleteFlavor.do?name="+gourl;
	 document.rvForm.submit();
	
	}
</script>

<script>
function frmsubmit(){
	
if(insertForm.name.value=="" || insertForm.vcpus.value=="" || insertForm.ram.value == "" || insertForm.disk.value == "" || insertForm.swap.value == ""){alert('빈 곳이 있습니다'); return false;}

<c:forEach items="${flist}" var="flist">
if(insertForm.name.value=="${flist.name}"){
	alert('같은 이름이 있습니다. 다시 입력해 주세요.');
	return false;
}
</c:forEach>	
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
						<li>Flavor</li>
					</ol>
				</div>
				<div class="x_title">
					<h2>Flavor</h2>
				</div>
				<form name="rvForm">
					<div id="location_div">
						<p style="float: right;">
							<!-- 
									<input type="button" name="Insert" class="ct-btn white large"
										onclick="layer_open('layer2');return false;" value="추가">
									<input type="button" name="Delete" class="ct-btn white large"
										onclick="flavorDelete(this.form);return false" value="삭제">
										 -->
						</p>
					</div>
					<div id="flavor"></div>
				</form>

				</section>
			</div>
			</section>
		</div>
	</div>

	<%@include file="../include/footer.jsp"%>
	</div>

	<div class="layer">
		<div class="bg"></div>
		<div class="pop-layer" id="layer2">
			<div class="pop-container">
				<div class="pop-conts">
					<form name="insertForm" action="createFlavor.do" method="post"
						enctype="multipart/form-data" onsubmit="return frmsubmit();">
						<table>
							<tr>
								<th>Name</th>
								<td><input type="text" name="name" value=""></td>
							</tr>
							<tr>
								<th>VCPUs</th>
								<td><input type="text" name="vcpus"
									style="ime-mode: disabled" placeholder="ex)5,8" value=""
									onkeydown="return onlyNum(event)"></td>
							</tr>
							<tr>
								<th>RAM(MB)</th>
								<td><input type="text" name="ram"
									style="ime-mode: disabled" placeholder="ex)8192" value=""
									onkeydown="return onlyNum(event)"></td>
							</tr>
							<tr>
								<th>Disk(GB)</th>
								<td><input type="text" name="disk"
									style="ime-mode: disabled" placeholder="ex)40" value=""
									onkeydown="return onlyNum(event)"></td>
							</tr>
							<tr>
								<th>SWAP(MB)</th>
								<td><input type="text" name="swap"
									style="ime-mode: disabled" placeholder="ex)8192" value=""
									onkeydown="return onlyNum(event)"></td>
							</tr>
						</table>
						<div class="btn-r">
							<input class="cbtn" type="submit" value="Submit"> <a
								class="cbtn" href="#">Close</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>