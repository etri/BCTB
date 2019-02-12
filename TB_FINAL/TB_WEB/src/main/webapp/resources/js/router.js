$(document).ready(function(){
	$.ajax({
		url : "getRouter.do",
		type:"get",
		dataType:"json",
		success : function(data) {
			var temp="";
			
			if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				
				temp += "<tr>";
				temp += "<td class='table_count_td' colspan='5'>";
				temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
				temp += "</td>";
				temp += "</tr>";
				
				temp += "<tr>";
				temp += "<th width='20%'>프로젝트</th>";
				temp += "<th width='20%'>이름</th>";
				temp += "<th width='20%'>Status</th>";
				temp += "<th width='20%'>가용성 존</th>";
				temp += "<th width='20%'>관리 상태</th>";
				temp += "</tr>";
				temp += "</thead>";

				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td>"+data.data[i].project_name+"</td>";
					temp += "<td><a href='/bctt/command/routerDetail.do?router_id=%22"+data.data[i].id+"%22&router_name=%22"+data.data[i].name+"%22'>"+data.data[i].name+"</a></td>";
					temp += "<td>"+data.data[i].status+"</td>";
					temp += "<td>"+data.data[i].availability_zones+"</td>";
					temp += "<td>"+data.data[i].admin_state_up+"</td>";
					temp += "</tr>";
				}

				temp +="</table>";

			}else{
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='20%'>프로젝트</th>";
				temp += "<th width='20%'>이름</th>";
				temp += "<th width='20%'>Status</th>";
				temp += "<th width='20%'>가용성 존</th>";
				temp += "<th width='20%'>관리 상태</th>";
				temp += "</tr>";
				temp += "</thead>";
				temp += "<tbody>";
				temp += "<tr><td colspan='4'>데이터가 없습니다</td>";
				temp += "</tbody></table>";
			}
			$("#route").html(temp);
		},
		beforeSend:function(){
			$('.wrap-loading').removeClass('display-none');
			$('#route').addClass('display-none');
		},
		complete:function(){
			$('.wrap-loading').addClass('display-none');
			$('#route').removeClass('display-none');
		}
	});
});
