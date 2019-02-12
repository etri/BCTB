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
<title>Image</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">
<script src="/bctt/resources/js/layer.js"></script>
<script src="/bctt/resources/js/image.js"></script>

<script type="text/javascript">
function imageDelete(form){
 var mylen = form.id.length;
 var gourl;
 
 var check = false;
 for(var i= 0; i < mylen; i++){
 	if(form.id[i].checked==true){
 		check = true;
 	}
 }
 if(check == false){alert("삭제할 이미지를 선택하세요."); return}
 
 if(mylen == "undefined"){
	 mylen = 1;	 
 }
 
for(var i= 0; i < mylen; i++){
	  if(form.id[i].checked == true){
		  
	<c:forEach items="${vlist}" var="vlist">
	if(form.id[i].value != "${vlist.image_id}"){
		gourl = form.id[i].value;
	}
	else{
		alert('vnfd에서 사용중 입니다.');
		return;
	}
	</c:forEach>
	}
}
 document.rvForm.action = "imageDelete.do?id="+gourl;
 document.rvForm.submit();
}
</script>

<script type="text/javascript">
	function frmsubmit() {
		if (insertForm.name.value == "" || insertForm.os.value == "" || insertForm.desc.value == "" || insertForm.format.value == "" || insertForm.file.value == "") {
			alert('빈 곳이 있습니다'); return false;
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

		<!-- main -->
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
						<li>Image</li>
					</ol>
				</div>

				<div class="x_title">
					<h2>Image</h2>
				</div>

				<!-- 
					<div class="wrap-loading display-none">
					    <div><img src="/bctt/resources/img/loading_img.gif" /></div>
					</div>
					-->

				<form name="rvForm">
					<!--
						<div id="location_div">
							<p style="float: left;">
								<a href="#layer2" class="button-default button btn-example">추가</a>
								<a class="button-default button btn-delete">삭제</a>
							</p>
						</div>
						-->
					<div id="image"></div>
					<!-- 
						<input name="id" type="radio" value="" style="display: none">
						 -->
				</form>

				</section>
			</div>
			</section>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>


	<div class="dim-layer">
		<div class="dimBg"></div>
		<div id="layer2" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
					<form name="insertForm" action="imageSave.do" method="post"
						enctype="multipart/form-data" onsubmit="return frmsubmit();">
						<table>
							<tr>
								<th>Name</th>
								<td><input type="text" name="name" value=""></td>
							</tr>
							<tr>
								<th>OS</th>
								<td><input type="text" name="os" value=""></td>
							</tr>
							<tr>
								<th>Description</th>
								<td><input type="text" name="desc" value=""></td>
							</tr>
							<tr>
								<th>Format</th>
								<td><select name="format" style="width: 147px;">
										<option value="">선택</option>
										<option value="iso">ISO - Optical Disk Image</option>
										<option value="qcow2">QCOW2 - QEMU Emulator</option>
										<option value="raw">Raw</option>
								</select></td>
							</tr>

							<tr>
								<th>Image path</th>
								<td><input id="fileup1" name="file" type="file"></td>
							</tr>
						</table>
						<div class="btn-r">
							<a href="#" class="button-primary button btn-layerClose">생성</a> <a
								href="#" class="button-default button btn-layerClose">취소</a>
						</div>
				</div>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
     //레이어
     $('.btn-example').click(function(){
          var $href = $(this).attr('href');
          layer_popup($href);
     });
     
     $('.btn-delete').click(function(){
         var $href = $(this).attr('href');
         imageDelete($href);
    });
     
     
     function imageDelete(form){
    	 var mylen = form.id.length;
    	 var gourl;
    	 
    	 var check = false;
    	 for(var i= 0; i < mylen; i++){
    	 	if(form.id[i].checked==true){
    	 		check = true;
    	 	}
    	 }
    	 if(check == false){alert("삭제할 이미지를 선택하세요."); return}
    	 
    	 if(mylen == "undefined"){
    		 mylen = 1;	 
    	 }
    	 
    	for(var i= 0; i < mylen; i++){
    		  if(form.id[i].checked == true){
    			  
    		<c:forEach items="${vlist}" var="vlist">
    		if(form.id[i].value != "${vlist.image_id}"){
    			gourl = form.id[i].value;
    		}
    		else{
    			alert('vnfd에서 사용중 입니다.');
    			return;
    		}
    		</c:forEach>
    		}
    	}
    	 document.rvForm.action = "imageDelete.do?id="+gourl;
    	 document.rvForm.submit();
     }
     
     function layer_popup(el){

          var $el = $(el);        //레이어의 id를 $el 변수에 저장
          var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

          isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

          var $elWidth = ~~($el.outerWidth()),
              $elHeight = ~~($el.outerHeight()),
              docWidth = $(document).width(),
              docHeight = $(document).height();

          // 화면의 중앙에 레이어를 띄운다.
          if ($elHeight < docHeight || $elWidth < docWidth) {
               $el.css({
                    marginTop: -$elHeight /2,
                    marginLeft: -$elWidth/2
               })
          } else {
               $el.css({top: 0, left: 0});
          }

          $el.find('a.btn-layerClose').click(function(){
               isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
               return false;
          });

          $('.layer .dimBg').click(function(){
               $('.dim-layer').fadeOut();
               return false;
          });

     }
     
     //탭
     $(function(){
          $("ul.panel li:not("+$("ul.tab li a.selected").attr("href")+")").hide();
          $("ul.tab li a").click(function(){
               $("ul.tab li a").removeClass("selected");
               $(this).addClass("selected");
               $("ul.panel li").slideUp("fast");
               $($(this).attr("href")).slideDown("fast");
               return false;
          });
     });
	</script>
</body>
</html>