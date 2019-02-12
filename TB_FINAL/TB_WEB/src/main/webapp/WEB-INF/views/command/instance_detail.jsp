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
<title>Instance</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">
<script src="/bctt/resources/js/layer.js"></script>
	
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	var instance_id = ${param.instance_id};
	var instance_name = ${param.instance_name};
</script>
	
	
	
	<script>
		$(document).ready(function(){
			
			$("#instance_name").html("<h2>" + instance_name + "</h2>");
			
			var ip_list = null;

			$.ajax({
				url : "getFloatingIp.do",
				type : "get",
				dataType : "json",
				success : function(data) {
					ip_list = data.ip_list;
					
					$.ajax({
						url : "getInstanceDetail.do",
						type:"get",
						data:{
							instance_id: instance_id
						},
						dataType:"json",
						success : function(data) {
							var temp="";
							
							if(data.result!="fail"){
								
								if(data.data[0].image_name.indexOf("ethereum") != -1){
									$("#name").html("<a href='#layer2' class='button-default button btn-example' onclick='open_popup()'>" + data.data[0].name + "</a>");
								}else{
									$("#name").html(data.data[0].name);
								}
								
								$("#instance_id").val(data.data[0].id);
								
								$("#id").html(data.data[0].id);
								$("#status").html(data.data[0].status);
								$("#availability_zone").html(data.data[0].availability_zone);
								$("#created").html(data.data[0].created);
								$("#host").html(data.data[0].host);
								$("#flavor_name").html(data.data[0].flavor_name);
								$("#flavor_id").html(data.data[0].flavor_id);
								$("#vcpus").html(data.data[0].vcpus);
								$("#ram").html(data.data[0].ram);
								$("#root_disk").html(data.data[0].root_disk);
								
								$("#ip_type").html(data.data[0].addresses.split("=")[0]);
								$("#ip_address").html(data.data[0].addresses.split("=")[1]);
								
								$("#key_name").html(data.data[0].key_name);
								$("#image_name").html(data.data[0].image_name);
								$("#image_id").html(data.data[0].image_id);
								
								console.dir("data.data[0].addresses.split(',').length :" + data.data[0].addresses.split(',').length);
								
								if(data.data[0].addresses.split(',').length > 1){ //floatingIP가 있으면!!!
									$("#floating_ip").html("<a class='button-default button btn-example' onclick='release_ip()'>유동IP 연결 해제</a>");
								}else{
									
									var flavor_node_html = "";
									flavor_node_html += "<select class='form-control' name='ip_select' id='ip_select' style='float: left;width: 76%;'>";
									flavor_node_html += "<option value='IP 주소 선택'><span style='font-weight:bold'>IP 주소 선택</option>";
									for (j = 0; j<ip_list.length; j++) {
										flavor_node_html += "<option value='"+ip_list[j].ip_addr+"'><span style='font-weight:bold'>"+ip_list[j].ip_addr+"</option>";
									}	
									flavor_node_html += "</select>";
									flavor_node_html += "<a class='button-default button btn-example' onclick='match_ip()' style='margin-left:  5px;'>연결</a>";
									
									$("#floating_ip").html(flavor_node_html);
									
									//$("#floating_ip").html("<a class='button-default button btn-example' onclick='match_ip()'>연결</a>");
								}
								
								if(data.data[0].url == ""){
								}else{
									$("#console_url").html("<a href='http://" + data.data[0].ip_addr +":" + data.data[0].port + "/" + data.data[0].url + "' target='_blank'>"+"http://" + data.data[0].ip_addr +":" + data.data[0].port + "/" + data.data[0].url + "</a>");
								}

							}else{
							}
							
						},
					});
				},

				beforeSend : function() {
				},
				complete : function() {
				}
			});
		});
		
	</script>
	
</head>

<body class="homepage">

	<div id="cover-spin"></div>

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
				<div id="instance_name" class="x_title">
					<h2>Instance</h2>
				</div>

				<div id="instance" class="table_wrapper">
				
					<table class="instance_table">
					
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
							<th width="20%">Status</th>
							<td width="80%" id="status"></td>
						</tr>
						<tr>
							<th width="20%">가용 구역</th>
							<td width="80%" id="availability_zone"></td>
						</tr>
						<tr>
							<th width="20%">생성됨</th>
							<td width="80%" id="created"></td>
						</tr>
						<tr>
							<th width="20%">호스트</th>
							<td width="80%" id="host"></td>
						</tr>
						
						<thead>
							<tr>
								<td colspan="2">
									<h4>사양</h4>
								</td>
							</tr>
						</thead>
						
						<tr>
							<th width="20%">Flavor 이름</th>
							<td width="80%" id="flavor_name"></td>
						</tr>
						
						<tr>
							<th width="20%">Flavor ID</th>
							<td width="80%" id="flavor_id"></td>
						</tr>
						
						<tr>
							<th width="20%">RAM</th>
							<td width="80%" id="ram"></td>
						</tr>
						
						<tr>
							<th width="20%">VCPUs</th>
							<td width="80%" id="vcpus"></td>
						</tr>
						
						<tr>
							<th width="20%">디스크</th>
							<td width="80%" id="root_disk"></td>
						</tr>
						
						<thead>
							<tr>
								<td colspan="2">
									<h4>IP 주소</h4>
								</td>
							</tr>
						</thead>
						
						<tr>
							<th width="20%" id="ip_type"></th>
							<td width="80%" id="ip_address"></td>
						</tr>
						
						<tr>
							<th width="20%">유동IP</th>
							<td width="80%" id="floating_ip"></td>
						</tr>
						
						<thead>
							<tr>
								<td colspan="2">
									<h4>메타 데이터</h4>
								</td>
							</tr>
						</thead>
						
						<tr>
							<th width="20%">키 이름</th>
							<td width="80%" id="key_name"></td>
						</tr>
						
						<tr>
							<th width="20%">이미지 이름</th>
							<td width="80%" id="image_name"></td>
						</tr>
						
						<tr>
							<th width="20%">이미지 ID</th>
							<td width="80%" id="image_id"></td>
						</tr>
						
						<thead>
							<tr>
								<td colspan="2">
									<h4>콘솔</h4>
								</td>
							</tr>
						</thead>
						
						<tr>
							<th width="20%">URL</th>
							<td width="80%" id="console_url"></td>
						</tr>
						
					</table>	
				</div>

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
				<h1>이름 변경</h1>
			</div>

			<div class="modal-body">
				<form id="regForm" action="changeName.do" method="get" enctype="multipart/form-data" name="regForm">
					<div class="tab" style="display: inline;">
						<p>
							<label>새 이름</label>
						</p>
						<p>
							<input type="text" class="form-control" name="instance_name" required="required" maxlength="32">
							<input type="hidden" class="form-control" id="instance_id" name="instance_id" value="" required="required" maxlength="64">
						</p>
						
					</div>
				</form>
			</div>

			<div id="bottom_div" class="modal-footer">
				<button class="button-primary pull-left button btn-layerClose" type="button" id="closeBtn">취소</button>
				<button class="button-primary button" type="button" id="nextBtn">저장</button>
			</div>

		</div>
	</div>
	<script>
	
		function match_ip() {
			
			var langSelect = document.getElementById("ip_select");
			// select element에서 선택된 option의 value가 저장된다.
			var selectValue = langSelect.options[langSelect.selectedIndex].value;
			
			if(selectValue == "IP 주소 선택"){
				alert("IP주소를 선택해주세요.");
				return false;	
			}
			
			var ip_addr = document.getElementById("ip_address").innerHTML;
            var id = document.getElementById("id").innerHTML;
            
           	var form = document.createElement("form");
             	
            form.setAttribute("charset", "UTF-8");
            form.setAttribute("method", "Post");  //Post 방식
            form.setAttribute("action", "matchFlotingIp.do"); //요청 보낼 주소

            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("name", "ip_addr");
            hiddenField.setAttribute("value", ip_addr);
            form.appendChild(hiddenField);
            
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("name", "float_ip");
            hiddenField.setAttribute("value", selectValue);
            form.appendChild(hiddenField);

            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("name", "id");
            hiddenField.setAttribute("value", id);
            form.appendChild(hiddenField);

            document.body.appendChild(form);

            form.submit();
		}
		
		function release_ip() {
			var form = document.createElement("form");
          	
            form.setAttribute("charset", "UTF-8");
            form.setAttribute("method", "Post");  //Post 방식
            form.setAttribute("action", "releaseFlotingIp.do"); //요청 보낼 주소
            
            var ip_addr = document.getElementById("ip_address").innerHTML;
            var id = document.getElementById("id").innerHTML;
            
            if(ip_addr.split(',').length > 1){
            	 var hiddenField = document.createElement("input");
                 hiddenField.setAttribute("name", "ip_addr");
                 hiddenField.setAttribute("value", ip_addr.split(',')[1]);
                 form.appendChild(hiddenField);

                 var hiddenField = document.createElement("input");
                 hiddenField.setAttribute("name", "id");
                 hiddenField.setAttribute("value", id);
                 form.appendChild(hiddenField);

                 document.body.appendChild(form);

                 form.submit();
            }
		}
		
		function open_popup() {
			layer_popup('#layer2');			
		}

		function layer_popup(el) {
			var $el = $(el); //레이어의 id를 $el 변수에 저장
			var isDim = $el.prev().hasClass('dimBg'); //dimmed 레이어를 감지하기 위한 boolean 변수
	
			isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();
	
			var $elWidth = ~~($el.outerWidth()),
				$elHeight = ~~($el.outerHeight()),
				docWidth = $(document).width(),
				docHeight = $(document).height();
	
			// 화면의 중앙에 레이어를 띄운다.
			if ($elHeight < docHeight || $elWidth < docWidth) {
				$el.css({
					marginTop : -$elHeight / 2,
					marginLeft : -$elWidth / 2
				})
			} else {
				$el.css({
					top : 0,
					left : 0
				});
			}
			
			$el.find('#closeBtn').click(function() {
				isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				return false;
			});
			
			$el.find('#nextBtn').click(function() {
				
				$('#cover-spin').show(0);
				
				document.getElementById("regForm").submit();
				
				$('.dim-layer').fadeOut();
				
				$('#cover-spin').show(1);
			});
		}
	</script>
</body>
</html>