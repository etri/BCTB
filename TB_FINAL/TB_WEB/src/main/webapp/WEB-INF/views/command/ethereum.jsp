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
<title>Ethereum</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">
<script src="/bctt/resources/js/layer.js"></script>
<script src="/bctt/resources/js/ethereum.js"></script>

<script>

	var node_cnt = 0;
	var client_cnt = 0;

	$(document).ready(function(){
	
	  //한글입력 안되게 처리
	  $("input[name=profile_name]").keyup(function(event){

	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
	    	var inputVal = $(this).val();
	    	$(this).val(inputVal.replace(/[^a-z0-9-_]/gi,''));
	   }
	  });
	  
	  $("input[name=description]").keyup(function(event){

		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    	var inputVal = $(this).val();
		    	$(this).val(inputVal.replace(/[^a-z0-9-_]/gi,''));
		   }
		  });
	  
	  $("input[name=disk_size]").keyup(function(event){

		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    	var inputVal = $(this).val();
		    	$(this).val(inputVal.replace(/[^0-9]/gi,''));
		   }
		  });
	  
	  $("input[name=mem_size]").keyup(function(event){

		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    	var inputVal = $(this).val();
		    	$(this).val(inputVal.replace(/[^0-9]/gi,''));
		   }
		  });
	  
	  $("input[name=num_cpus]").keyup(function(event){

		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    	var inputVal = $(this).val();
		    	$(this).val(inputVal.replace(/[^0-9]/gi,''));
		   }
		  });
	  
	  $("input[name=chainid]").keyup(function(event){

		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    	var inputVal = $(this).val();
		    	$(this).val(inputVal.replace(/[^0-9]/gi,''));
		   }
		  });
	  
	  $("input[name=difficulty]").keyup(function(event){

		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    	var inputVal = $(this).val();
		    	$(this).val(inputVal.replace(/[^0-9]/gi,''));
		   }
		  });
	  
	  $("input[name=gaslimit]").keyup(function(event){

		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    	var inputVal = $(this).val();
		    	$(this).val(inputVal.replace(/[^0-9]/gi,''));
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

			flavor_list = data.flavor;
			zone_list = data.zone;

			network_temp = "<option value=''>선택하세요.</option>";
			zone_temp = "<option value=''>선택하세요.</option>";

			if (data.result != "fail") {

				for (i = 0; i < data.flavor.length; i++) {
					flavor_temp += "<option value='" + data.flavor[i].name + "'><span style='font-weight:bold'>" + data.flavor[i].name + "</span></option>";
				}

				for (i = 0; i < data.network.length; i++) {
					network_temp += "<option value='" + data.network[i].network_name + "'><span style='font-weight:bold'>" + data.network[i].network_name + "</option>";
				}

				for (i = 0; i < data.zone.length; i++) {
					zone_temp += "<option value='" + data.zone[i].zone + "'><span style='font-weight:bold'>" + data.zone[i].zone + "</option>";
				}
			} else {
			}
			$("#net_select").html(network_temp);
		},

		beforeSend : function() {
		},
		complete : function() {
		}
	});

	var currentTab = 0; // Current tab is set to be the first tab (0)
	showTab(currentTab); // Display the crurrent tab

	function showTab(n) {
		
		// This function will display the specified tab of the form...
		var x = document.getElementsByClassName("tab");
		x[n].style.display = "block";

		//... and fix the Previous/Next buttons:

		if (n == 0) {
			document.getElementById("prevBtn").style.display = "none";
		} else {
			document.getElementById("prevBtn").style.display = "inline";
		}

		if (n == (x.length - 1)) {
			document.getElementById("nextBtn").innerHTML = "생성";
		} else {
			document.getElementById("nextBtn").innerHTML = "다음";
		}

		//... and run a function that will display the correct step indicator:
		fixStepIndicator(n)
	}

	function nextPrev(n) {
		
		// This function will figure out which tab to display
		var x = document.getElementsByClassName("tab");
		// Exit the function if any field in the current tab is invalid:

		var langSelect = document.getElementById("network_select");
		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = langSelect.options[langSelect.selectedIndex].value;

		if (selectValue == "private") {
			if (n == 1 && !validateForm()) return false;
		}
		
		// Hide the current tab:
		x[currentTab].style.display = "none";
		// Increase or decrease the current tab by 1:
		currentTab = currentTab + n;
		
		if(currentTab == 2){
			if(document.regForm.node_cnt.value <= 0 || document.regForm.node_cnt.value > 4){
				alert("부트스트랩 노드수는 1 ~ 4 사이의 값으로 입력해주세요.");
				currentTab = currentTab - n;
				x[currentTab].style.display = "block";		
				return false;
			}
			
			if(document.regForm.client_cnt.value <= 0 || document.regForm.client_cnt.value > 4){
				alert("클라이언트 수는 1 ~ 4 사이의 값으로 입력해주세요.");
				currentTab = currentTab - n;
				x[currentTab].style.display = "block";		
				return false;
			}	
			
			if(document.regForm.net_select.value == ""){
				alert("네트워크를 선택해주세요.");
				currentTab = currentTab - n;
				x[currentTab].style.display = "block";		
				return false;
			}
			
			var flavor_node_html = "";
			var flavor_client_html = "";
			
			for (i = 0; i < document.regForm.node_cnt.value; i++) {
				flavor_node_html += "<table class='flavor_table'>";
				flavor_node_html += "<thead>";
				flavor_node_html += "<tr>";
				flavor_node_html += "<th>Flavor(node"+i+")</th>";
				flavor_node_html += "<th>VCPUs</th>";
				flavor_node_html += "<th>Memory</th>";
				flavor_node_html += "<th>Disk</th>";
				flavor_node_html += "</tr>";
				flavor_node_html += "<tr>";
				flavor_node_html += "<td>";
				flavor_node_html += "<select class='form-control' name='flavor_select_node_"+i+"' id='flavor_select_node_"+i+"' onchange='changeNodeFlavorSelect(this)'>";
				flavor_node_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
				for (j = 0; j < flavor_list.length; j++) {
					flavor_node_html += "<option value='" + flavor_list[j].name + "'><span style='font-weight:bold'>" + flavor_list[j].name + "</option>";
				}	
				flavor_node_html += "</select>";
				flavor_node_html += "</td>";
				
				flavor_node_html += "<td>";
				flavor_node_html += "<input class='form-control' name='node_num_cpus_"+i+"' id='node_num_cpus_"+i+"' type='number'>";
				flavor_node_html += "</td>";
				
				flavor_node_html += "<td>";
				flavor_node_html += "<input class='form-control' name='node_mem_size_"+i+"' id='node_mem_size_"+i+"' type='number'>";
				flavor_node_html += "</td>";
				
				flavor_node_html += "<td>";
				flavor_node_html += "<input class='form-control' name='node_disk_size_"+i+"' id='node_disk_size_"+i+"' type='number'>";
				flavor_node_html += "</td>";
				
				flavor_node_html += "</tr>";
				flavor_node_html += "</thead>";
				flavor_node_html += "</table>";
			}
			
			for (i = 0; i < document.regForm.client_cnt.value; i++) {
				flavor_client_html += "<table class='flavor_table'>";
				flavor_client_html += "<thead>";
				flavor_client_html += "<tr>";
				flavor_client_html += "<th>Flavor(client"+i+")</th>";
				flavor_client_html += "<th>VCPUs</th>";
				flavor_client_html += "<th>Memory</th>";
				flavor_client_html += "<th>Disk</th>";
				flavor_client_html += "</tr>";
				flavor_client_html += "<tr>";
				flavor_client_html += "<td>";
				flavor_client_html += "<select class='form-control' name='flavor_select_client_"+i+"' id='flavor_select_client_"+i+"' onchange='changeClientFlavorSelect(this)'>";
				flavor_client_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
				for (j = 0; j < flavor_list.length; j++) {
					flavor_client_html += "<option value='" + flavor_list[j].name + "'><span style='font-weight:bold'>" + flavor_list[j].name + "</option>";
				}	
				flavor_client_html += "</select>";
				flavor_client_html += "</td>";
				
				flavor_client_html += "<td>";
				flavor_client_html += "<input class='form-control' name='client_num_cpus_"+i+"' id='client_num_cpus_"+i+"' type='number'>";
				flavor_client_html += "</td>";
				
				flavor_client_html += "<td>";
				flavor_client_html += "<input class='form-control' name='client_mem_size_"+i+"' id='client_mem_size_"+i+"' type='number'>";
				flavor_client_html += "</td>";
				
				flavor_client_html += "<td>";
				flavor_client_html += "<input class='form-control' name='client_disk_size_"+i+"' id='client_disk_size_"+i+"' type='number'>";
				flavor_client_html += "</td>";
				
				flavor_client_html += "</tr>";
				flavor_client_html += "</thead>";
				flavor_client_html += "</table>";
			}
			
			$("#flavor_node_div").html(flavor_node_html);
			$("#flavor_client_div").html(flavor_client_html);
		}
		
		if(currentTab == 4){
			
			node_cnt = document.regForm.node_cnt.value;
			client_cnt = document.regForm.client_cnt.value;
			
			var zone_html = "";
			
			for (i = 0; i < document.regForm.node_cnt.value; i++) {
				zone_html += "<p><label>Availability Zone(node"+i+")</label></p>";
				zone_html += "<p><select class='form-control' name='zone_select_node_"+i+"' id='zone_select_node_"+i+"'>";
				for (j = 0; j < zone_list.length; j++) {
					zone_html += "<option value='" + zone_list[j].zone + "'><span style='font-weight:bold'>" + zone_list[j].zone + "</option>";
				}	
				zone_html += "</select></p>";
			}
			
			for (i = 0; i < document.regForm.client_cnt.value; i++) {
				zone_html += "<p><label>Availability Zone(client"+i+")</label></p>";
				zone_html += "<p><select class='form-control' name='zone_select_client_"+i+"' id='zone_select_client_"+i+"'>";
				for (j = 0; j < zone_list.length; j++) {
					zone_html += "<option value='" + zone_list[j].zone + "'><span style='font-weight:bold'>" + zone_list[j].zone + "</option>";
				}	
				zone_html += "</select></p>";
			}
			
			$("#zone_div").html(zone_html);
		}
		
		// if you have reached the end of the form...
		if (currentTab >= x.length) {
			// ... the form gets submitted:
				
			$('#cover-spin').show(0);
		
			document.getElementById("regForm").submit();
			document.getElementById("bottom_div").style.display = "none";
			document.getElementById("step_div").style.display = "none";
			
			document.regForm.profile_name.value = "";
			document.regForm.description.value = "";
			document.regForm.network_select.selectedIndex = 0;
			document.regForm.node_cnt.value = "";
			document.regForm.client_cnt.value = "";
			document.regForm.chainid.value = "";
			document.regForm.difficulty.value = "";
			document.regForm.gaslimit.value = "";
			
			document.regForm.net_select.selectedIndex = 0;
			
			$('.dim-layer').fadeOut();
			
			$('#cover-spin').show(1);

			if (currentTab == x.length) {
				return false;
			}
		}
		// Otherwise, display the correct tab:
		showTab(currentTab);
	}

	function validateForm() {
		// This function deals with validation of the form fields
		var x,
			y,
			i,
			valid = true;
		x = document.getElementsByClassName("tab");
		
		y = x[currentTab].getElementsByTagName("input");
		
		if(currentTab == 5){ //zone선택창
			valid = false;
		}else{
			// A loop that checks every input field in the current tab:
			for (i = 0; i < y.length; i++) {
				// If a field is empty...
				if (y[i].value == "") {
					// add an "invalid" class to the field:
					y[i].className += " invalid";
					// and set the current valid status to false
					valid = false;
				}
			}
		}
		
		// If the valid status is true, mark the step as finished and valid:
		if (valid) {
			document.getElementsByClassName("step")[currentTab].className += " finish";
		}
		return valid; // return the valid status
	}

	function fixStepIndicator(n) {
		
		// This function removes the "active" class of all steps...
		var i, x = document.getElementsByClassName("step");
		
		for (i = 0; i < x.length; i++) {
			x[i].className = x[i].className.replace(" active", "");
		}
		//... and adds the "active" class on the current step:
		x[n].className += " active";
	}

	function chageLangSelect() {
		var langSelect = document.getElementById("network_select");

		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = langSelect.options[langSelect.selectedIndex].value;

		if (selectValue == "private") {
			$("#chainid").css("display", "block");
			$("#difficulty").css("display", "block");
			$("#gaslimit").css("display", "block");

			$("#chainid_lbl").css("display", "block");
			$("#diff_lbl").css("display", "block");
			$("#gas_lbl").css("display", "block");

		} else if (selectValue == "testnet") {
			$("#chainid").css("display", "none");
			$("#difficulty").css("display", "none");
			$("#gaslimit").css("display", "none");

			$("#chainid_lbl").css("display", "none");
			$("#diff_lbl").css("display", "none");
			$("#gas_lbl").css("display", "none");
		}
	}

	function frmsubmit() {
		
		$.ajax({
			url : "getEthereum.do",
			type : "get",
			dataType : "json",
			success : function(data) {
				var temp = "";
				if (data.result != "fail") {
					temp += "<table>";
					temp += "<thead>";

					temp += "<tr>";
					temp += "<td class='table_count_td' colspan='16'>";
					temp += "<span class='table_count'>Displaying " + data.data.length + " items</span>";
					temp += "</td>";
					temp += "</tr>";

					temp += "<tr>";
					temp += "<th width='5%'>선택</th>"
					temp += "<th width='5%'>프로파일 명</th>"
					temp += "<th width='15%'>생성시간</th>";
					temp += "<th width='5%'>상태</th>";
					temp += "<th width='5%'>설명</th>";
					temp += "<th width='10%'>네트워크 타입</th>";
					temp += "<th width='10%'>부트스트랩 노드수</th>";
					temp += "<th width='5%'>클라이언트 수</th>";
					temp += "<th width='5%'>ChainID</th>";
					temp += "<th width='5%'>Difficulty</th>";
					temp += "<th width='5%'>GasLimit</th>";
					temp += "<th width='5%'>Flavor</th>";
					temp += "<th width='5%'>CPU</th>";
					temp += "<th width='5%'>MEM</th>";
					temp += "<th width='5%'>DISK</th>";
					temp += "<th width='10%'>Network</th>";
					temp += "</tr>";
					temp += "</thead>";
					for (i = 0; i < data.data.length; i++) {
						temp += "<tr>";
						temp += "<td><input type='radio' name='idx' value='"+data.data[i].idx+"'></td>";
						temp += "<td>" + data.data[i].profile_name + "</td>";
						temp += "<td><a href='/bctt/command/vnfDetail.do?vnf_id=\""+data.data[i].vnf_id+"\"'>"+data.data[i].profile_name+"</a></td>";
						temp += "<td>" + data.data[i].time_stamp + "</td>";
						temp += "<td>" + data.data[i].status + "</td>";
						temp += "<td>" + data.data[i].description + "</td>";
						temp += "<td>" + data.data[i].network_type + "</td>";
						temp += "<td>" + data.data[i].node_cnt + "</td>";
						temp += "<td>" + data.data[i].client_cnt + "</td>";
						temp += "<td>" + data.data[i].chainid + "</td>";
						temp += "<td>" + data.data[i].difficulty + "</td>";
						temp += "<td>" + data.data[i].gaslimit + "</td>";
						temp += "<td>" + data.data[i].flavor_name + "</td>";
						temp += "<td>" + data.data[i].num_cpus + "</td>";
						temp += "<td>" + data.data[i].mem_size + "</td>";
						temp += "<td>" + data.data[i].disk_size + "</td>";
						temp += "<td>" + data.data[i].network_name + "</td>";
						temp += "</tr>";
					}
					temp += "</table>";
				} else {
					temp += "<table>";
					temp += "<thead>";
					temp += "<tr>";
					temp += "<th width='5%'>선택</th>"
					temp += "<th width='5%'>프로파일 명</th>"
					temp += "<th width='15%'>생성시간</th>";
					temp += "<th width='5%'>상태</th>";
					temp += "<th width='5%'>설명</th>";
					temp += "<th width='10%'>네트워크 타입</th>";
					temp += "<th width='10%'>부트스트랩 노드수</th>";
					temp += "<th width='5%'>클라이언트 수</th>";
					temp += "<th width='5%'>ChainID</th>";
					temp += "<th width='5%'>Difficulty</th>";
					temp += "<th width='5%'>GasLimit</th>";
					temp += "<th width='5%'>Flavor</th>";
					temp += "<th width='5%'>CPU</th>";
					temp += "<th width='5%'>MEM</th>";
					temp += "<th width='5%'>DISK</th>";
					temp += "<th width='10%'>Network</th>";
					temp += "</tr>";
					temp += "</thead>";
					temp += "<tbody>";
					temp += "<tr><td colspan='16'>데이터가 없습니다</td>";
					temp += "</tbody></table>";
				}
				$("#ethereum").html(temp);
			},
			beforeSend : function() {
				$('.wrap-loading').removeClass('display-none');
			},
			complete : function() {
				$('.wrap-loading').addClass('display-none');
			}
		});
	}
	
	function changeNodeFlavorSelect(flavor) {
		var flavor_select = document.getElementById(flavor.id);
		
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;
		
		var idx = flavor.id.substring(19, 20);

		if (selectValue == "직접입력") {
			
			$("#node_num_cpus_"+idx).val("");
			$("#node_mem_size_"+idx).val("");
			$("#node_disk_size_"+idx).val("");
			
			$("#node_num_cpus_"+idx).prop("disabled", false);
			$("#node_mem_size_"+idx).prop("disabled", false);
			$("#node_disk_size_"+idx).prop("disabled", false);
			
		} else {
			
			$("#node_num_cpus_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#node_mem_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#node_disk_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#node_num_cpus_"+idx).prop("disabled", true);
			$("#node_mem_size_"+idx).prop("disabled", true);
			$("#node_disk_size_"+idx).prop("disabled", true);
		}
	}
	
	function changeClientFlavorSelect(flavor) {
		var flavor_select = document.getElementById(flavor.id);
		
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;
		
		var idx = flavor.id.substring(21, 22);

		if (selectValue == "직접입력") {
			
			$("#client_num_cpus_"+idx).val("");
			$("#client_mem_size_"+idx).val("");
			$("#client_disk_size_"+idx).val("");
			
			$("#client_num_cpus_"+idx).prop("disabled", false);
			$("#client_mem_size_"+idx).prop("disabled", false);
			$("#client_disk_size_"+idx).prop("disabled", false);
			
		} else {
			
			$("#client_num_cpus_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#client_mem_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#client_disk_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#client_num_cpus_"+idx).prop("disabled", true);
			$("#client_mem_size_"+idx).prop("disabled", true);
			$("#client_disk_size_"+idx).prop("disabled", true);
		}
	}

	//텍스트 박스에 숫자와 영문만 입력할수있도록

	function nonHangulSpecialKey() {
		if (check_key() != 1 && check_key() != 2) {
			event.returnValue = false;
			return;
		}
	}

	function check_key() {
		var char_ASCII = event.keyCode;

		//숫자
		if (char_ASCII >= 48 && char_ASCII <= 57)
			return 1;
		//영어
		else if ((char_ASCII >= 65 && char_ASCII <= 90) || (char_ASCII >= 97 && char_ASCII <= 122))
			return 2;
		//특수기호
		else if ((char_ASCII >= 33 && char_ASCII <= 47) || (char_ASCII >= 58 && char_ASCII <= 64)
			|| (char_ASCII >= 91 && char_ASCII <= 96) || (char_ASCII >= 123 && char_ASCII <= 126))
			return 4;
		//한글
		else if ((char_ASCII >= 12592) || (char_ASCII <= 12687))
			return 3;
		else
			return 0;
	}
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

		<div id="main">
			<nav id="nav" class="vertical">
				<ul>
					<li><label for="home">프로젝트</label> <input type="radio"
						name="verticalMenu" id="home" checked="checked"/>
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
								<li>Ethereum</li>
							</ol>
						</div>

						<div class="x_title">
							<h2>Ethereum</h2>
						</div>

						<form name="rvForm" id="rvForm">
							<div id="location_div">
								<p style="float: right;">
									<a href="#layer2" class="button-default button btn-example">추가</a>
									<a class="button-default button btn-delete">삭제</a>
								</p>
							</div>
							<div id="ethereum"></div>
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
				<h1>Ethereum 자동화 테스트</h1>
			</div>

			<div class="modal-body">
				<form id="regForm" action="createEthereum.do" method="get" enctype="multipart/form-data" name="regForm">
					<div class="tab" style="display: inline;">
						<p>
							<label>프로파일명</label>
						</p>
						<p>
							<input type="text" class="form-control" placeholder="프로파일 명" name="profile_name" required="required" maxlength="32">
						</p>
						<p>
							<label>설명</label>
						</p>
						<p>
							<input class="form-control" placeholder="설명" name="description" required="required" maxlength="128">
						</p>
					</div>

					<div class="tab">
						<p>
							<label>테스트 네트워크 타입</label>
						</p>
						<p>
							<select class="form-control" id="network_select"
								onchange="chageLangSelect()" name="network_select">
								<option label="Private" value="private" selected="selected">Private</option>
								<option label="Public / Testnet" value="testnet">Public / Testnet</option>
							</select>
						</p>

						<p id="node_lbl">
							<label>부트스트랩 노드 수</label>
						</p>
						<p id="node_cnt"><input class="form-control" id="node-count" name="node_cnt" type="number" min="1" max="4"></p>

						<p id="client_lbl">
							<label>클라이언트 수</label>
						</p>
						<p id="client_cnt">
							<input class="form-control" id="client-count" name="client_cnt" type="number" min="1" max="4">
						</p>

						<p id="chainid_lbl">
							<label>ChainID</label>
						</p>
						<p id="chainid">
							<input class="form-control" placeholder="Chain Id" name="chainid" min="1" max="8">
						</p>

						<p id="diff_lbl">
							<label>Difficulty</label>
						</p>
						<p id="difficulty">
							<input class="form-control" placeholder="Difficulty" name="difficulty" min="1" max="8">
						</p>

						<p id="gas_lbl">
							<label>Gas Limit</label>
						</p>
						<p id="gaslimit">
							<input class="form-control" placeholder="Gas Limit" name="gaslimit" min="1" max="8">
						</p>
						
						<p>
							<label>Network</label>
						</p>
						<p>
							<select class="form-control" name="net_select" id="net_select">
							</select>
						</p>

					</div>

					<div class="tab" id="flavor_node_div">
					</div>
					
					<div class="tab" id="flavor_client_div">
					</div>
					 
					<div class="tab" id="zone_div">
					</div>

					<div id="step_div" style="text-align: center; position: absolute; bottom: 10; margin: 0 auto; width: 90%">
						<div style="width: 100%; margin: 0 auto">
							<span class="step"> </span> 
							<span class="step"> </span> 
							<span class="step"> </span>
							<span class="step"> </span>
							<span class="step"> </span>
						</div>
					</div>
				</form>
			</div>

			<div id="bottom_div" class="modal-footer">
				<button class="button-primary pull-left button btn-layerClose"
					type="button" id="closeBtn">취소</button>
				<button style="display: none;" class="button-primary button"
					type="button" id="prevBtn" onclick="nextPrev(-1)">이전</button>
				<button class="button-primary button" type="button" id="nextBtn"
					onclick="nextPrev(1)">다음</button>
			</div>

		</div>

		<script src="http://code.jquery.com/jquery-latest.min.js"></script>

		<script>
		//레이어
		$('.btn-example').click(function() {
			var $href = $(this).attr('href');
			layer_popup($href);
		});
		function layer_popup(el) {
			currentTab = 0;
			
			var x = document.getElementsByClassName("tab");
			x[0].style.display = "block";
			x[1].style.display = "none";
			x[2].style.display = "none";
			
			showTab(currentTab);
			
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
				
				document.regForm.profile_name.value = "";
				document.regForm.description.value = "";
				document.regForm.network_select.selectedIndex = 0;
				document.regForm.node_cnt.value = "";
				document.regForm.client_cnt.value = "";
				document.regForm.chainid.value = "";
				document.regForm.difficulty.value = "";
				document.regForm.gaslimit.value = "";
				
				//document.regForm.flavor_select.selectedIndex = 0;
				document.regForm.net_select.selectedIndex = 0;
				//document.regForm.zone_select.selectedIndex = 0;
				
				isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				return false;
			});
	
			$('.layer .dimBg').click(function() {
				$('.dim-layer').fadeOut();
				return false;
			});
	
		}
	
		//탭
		$(function() {
			$("ul.panel li:not(" + $("ul.tab li a.selected").attr("href") + ")").hide();
			$("ul.tab li a").click(function() {
				$("ul.tab li a").removeClass("selected");
				$(this).addClass("selected");
				$("ul.panel li").slideUp("fast");
				$($(this).attr("href")).slideDown("fast");
				return false;
			});
		});
		
		$('.btn-delete').click(function() {
						
			var mylen =	$(':input[name="idx"]:radio:checked').val();

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
		
		function vnfdDelete(form){
			var mylen = form.idx.length;
			var gourl;
			
			if(mylen == "undefined"){
		  		mylen = 1;
		 	}
		 	for( var i=0; i<mylen; i++){
		  		if(form.idx[i].checked == true) {
		  			gourl = form.idx[i].value;
		   			break;
		  		}
		 	}
		 	if(i == mylen){
		  		alert("선택하세요!");
		  		return;
		 	}
		 	$('#cover-spin').show(0);
		 	
		 	document.rvForm.action = "deleteEthereum.do?id="+gourl;
		 	document.rvForm.submit();
		 	
		 	$('#cover-spin').show(1);
		}
		
	</script>
</body>
</html>