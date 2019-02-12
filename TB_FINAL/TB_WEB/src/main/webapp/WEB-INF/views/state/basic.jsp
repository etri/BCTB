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
<title>기본</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">

<script type="text/javascript">
		var instance_id = ${param.instance_id};
		var instance_name = ${param.instance_name};
	</script>
	
	<script language=javascript>
	
	var ip_addr = "";
	var port = "";
	
		$(document).ready(function(){
			$.ajax({
				url : "getBasicList.do",
				type:"get",
				dataType:"json",
				success : function(data) {
					var temp="";
					
					var useCpu = 0;
					var totalCpu = 0;
					
					var useRam = 0;
					var totalRam = 0;
					
					var useDisk = 0;
					var totalDisk = 0;
					
					
					
					if(data.result!="fail"){
						temp += "<table>";
						temp += "<thead>";
						
						temp += "<tr>";
						temp += "<td class='table_count_td' colspan='2'>";
						temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
						temp += "</td>";
						temp += "</tr>";
						
						temp += "<tr>";
						temp += "<th width='10%'>이름</th>";
						temp += "<th width='15%'>값</th>";
						temp += "</tr>";
						temp += "</thead>";

						for(i=0; i<data.data.length; i++){
							if(i == 8 || i == 9){
								useCpu += data.data[i].useCpu;
								
								temp += "<tr>";
								temp += "<td>"+data.data[i].host+"</td>";
								temp += "<td>"+"<a href='#layer2' class='button-default button btn-example' onclick='open_popup()'>" + data.data[i].useCpu + "</a>"+"</td>";
								temp += "</tr>";
								
								if(i == 8){
									
									ip_addr = data.data[i].useCpu;
									$("#ip_addr").val(data.data[i].useCpu);	
								}
								if(i == 9){
									port = data.data[i].useCpu;
									$("#port").val(data.data[i].useCpu);	
								}
								
							}else{
								temp += "<tr>";
								temp += "<td>"+data.data[i].host+"</td>";
								temp += "<td>"+data.data[i].useCpu+"</td>";
								temp += "</tr>";
							}
						}
						
						temp +="</table>";
						
					}else{
						temp += "<table>";
						temp += "<thead>";
						temp += "<tr>";
						temp += "<th width='10%'>이름</th>";
						temp += "<th width='15%'>값</th>";
						temp += "</tr>";
						temp += "</thead>";
						temp += "<tbody>";
						temp += "<tr><td colspan='2'>데이터가 없습니다</td>";
						temp += "</tbody></table>";
					}
					$("#computenode0").html(temp);
				},
				beforeSend:function(){
					$('.wrap-loading').removeClass('display-none');
				},
				complete:function(){
					$('.wrap-loading').addClass('display-none');
				}
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
						<input type="radio" name="verticalMenu" id="management"/>
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
						<li>기본</li>
					</ol>
				</div>
				<div id="instance_name" class="x_title">
					<h2>기본</h2>
				</div>
				
				<div id="computenode0"></div>

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
				<h1>VNC 접속 정보 변경</h1>
			</div>

			<div class="modal-body">
				<form id="regForm" action="changeVnc.do" method="get" enctype="multipart/form-data" name="regForm">
					<div class="tab" style="display: inline;">
						<p>
							<label>IP</label>
						</p>
						<p>
							<input type="text" class="form-control" id="ip_addr" name="ip_addr" required="required" maxlength="32">
						</p>
						
						<p>
							<label>Port</label>
						</p>
						<p>
							<input type="text" class="form-control" id="port" name="port" required="required" maxlength="32">
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
		
		function open_popup() {
			layer_popup('#layer2');			
		}

		function layer_popup(el) {
			var $el = $(el); //레이어의 id를 $el 변수에 저장
			var isDim = $el.prev().hasClass('dimBg'); //dimmed 레이어를 감지하기 위한 boolean 변수
			
			document.regForm.ip_addr.value = ip_addr;
			document.regForm.port.value = port;

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