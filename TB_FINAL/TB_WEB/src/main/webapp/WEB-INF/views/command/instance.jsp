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
<title>Instance</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">
<script src="/bctt/resources/js/layer.js"></script>
<script src="/bctt/resources/js/instance.js"></script>

<script>

	$(document).ready(function(){
	
	  //한글입력 안되게 처리
	  $("input[name=instance_name]").keyup(function(event){

	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
	    	var inputVal = $(this).val();
	    	$(this).val(inputVal.replace(/[^a-z0-9-_]/gi,''));
	   }
	  });
	});

	var flavor_list = null;
	var zone_list = null;

	$.ajax({
		url : "getFlavor.do",
		type : "get",
		dataType : "json",
		success : function(data) {
			var flavor_temp = "";
			var network_temp = "";
			var zone_temp = "";
			var image_temp = "";

			flavor_temp = "<option value=''>선택하세요.</option>";
			network_temp = "<option value=''>선택하세요.</option>";
			zone_temp = "<option value=''>선택하세요.</option>";
			image_temp = "<option value=''>선택하세요.</option>";

			if (data.result != "fail") {

				for (i = 0; i < data.flavor.length; i++) {
					flavor_temp += "<option value='" + data.flavor[i].name + "'><span style='font-weight:bold'>" + data.flavor[i].name + "</span></option>";
				}

				for (i = 0; i < data.network.length; i++) {
					network_temp += "<option value='" + data.network[i].network_id + "'><span style='font-weight:bold'>" + data.network[i].network_name + "</option>";
				}

				for (i = 0; i < data.zone.length; i++) {
					zone_temp += "<option value='" + data.zone[i].zone + "'><span style='font-weight:bold'>" + data.zone[i].zone + "</option>";
				}
				
				for (i = 0; i < data.image.length; i++) {
					image_temp += "<option value='" + data.image[i].id + "'><span style='font-weight:bold'>" + data.image[i].name + "</option>";
				}
			} else {
			}
			$("#network_select").html(network_temp);
			$("#flavor_select").html(flavor_temp);
			$("#zone_select").html(zone_temp);
			$("#image_select").html(image_temp);
		},

		beforeSend : function() {
		},
		complete : function() {
		}
	});
</script>


</head>
<body class="homepage">

	<div id="cover-spin"></div>

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
						<li>Instance</li>
					</ol>
				</div>

				<div class="x_title">
					<h2>Instance</h2>
				</div>

				<form name="rvForm">
					<div id="location_div">
						<p style="float: right;">
							<a href="#layer2" class="button-default button btn-example">추가</a>
							<a class="button-default button btn-delete">삭제</a>
						</p>
					</div>
					<div id="instance"></div>
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
			<div class="modal-header">
				<h1>Instance 생성</h1>
			</div>

			<div class="modal-body">
				<form id="regForm" action="createInstance.do" method="get" enctype="multipart/form-data" name="regForm">
					<div class="tab" style="display: inline;">
						<p>
							<label>인스턴스 이름</label>
						</p>
						<p>
							<input type="text" class="form-control" placeholder="인스턴스 이름" name="instance_name" required="required" maxlength="32">
						</p>
						<p>
							<label>Network</label>
						</p>
						<p>
							<select class="form-control" name="network_id" id="network_select">
							</select>
						</p>
						<p>
							<label>Flavor</label>
						</p>
						<p>
							<select class="form-control" name="flavor_name" id="flavor_select">
							</select>
						</p>
						<p>
							<label>Image</label>
						</p>
						<p>
							<select class="form-control" name="image_id" id="image_select">
							</select>
						</p>
						<p>
							<label>Availability Zone</label>
						</p>
						<p>
							<select class="form-control" name="zone_name" id="zone_select">
							</select>
						</p>
					</div>
				</form>
			</div>

			<div class="modal-footer">
				<button class="button-primary pull-left button btn-layerClose" type="button" id="closeBtn">취소</button>
				<button class="button-primary button" type="button" id="nextBtn" onclick="create()">생성</button>
			</div>

		</div>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
     //레이어
     $('.btn-example').click(function(){
          var $href = $(this).attr('href');
          layer_popup($href);
     });
     
     function vnfdDelete(form){
		var mylen = form.id.length;
		var gourl;
		
		if(mylen == "undefined"){
	  		mylen = 1;
	 	}
	 	for( var i=0; i<mylen; i++){
	  		if(form.id[i].checked == true) {
	  			gourl = form.id[i].value;
	   			break;
	  		}
	 	}
	 	if(i == mylen){
	  		alert("선택하세요!");
	  		return;
	 	}
	 	$('#cover-spin').show(0);
	 	
	 	document.rvForm.action = "deleteInstance.do?id="+gourl;
	 	document.rvForm.submit();
	 	
	 	$('#cover-spin').show(1);
	}
     
     
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
    			return;
    		}
    		</c:forEach>
    		}
    	}
    	 document.rvForm.action = "imageDelete.do?id="+gourl;
    	 document.rvForm.submit();
     }
     
     function create(){
    	 document.getElementById("regForm").submit();
    	 
    	 document.regForm.instance_name.value = "";
		 document.regForm.network_select.selectedIndex = 0;
		 document.regForm.flavor_select.selectedIndex = 0;
		 document.regForm.image_select.selectedIndex = 0;
		 document.regForm.zone_select.selectedIndex = 0;
			
    	 $('.dim-layer').fadeOut();
		 $('#cover-spin').show(1);
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
          
          $el.find('#closeBtn').click(function() {
			
        	document.regForm.instance_name.value = "";
			document.regForm.network_select.selectedIndex = 0;
			document.regForm.flavor_select.selectedIndex = 0;
			document.regForm.image_select.selectedIndex = 0;
			document.regForm.zone_select.selectedIndex = 0;
			
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
     
     $('.btn-delete').click(function() {
		var mylen =	$(':input[name="id"]:radio:checked').val();

		var gourl = 0;
		
		if(mylen == "undefined" || mylen == null){
	  		return;
	 	}else{
	 		gourl = mylen;
	 	}
					
		var isConfirm = confirm("선택한 목록을 삭제 하시겠습니까?");
		
		if (isConfirm) {
			vnfdDelete(document.rvForm);    
		}
		
		
	});
	
	</script>
</body>
</html>