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
<title>Hyperledger Fabric</title>
<link rel="stylesheet" type="text/css"
	href="/bctt/resources/css/style.css">
<script src="/bctt/resources/js/layer.js"></script>
<script src="/bctt/resources/js/hlf.js"></script>

<script>

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
			$("#network_select").html(network_temp);
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

		if (n == 1 && !validateForm()) return false;
		
		// Hide the current tab:
		x[currentTab].style.display = "none";
		// Increase or decrease the current tab by 1:
		currentTab = currentTab + n;
		
		if(currentTab == 2){
			if(document.regForm.orderer_cnt.value <= 0 || document.regForm.orderer_cnt.value > 2){
				alert("Orderer수는 1 ~ 2 사이의 값으로 입력해주세요.");
				currentTab = currentTab - n;
				x[currentTab].style.display = "block";		
				return false;
			}
			
			if(document.regForm.peer_org_cnt.value <= 0 || document.regForm.peer_org_cnt.value > 2){
				alert("Org 수는 1 ~ 2 사이의 값으로 입력해주세요.");
				currentTab = currentTab - n;
				x[currentTab].style.display = "block";		
				return false;
			}
			if(document.regForm.org_peer_cnt.value <= 0 || document.regForm.org_peer_cnt.value > 2){
				alert("Org별 Peer 수는 1 ~ 2 사이의 값으로 입력해주세요.");
				currentTab = currentTab - n;
				x[currentTab].style.display = "block";		
				return false;
			}
			
			var flavor_orderer_html = "";
			var flavor_peer_html = "";
			
			
			/*일괄적용*/
			flavor_orderer_html += "<table class='flavor_table'>";
			flavor_orderer_html += "<thead>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<th width='25%'>Flavor(일괄적용)</th>";
			flavor_orderer_html += "<th width='25%' style='visibility:hidden;'>VCPUs(EA)</th>";
			flavor_orderer_html += "<th width='25%' style='visibility:hidden;'>Memory(GB)</th>";
			flavor_orderer_html += "<th width='25%' style='visibility:hidden;'>Disk<(TB)/th>";
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<select class='form-control' name='flavor_select_all' id='flavor_select_all' onchange='changeAllFlavorSelect(this)'>";
			flavor_orderer_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
			for (j = 0; j < flavor_list.length; j++) {
				flavor_orderer_html += "<option value='" + flavor_list[j].name + "'><span style='font-weight:bold'>" + flavor_list[j].name + "</option>";
			}	
			flavor_orderer_html += "</select>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td style='visibility:hidden;'>";
			flavor_orderer_html += "<input class='form-control' type='number' value='0'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td style='visibility:hidden;'>";
			flavor_orderer_html += "<input class='form-control' type='number' value='0'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td style='visibility:hidden;'>";
			flavor_orderer_html += "<input class='form-control' type='number' value='0'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "</thead>";
			flavor_orderer_html += "</table>";
			/*일괄적용*/ 
			
			flavor_orderer_html += "<table class='flavor_table'>";
			flavor_orderer_html += "<thead>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<th width='25%'>Flavor(adminordererorg0)</th>";
			flavor_orderer_html += "<th width='25%'>VCPUs</th>";
			flavor_orderer_html += "<th width='25%'>Memory</th>";
			flavor_orderer_html += "<th width='25%'>Disk</th>";
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<select class='form-control' name='flavor_select_adminordererorg0' id='flavor_select_adminordererorg0' onchange='changeAdminOrdererOrgFlavorSelect(this)'>";
			flavor_orderer_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
			for (j = 0; j < flavor_list.length; j++) {
				flavor_orderer_html += "<option value='" + flavor_list[j].name + "'><span style='font-weight:bold'>" + flavor_list[j].name + "</option>";
			}	
			flavor_orderer_html += "</select>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='adminordererorg0_num_cpus' id='adminordererorg0_num_cpus' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='adminordererorg0_mem_size' id='adminordererorg0_mem_size' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='adminordererorg0_disk_size' id='adminordererorg0_disk_size' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "</thead>";
			flavor_orderer_html += "</table>";
			
			for (i = 0; i < document.regForm.orderer_cnt.value; i++) {
				flavor_orderer_html += "<table class='flavor_table'>";
				flavor_orderer_html += "<thead>";
				flavor_orderer_html += "<tr>";
				flavor_orderer_html += "<th width='25%'>Flavor(orderer"+i+")</th>";
				flavor_orderer_html += "<th width='25%'>VCPUs</th>";
				flavor_orderer_html += "<th width='25%'>Memory</th>";
				flavor_orderer_html += "<th width='25%'>Disk</th>";
				flavor_orderer_html += "</tr>";
				flavor_orderer_html += "<tr>";
				flavor_orderer_html += "<td>";
				flavor_orderer_html += "<select class='form-control' name='flavor_select_orderer_"+i+"' id='flavor_select_orderer_"+i+"' onchange='changeOrdererFlavorSelect(this)'>";
				flavor_orderer_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
				for (j = 0; j < flavor_list.length; j++) {
					flavor_orderer_html += "<option value='" + flavor_list[j].name + "'><span style='font-weight:bold'>" + flavor_list[j].name + "</option>";
				}	
				flavor_orderer_html += "</select>";
				flavor_orderer_html += "</td>";
				
				flavor_orderer_html += "<td>";
				flavor_orderer_html += "<input class='form-control' name='orderer_num_cpus_"+i+"' id='orderer_num_cpus_"+i+"' type='number'>";
				flavor_orderer_html += "</td>";
				
				flavor_orderer_html += "<td>";
				flavor_orderer_html += "<input class='form-control' name='orderer_mem_size_"+i+"' id='orderer_mem_size_"+i+"' type='number'>";
				flavor_orderer_html += "</td>";
				
				flavor_orderer_html += "<td>";
				flavor_orderer_html += "<input class='form-control' name='orderer_disk_size_"+i+"' id='orderer_disk_size_"+i+"' type='number'>";
				flavor_orderer_html += "</td>";
				
				flavor_orderer_html += "</tr>";
				flavor_orderer_html += "</thead>";
				flavor_orderer_html += "</table>";
			}
			
			flavor_orderer_html += "<table class='flavor_table'>";
			flavor_orderer_html += "<thead>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<th width='25%'>Flavor(kafka-zookeeper)</th>";
			flavor_orderer_html += "<th width='25%'>VCPUs</th>";
			flavor_orderer_html += "<th width='25%'>Memory</th>";
			flavor_orderer_html += "<th width='25%'>Disk</th>";
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<select class='form-control' name='flavor_select_kafka' id='flavor_select_kafka' onchange='changeKafkaFlavorSelect(this)'>";
			flavor_orderer_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
			for (j = 0; j < flavor_list.length; j++) {
				flavor_orderer_html += "<option value='" + flavor_list[j].name + "'><span style='font-weight:bold'>" + flavor_list[j].name + "</option>";
			}	
			flavor_orderer_html += "</select>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='kafka_num_cpus' id='kafka_num_cpus' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='kafka_mem_size' id='kafka_mem_size' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='kafka_disk_size' id='kafka_disk_size' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "</thead>";
			flavor_orderer_html += "</table>";
			
			flavor_orderer_html += "<table class='flavor_table'>";
			flavor_orderer_html += "<thead>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<th width='25%'>Flavor(bcmanager)</th>";
			flavor_orderer_html += "<th width='25%'>VCPUs</th>";
			flavor_orderer_html += "<th width='25%'>Memory</th>";
			flavor_orderer_html += "<th width='25%'>Disk</th>";
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "<tr>";
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<select class='form-control' name='flavor_select_bcmanager' id='flavor_select_bcmanager' onchange='changeBcmanagerFlavorSelect(this)'>";
			flavor_orderer_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
			for (j = 0; j < flavor_list.length; j++) {
				flavor_orderer_html += "<option value='" + flavor_list[j].name + "'><span style='font-weight:bold'>" + flavor_list[j].name + "</option>";
			}	
			flavor_orderer_html += "</select>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='bcmanager_num_cpus' id='bcmanager_num_cpus' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='bcmanager_mem_size' id='bcmanager_mem_size' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "<td>";
			flavor_orderer_html += "<input class='form-control' name='bcmanager_disk_size' id='bcmanager_disk_size' type='number'>";
			flavor_orderer_html += "</td>";
			
			flavor_orderer_html += "</tr>";
			flavor_orderer_html += "</thead>";
			flavor_orderer_html += "</table>";
			
			var peer_org_cnt = 0;
			peer_org_cnt = document.regForm.peer_org_cnt.value;
			
			var org_peer_cnt = 0;
			org_peer_cnt = document.regForm.org_peer_cnt.value;
			
			for (i = 0; i < document.regForm.peer_org_cnt.value; i++) {
				for (j = 0; j < document.regForm.org_peer_cnt.value; j++) {
					flavor_peer_html += "<table class='flavor_table'>";
					flavor_peer_html += "<thead>";
					flavor_peer_html += "<tr>";
					flavor_peer_html += "<th width='25%'>Flavor(peer"+((i*org_peer_cnt) + j)+")</th>";
					flavor_peer_html += "<th width='25%'>VCPUs</th>";
					flavor_peer_html += "<th width='25%'>Memory</th>";
					flavor_peer_html += "<th width='25%'>Disk</th>";
					flavor_peer_html += "</tr>";
					flavor_peer_html += "<tr>";
					flavor_peer_html += "<td>";
					flavor_peer_html += "<select class='form-control' name='flavor_select_peer_"+((i*org_peer_cnt) + j)+"' id='flavor_select_peer_"+((i*org_peer_cnt) + j)+"' onchange='changePeerFlavorSelect(this)'>";
					flavor_peer_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
					for (k = 0; k < flavor_list.length; k++) {
						flavor_peer_html += "<option value='" + flavor_list[k].name + "'><span style='font-weight:bold'>" + flavor_list[k].name + "</option>";
					}	
					flavor_peer_html += "</select>";
					flavor_peer_html += "</td>";
					
					flavor_peer_html += "<td>";
					flavor_peer_html += "<input class='form-control' name='peer_num_cpus_"+((i*org_peer_cnt) + j)+"' id='peer_num_cpus_"+((i*org_peer_cnt) + j)+"' type='number'>";
					flavor_peer_html += "</td>";
					
					flavor_peer_html += "<td>";
					flavor_peer_html += "<input class='form-control' name='peer_mem_size_"+((i*org_peer_cnt) + j)+"' id='peer_mem_size_"+((i*org_peer_cnt) + j)+"' type='number'>";
					flavor_peer_html += "</td>";
					
					flavor_peer_html += "<td>";
					flavor_peer_html += "<input class='form-control' name='peer_disk_size_"+((i*org_peer_cnt) + j)+"' id='peer_disk_size_"+((i*org_peer_cnt) + j)+"' type='number'>";
					flavor_peer_html += "</td>";
					
					flavor_peer_html += "</tr>";
					flavor_peer_html += "</thead>";
					flavor_peer_html += "</table>";		
					
					if(j == ((document.regForm.org_peer_cnt.value) - 1)){
						flavor_peer_html += "<table class='flavor_table'>";
						flavor_peer_html += "<thead>";
						flavor_peer_html += "<tr>";
						flavor_peer_html += "<th width='25%'>Flavor(adminorg"+(i)+")</th>";
						flavor_peer_html += "<th width='25%'>VCPUs</th>";
						flavor_peer_html += "<th width='25%'>Memory</th>";
						flavor_peer_html += "<th width='25%'>Disk</th>";
						flavor_peer_html += "</tr>";
						flavor_peer_html += "<tr>";
						flavor_peer_html += "<td>";
						flavor_peer_html += "<select class='form-control' name='flavor_select_adminorg_"+i+"' id='flavor_select_adminorg_"+i+"' onchange='changeAdminOrgFlavorSelect(this)'>";
						flavor_peer_html += "<option value='직접입력'><span style='font-weight:bold'>직접입력</option>";
						for (k = 0; k < flavor_list.length; k++) {
							flavor_peer_html += "<option value='" + flavor_list[k].name + "'><span style='font-weight:bold'>" + flavor_list[k].name + "</option>";
						}	
						flavor_peer_html += "</select>";
						flavor_peer_html += "</td>";
						
						flavor_peer_html += "<td>";
						flavor_peer_html += "<input class='form-control' name='adminorg_num_cpus_"+i+"' id='adminorg_num_cpus_"+i+"' type='number'>";
						flavor_peer_html += "</td>";
						
						flavor_peer_html += "<td>";
						flavor_peer_html += "<input class='form-control' name='adminorg_mem_size_"+i+"' id='adminorg_mem_size_"+i+"' type='number'>";
						flavor_peer_html += "</td>";
						
						flavor_peer_html += "<td>";
						flavor_peer_html += "<input class='form-control' name='adminorg_disk_size_"+i+"' id='adminorg_disk_size_"+i+"' type='number'>";
						flavor_peer_html += "</td>";
						
						flavor_peer_html += "</tr>";
						flavor_peer_html += "</thead>";
						flavor_peer_html += "</table>";	
					}
				}
			}
			
			$("#flavor_node_div").html(flavor_orderer_html);
			$("#flavor_client_div").html(flavor_peer_html);
		}
		
		if(currentTab == 3){
			if(document.regForm.network_select.selectedIndex == 0){
				alert("네트워크를 선택하세요.");
				currentTab = currentTab - n;
				x[currentTab].style.display = "block";		
				return false;
			}
		}
		
		if(currentTab == 4){ //zone 선택창
			
			var peer_org_cnt = 0;
			peer_org_cnt = document.regForm.peer_org_cnt.value;
			
			var org_peer_cnt = 0;
			org_peer_cnt = document.regForm.org_peer_cnt.value;
			
			var zone_html = "";
			
			zone_html += "<p><label>Availability Zone(adminordererorg0)</label></p>";
			zone_html += "<p><select class='form-control' name='zone_select_adminordererorg0' id='zone_select_adminordererorg0'>";
			for (j = 0; j < zone_list.length; j++) {
				zone_html += "<option value='" + zone_list[j].zone + "'><span style='font-weight:bold'>" + zone_list[j].zone + "</option>";
			}	
			zone_html += "</select></p>";
			
			for (i = 0; i < document.regForm.orderer_cnt.value; i++) {
				zone_html += "<p><label>Availability Zone(orderer"+i+")</label></p>";
				zone_html += "<p><select class='form-control' name='zone_select_orderer_"+i+"' id='zone_select_node_"+i+"'>";
				for (j = 0; j < zone_list.length; j++) {
					zone_html += "<option value='" + zone_list[j].zone + "'><span style='font-weight:bold'>" + zone_list[j].zone + "</option>";
				}	
				zone_html += "</select></p>";
			}
			
			zone_html += "<p><label>Availability Zone(kafka-zookeeper)</label></p>";
			zone_html += "<p><select class='form-control' name='zone_select_kafka' id='zone_select_kafka'>";
			for (j = 0; j < zone_list.length; j++) {
				zone_html += "<option value='" + zone_list[j].zone + "'><span style='font-weight:bold'>" + zone_list[j].zone + "</option>";
			}	
			zone_html += "</select></p>";
			
			zone_html += "<p><label>Availability Zone(bcmanager)</label></p>";
			zone_html += "<p><select class='form-control' name='zone_select_bcmanager' id='zone_select_bcmanager'>";
			for (j = 0; j < zone_list.length; j++) {
				zone_html += "<option value='" + zone_list[j].zone + "'><span style='font-weight:bold'>" + zone_list[j].zone + "</option>";
			}	
			zone_html += "</select></p>";
			
			
			
			for (i = 0; i < document.regForm.peer_org_cnt.value; i++) {
				for (j = 0; j < document.regForm.org_peer_cnt.value; j++) {
					zone_html += "<p><label>Availability Zone(peer"+((i*org_peer_cnt) + j)+")</label></p>";
					zone_html += "<p><select class='form-control' name='zone_select_peer_"+((i*org_peer_cnt) + j)+"' id='zone_select_peer_"+((i*org_peer_cnt) + j)+"'>";
					for (k = 0; k < zone_list.length; k++) {
						zone_html += "<option value='" + zone_list[k].zone + "'><span style='font-weight:bold'>" + zone_list[k].zone + "</option>";
					}	
					zone_html += "</select></p>";
					
					if(j == ((document.regForm.org_peer_cnt.value) - 1)){
						zone_html += "<p><label>Availability Zone(adminorg"+(i)+")</label></p>";
						zone_html += "<p><select class='form-control' name='zone_select_adminorg_"+(i)+"' id='zone_select_adminorg_"+(i)+"'>";
						for (k = 0; k < zone_list.length; k++) {
							zone_html += "<option value='" + zone_list[k].zone + "'><span style='font-weight:bold'>" + zone_list[k].zone + "</option>";
						}	
						zone_html += "</select></p>";
					}
				}
			}
			
			$("#hlf_zone_div").html(zone_html);
		}
		
		// if you have reached the end of the form...
		
		if (currentTab >= x.length) {
			// ... the form gets submitted:
			$('.dim-layer').fadeOut();
		
			$('#cover-spin').show(0);
		
			document.getElementById("regForm").submit();
			document.getElementById("bottom_div").style.display = "none";
			document.getElementById("step_div").style.display = "none";
			
			document.regForm.profile_name.value = "";
			document.regForm.description.value = "";
			document.regForm.network_select.selectedIndex = 0;
			
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
		
		if(currentTab == 6){ //zone선택창
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
		var i,x = document.getElementsByClassName("step");
		
		for (i = 0; i < x.length; i++) {
			x[i].className = x[i].className.replace(" active", "");
		}
		//... and adds the "active" class on the current step:
		x[n].className += " active";
	}

	function frmsubmit() {
		$.ajax({
			url : "getHyperledger.do",
			type : "get",
			dataType : "json",
			success : function(data) {
				
				var temp = "";
				if (data.result != "fail") {
					temp += "<table>";
					temp += "<thead>";

					temp += "<tr>";
					temp += "<td class='table_count_td' colspan='14'>";
					temp += "<span class='table_count'>Displaying " + data.data.length + " items</span>";
					temp += "</td>";
					temp += "</tr>";

					temp += "<tr>";
					temp += "<th width='5%'>선택</th>"
					temp += "<th width='5%'>프로파일 명</th>"
					temp += "<th width='15%'>생성시간</th>";
					temp += "<th width='5%'>상태</th>";
					temp += "<th width='5%'>설명</th>";
					temp += "<th width='5%'>Orderer 수</th>";
					temp += "<th width='5%'>Peer Org 수</th>";
					temp += "<th width='5%'>Org별 Peer 수</th>";
					temp += "<th width='5%'>Order Type</th>";
					temp += "<th width='5%'>BatchTimeout</th>";
					temp += "<th width='5%'>MaxMessageCount</th>";
					temp += "<th width='5%'>AbsoluteMaxBytes</th>";
					temp += "<th width='5%'>PreferredMaxBytes</th>";
					temp += "<th width='5%'>Network</th>";
					temp += "</tr>";
					temp += "</thead>";
					for (i = 0; i < data.data.length; i++) {
						temp += "<tr>";
						temp += "<td><input type='radio' name='idx' value='" + data.data[i].idx + "'></td>";
						temp += "<td><a href='/bctt/command/vnfDetail.do?vnf_id=\""+data.data[i].vnf_id+"\"'>"+data.data[i].profile_name+"</a></td>";
						temp += "<td>" + data.data[i].time_stamp + "</td>";
						temp += "<td>" + data.data[i].status + "</td>";
						temp += "<td>" + data.data[i].description + "</td>";
						temp += "<td>" + data.data[i].orderer_cnt + "</td>";
						temp += "<td>" + data.data[i].peer_org_cnt + "</td>";
						temp += "<td>" + data.data[i].org_peer_cnt + "</td>";
						temp += "<td>" + data.data[i].orderer_type + "</td>";
						temp += "<td>" + data.data[i].batch_timeout + "</td>";
						temp += "<td>" + data.data[i].max_message_cnt + "</td>";
						temp += "<td>" + data.data[i].absolute_max_bytes + "</td>";
						temp += "<td>" + data.data[i].preferred_max_bytes + "</td>";
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
					temp += "<th width='5%'>Orderer 수</th>";
					temp += "<th width='5%'>Peer Org 수</th>";
					temp += "<th width='5%'>Org별 Peer 수</th>";
					temp += "<th width='5%'>Order Type</th>";
					temp += "<th width='5%'>BatchTimeout</th>";
					temp += "<th width='5%'>MaxMessageCount</th>";
					temp += "<th width='5%'>AbsoluteMaxBytes</th>";
					temp += "<th width='5%'>PreferredMaxBytes</th>";
					temp += "<th width='5%'>Network</th>";
					temp += "</tr>";
					temp += "</thead>";
					temp += "<tbody>";
					temp += "<tr><td colspan='14'>데이터가 없습니다</td>";
					temp += "</tbody></table>";
				}
				$("#hlf").html(temp);
			},
			beforeSend : function() {
			},
			complete : function() {
			}
		});
	}
	
	function changeAllFlavorSelect(flavor) {
		var flavor_select = document.getElementById("flavor_select_all");
		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;

		document.getElementById("flavor_select_adminordererorg0").value = selectValue;
		
		for (i = 0; i < document.regForm.orderer_cnt.value; i++) {
			document.getElementById("flavor_select_orderer_"+i).value = selectValue;	
		}
		
		document.getElementById("flavor_select_kafka").value = selectValue;
		document.getElementById("flavor_select_bcmanager").value = selectValue;
		
		
		var peer_org_cnt = 0;
		peer_org_cnt = document.regForm.peer_org_cnt.value;
		
		var org_peer_cnt = 0;
		org_peer_cnt = document.regForm.org_peer_cnt.value;
		
		for (i = 0; i < document.regForm.peer_org_cnt.value; i++) {
			for (j = 0; j < document.regForm.org_peer_cnt.value; j++) {
				document.getElementById("flavor_select_peer_"+((i*org_peer_cnt) + j)).value = selectValue;	
				
				if(j == ((document.regForm.org_peer_cnt.value) - 1)){
					document.getElementById("flavor_select_adminorg_"+i).value = selectValue;	
				}
			}
		}
		
		if(selectValue == "직접입력"){
			$("#adminordererorg0_num_cpus").val("");
			$("#adminordererorg0_mem_size").val("");
			$("#adminordererorg0_disk_size").val("");
			
			$("#adminordererorg0_num_cpus").prop("disabled", false);
			$("#adminordererorg0_mem_size").prop("disabled", false);
			$("#adminordererorg0_disk_size").prop("disabled", false);
			
			for (i = 0; i < document.regForm.orderer_cnt.value; i++) {
				$("#orderer_num_cpus_"+i).val("");
				$("#orderer_mem_size_"+i).val("");
				$("#orderer_disk_size_"+i).val("");
				
				$("#orderer_num_cpus_"+i).prop("disabled", false);
				$("#orderer_mem_size_"+i).prop("disabled", false);
				$("#orderer_disk_size_"+i).prop("disabled", false);
			}
			
			$("#kafka_num_cpus").val("");
			$("#kafka_mem_size").val("");
			$("#kafka_disk_size").val("");
			
			$("#kafka_num_cpus").prop("disabled", false);
			$("#kafka_mem_size").prop("disabled", false);
			$("#kafka_disk_size").prop("disabled", false);
			
			$("#bcmanager_num_cpus").val("");
			$("#bcmanager_mem_size").val("");
			$("#bcmanager_disk_size").val("");
			
			$("#bcmanager_num_cpus").prop("disabled", false);
			$("#bcmanager_mem_size").prop("disabled", false);
			$("#bcmanager_disk_size").prop("disabled", false);
			
			
			for (i = 0; i < document.regForm.peer_org_cnt.value; i++) {
				for (j = 0; j < document.regForm.org_peer_cnt.value; j++) {
					
					$("#peer_num_cpus_"+((i*org_peer_cnt) + j)).val("");
					$("#peer_mem_size_"+((i*org_peer_cnt) + j)).val("");
					$("#peer_disk_size_"+((i*org_peer_cnt) + j)).val("");
					
					$("#peer_num_cpus_"+((i*org_peer_cnt) + j)).prop("disabled", false);
					$("#peer_mem_size_"+((i*org_peer_cnt) + j)).prop("disabled", false);
					$("#peer_disk_size_"+((i*org_peer_cnt) + j)).prop("disabled", false);
					
					if(j == ((document.regForm.org_peer_cnt.value) - 1)){
						$("#adminorg_num_cpus_"+i).val("");
						$("#adminorg_mem_size_"+i).val("");
						$("#adminorg_disk_size_"+i).val("");
						
						$("#adminorg_num_cpus_"+i).prop("disabled", false);
						$("#adminorg_mem_size_"+i).prop("disabled", false);
						$("#adminorg_disk_size_"+i).prop("disabled", false);
					}
				}
			}
			
		}else{
			$("#adminordererorg0_num_cpus").val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#adminordererorg0_mem_size").val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#adminordererorg0_disk_size").val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#adminordererorg0_num_cpus").prop("disabled", true);
			$("#adminordererorg0_mem_size").prop("disabled", true);
			$("#adminordererorg0_disk_size").prop("disabled", true);
			
			for (i = 0; i < document.regForm.orderer_cnt.value; i++) {
				$("#orderer_num_cpus_"+i).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
				$("#orderer_mem_size_"+i).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
				$("#orderer_disk_size_"+i).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
				
				$("#orderer_num_cpus_"+i).prop("disabled", true);
				$("#orderer_mem_size_"+i).prop("disabled", true);
				$("#orderer_disk_size_"+i).prop("disabled", true);
			}
			
			$("#kafka_num_cpus").val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#kafka_mem_size").val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#kafka_disk_size").val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#kafka_num_cpus").prop("disabled", true);
			$("#kafka_mem_size").prop("disabled", true);
			$("#kafka_disk_size").prop("disabled", true);
			
			$("#bcmanager_num_cpus").val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#bcmanager_mem_size").val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#bcmanager_disk_size").val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#bcmanager_num_cpus").prop("disabled", true);
			$("#bcmanager_mem_size").prop("disabled", true);
			$("#bcmanager_disk_size").prop("disabled", true);
			
			
			
			for (i = 0; i < document.regForm.peer_org_cnt.value; i++) {
				for (j = 0; j < document.regForm.org_peer_cnt.value; j++) {
					
					$("#peer_num_cpus_"+((i*org_peer_cnt) + j)).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
					$("#peer_mem_size_"+((i*org_peer_cnt) + j)).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
					$("#peer_disk_size_"+((i*org_peer_cnt) + j)).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
					
					$("#peer_num_cpus_"+((i*org_peer_cnt) + j)).prop("disabled", true);
					$("#peer_mem_size_"+((i*org_peer_cnt) + j)).prop("disabled", true);
					$("#peer_disk_size_"+((i*org_peer_cnt) + j)).prop("disabled", true);
					
					if(j == ((document.regForm.org_peer_cnt.value) - 1)){
						$("#adminorg_num_cpus_"+i).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
						$("#adminorg_mem_size_"+i).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
						$("#adminorg_disk_size_"+i).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
						
						$("#adminorg_num_cpus_"+i).prop("disabled", true);
						$("#adminorg_mem_size_"+i).prop("disabled", true);
						$("#adminorg_disk_size_"+i).prop("disabled", true);
					}
				}
			}
			
		}
		
		
		
	}

	function changeAdminOrdererOrgFlavorSelect(flavor) {
		var flavor_select = document.getElementById("flavor_select_adminordererorg0");
		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;

		if (selectValue == "직접입력") {
			
			$("#adminordererorg0_num_cpus").val("");
			$("#adminordererorg0_mem_size").val("");
			$("#adminordererorg0_disk_size").val("");
			
			$("#adminordererorg0_num_cpus").prop("disabled", false);
			$("#adminordererorg0_mem_size").prop("disabled", false);
			$("#adminordererorg0_disk_size").prop("disabled", false);
			
		} else {
			$("#adminordererorg0_num_cpus").val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#adminordererorg0_mem_size").val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#adminordererorg0_disk_size").val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#adminordererorg0_num_cpus").prop("disabled", true);
			$("#adminordererorg0_mem_size").prop("disabled", true);
			$("#adminordererorg0_disk_size").prop("disabled", true);
		}
	}
	
	function changeOrdererFlavorSelect(flavor) {
		var flavor_select = document.getElementById(flavor.id);
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;
		var idx = flavor.id.substring(22, 23);

		if (selectValue == "직접입력") {
			$("#orderer_num_cpus_"+idx).val("");
			$("#orderer_mem_size_"+idx).val("");
			$("#orderer_disk_size_"+idx).val("");
			
			$("#orderer_num_cpus_"+idx).prop("disabled", false);
			$("#orderer_mem_size_"+idx).prop("disabled", false);
			$("#orderer_disk_size_"+idx).prop("disabled", false);
		} else {
			$("#orderer_num_cpus_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#orderer_mem_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#orderer_disk_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#orderer_num_cpus_"+idx).prop("disabled", true);
			$("#orderer_mem_size_"+idx).prop("disabled", true);
			$("#orderer_disk_size_"+idx).prop("disabled", true);
		}
	}
	
	function changeKafkaFlavorSelect(flavor) {
		var flavor_select = document.getElementById("flavor_select_kafka");
		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;

		if (selectValue == "직접입력") {
			
			$("#kafka_num_cpus").val("");
			$("#kafka_mem_size").val("");
			$("#kafka_disk_size").val("");
			
			$("#kafka_num_cpus").prop("disabled", false);
			$("#kafka_mem_size").prop("disabled", false);
			$("#kafka_disk_size").prop("disabled", false);
			
		} else {
			$("#kafka_num_cpus").val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#kafka_mem_size").val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#kafka_disk_size").val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#kafka_num_cpus").prop("disabled", true);
			$("#kafka_mem_size").prop("disabled", true);
			$("#kafka_disk_size").prop("disabled", true);
		}
	}
	
	function changeBcmanagerFlavorSelect(flavor) {
		var flavor_select = document.getElementById("flavor_select_bcmanager");
		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;

		if (selectValue == "직접입력") {
			
			$("#bcmanager_num_cpus").val("");
			$("#bcmanager_mem_size").val("");
			$("#bcmanager_disk_size").val("");
			
			$("#bcmanager_num_cpus").prop("disabled", false);
			$("#bcmanager_mem_size").prop("disabled", false);
			$("#bcmanager_disk_size").prop("disabled", false);
			
		} else {
			$("#bcmanager_num_cpus").val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#bcmanager_mem_size").val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#bcmanager_disk_size").val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#bcmanager_num_cpus").prop("disabled", true);
			$("#bcmanager_mem_size").prop("disabled", true);
			$("#bcmanager_disk_size").prop("disabled", true);
		}
	}
	
	function changePeerFlavorSelect(flavor) {
		var flavor_select = document.getElementById(flavor.id);
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;
		var idx = flavor.id.substring(19, flavor.id.length);
		
		if (selectValue == "직접입력") {
			$("#peer_num_cpus_"+idx).val("");
			$("#peer_mem_size_"+idx).val("");
			$("#peer_disk_size_"+idx).val("");
			
			$("#peer_num_cpus_"+idx).prop("disabled", false);
			$("#peer_mem_size_"+idx).prop("disabled", false);
			$("#peer_disk_size_"+idx).prop("disabled", false);
		} else {
			$("#peer_num_cpus_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#peer_mem_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#peer_disk_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#peer_num_cpus_"+idx).prop("disabled", true);
			$("#peer_mem_size_"+idx).prop("disabled", true);
			$("#peer_disk_size_"+idx).prop("disabled", true);
		}
	}
	
	function changeAdminOrgFlavorSelect(flavor) {
		var flavor_select = document.getElementById(flavor.id);
		var selectValue = flavor_select.options[flavor_select.selectedIndex].value;
		var idx = flavor.id.substring(23, 24);
		
		if (selectValue == "직접입력") {
			$("#adminorg_num_cpus_"+idx).val("");
			$("#adminorg_mem_size_"+idx).val("");
			$("#adminorg_disk_size_"+idx).val("");
			
			$("#adminorg_num_cpus_"+idx).prop("disabled", false);
			$("#adminorg_mem_size_"+idx).prop("disabled", false);
			$("#adminorg_disk_size_"+idx).prop("disabled", false);
		} else {
			$("#adminorg_num_cpus_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].vcpus);
			$("#adminorg_mem_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].ram);
			$("#adminorg_disk_size_"+idx).val(flavor_list[(flavor_select.selectedIndex - 1)].root_disk);
			
			$("#adminorg_num_cpus_"+idx).prop("disabled", true);
			$("#adminorg_mem_size_"+idx).prop("disabled", true);
			$("#adminorg_disk_size_"+idx).prop("disabled", true);
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
		<div id="header">
			<div class="container">
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
			<nav id="nav" class="vertical">
				<ul>
					<li><label for="home">프로젝트</label> <input type="radio"
						name="verticalMenu" id="home"  checked="checked"/>
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
								<li>Hyperledger</li>
							</ol>
						</div>

						<div class="x_title">
							<h2>Hyperledger</h2>
						</div>

						<form name="rvForm">
							<div id="location_div">
								<p style="float: right;">
									<a href="#layer2" class="button-default button btn-example">추가</a>
									<a class="button-default button btn-delete">삭제</a>
								</p>
							</div>
							<div id="hlf"></div>
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
				<h1>Hyperledger 테스트</h1>
			</div>

			<div class="modal-body">
				<form id="regForm" action="createHyperledger.do" method="get"
					enctype="multipart/form-data" name="regForm"
					onsubmit="return frmsubmit();">
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
							<label>Orderer 수</label>
						</p>
						<p>
							<input class="form-control" id="orderer_cnt" name="orderer_cnt" placeholder="1 ~ 2" type="number" min="1" maxlength="1" max="2">
						</p>

						<p>
							<label>Org 수</label>
						</p>
						<p id="peer_org_cnt">
							<input class="form-control" id="peer_org_cnt" name="peer_org_cnt" placeholder="1 ~ 2" type="number" min="1" maxlength="1" max="2">
						</p>

						<p>
							<label>Org별 Peer 수</label>
						</p>
						<p id="org_peer_cnt">
							<input class="form-control" id="org_peer_cnt" name="org_peer_cnt" placeholder="1 ~ 2" type="number" min="1" maxlength="1" max="2">
						</p>
					</div>
					
					<div class="tab">
						<p><label>Orderer Type</label></p>
						<p>
							<select class="form-control" id="orderer_type" name="orderer_type">
								<option label="kafka" value="kafka" selected="selected">kafka</option>
								<option label="solo" value="solo">solo</option>
						</p>
						</select>
						</p>

						<p>
							<label>BatchTimeout(초)</label>
						</p>
						<p id="batch_timeout">
							<input class="form-control" id="batch_timeout" name="batch_timeout" type="number" value="1">
						</p>

						<p>
							<label>MaxMessageCount</label>
						</p>
						<p id="max_message_cnt">
							<input class="form-control" name="max_message_cnt" type="number" value="30">
						</p>
						
						<p>
							<label>AbsoluteMaxBytes(MB)</label>
						</p>
						<p id="absolute_max_bytes">
							<input class="form-control" name="absolute_max_bytes" type="number" value="99">
						</p>
						
						<p>
							<label>PreferredMaxBytes(KB)</label>
						</p>
						<p id="preferred_max_bytes">
							<input class="form-control" name="preferred_max_bytes" type="number" value="512">
						</p>
						
						<p>
							<label>Network</label>
						</p>
						<p>
							<select class="form-control" name="network_select" id="network_select">
							</select>
						</p>
					</div>
					
					<div class="tab" id="flavor_node_div">
					</div>
					
					<div class="tab" id="flavor_client_div">
					</div>
					 
					<div class="tab" id="hlf_zone_div">
					</div>

					<div id="step_div" style="text-align: center; position: absolute; bottom: 10; margin: 0 auto; width: 90%">
						<div style="width: 100%; margin: 0 auto">
							<span class="step"> </span> 
							<span class="step"> </span> 
							<span class="step"> </span>
							<span class="step"> </span>
							<span class="step"> </span>
							<span class="step"> </span>
						</div>
					</div>
				</form>
			</div>

			<div class="modal-footer">
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
				x[3].style.display = "none";
				x[4].style.display = "none";
				x[5].style.display = "none";
		
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
		
			function vnfdDelete(form) {
				var mylen = form.idx.length;
				var gourl;
		
				if (mylen == "undefined") {
					mylen = 1;
				}
				for (var i = 0; i < mylen; i++) {
					if (form.idx[i].checked == true) {
						gourl = form.idx[i].value;
						break;
					}
				}
				if (i == mylen) {
					alert("선택하세요!");
					return;
				}
				document.rvForm.action = "deleteHyperledger.do?id=" + gourl;
				document.rvForm.submit();
		
				$('#cover-spin').show(1);
			}
		</script>
</body>
</html>