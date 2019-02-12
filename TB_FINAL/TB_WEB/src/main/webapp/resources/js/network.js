$(document).ready(function(){
	$.ajax({
		url : "getNetwork.do",
		type:"get",
		dataType:"json",
		success : function(data) {
			var temp="";
			
			if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				
				temp += "<tr>";
				temp += "<td class='table_count_td' colspan='8'>";
				temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
				temp += "</td>";
				temp += "</tr>";
				
				temp += "<tr>";
				temp += "<th width='10%'>프로젝트</th>";
				temp += "<th width='10%'>네트워크 이름</th>";
				temp += "<th width='20%'>관련 서브넷</th>";
				temp += "<th width='10%'>게이트웨이 IP</th>";
				temp += "<th width='20%'>IP할당 Pool</th>";
				temp += "<th width='10%'>DNS 네임 서버</th>";
				temp += "<th width='10%'>DNS 활성화</th>";
				temp += "<th width='10%'>IP버전</th>";
				temp += "</tr>";
				temp += "</thead>";

				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td>"+data.data[i].project_name+"</td>";
					temp += "<td>"+data.data[i].network_name+"</td>";
					temp += "<td><a href='/bctt/command/subnet.do?subnet_id=%22"+data.data[i].subnet_id+"%22&subnet_name=%22"+data.data[i].subnet_name+"%22'>"+data.data[i].subnet_name+"</a> "+data.data[i].cidr+"</td>";
					temp += "<td>"+data.data[i].gateway_ip+"</td>";
					temp += "<td>"+data.data[i].allocation_pools+"</td>";
					temp += "<td>"+data.data[i].dns_nameservers+"</td>";
					temp += "<td>"+data.data[i].enable_dhcp+"</td>";
					temp += "<td>"+data.data[i].ip_version+"</td>";
					temp += "</tr>";
				}

				temp +="</table>";

			}else{
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='10%'>프로젝트</th>";
				temp += "<th width='10%'>네트워크 이름</th>";
				temp += "<th width='20%'>관련 서브넷</th>";
				temp += "<th width='10%'>게이트웨이 IP</th>";
				temp += "<th width='20%'>IP할당 Pool</th>";
				temp += "<th width='10%'>DNS 네임 서버</th>";
				temp += "<th width='10%'>DNS 활성화</th>";
				temp += "<th width='10%'>IP버전</th>";
				temp += "</tr>";
				temp += "</thead>";
				temp += "<tbody>";
				temp += "<tr><td colspan='8'>데이터가 없습니다</td>";
				temp += "</tbody></table>";
			}
			$("#public").html(temp);
		}
	});
});