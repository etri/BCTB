$(document).ready(function(){
	$.ajax({
		url : "projectList.do",
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
				temp += "<th width='20%'>이름</th>";
				temp += "<th width='25%'>설명</th>";
				temp += "<th width='25%'>프로젝트 ID</th>";
				temp += "<th width='15%'>도메인 ID</th>";
				temp += "<th width='15%'>활성화됨</th>";
				temp += "</tr>";
				temp += "</thead>";

				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td>"+data.data[i].project_name+"</a></td>";
					temp += "<td>"+data.data[i].description+"</td>";
					temp += "<td>"+data.data[i].project_id+"</td>";
					temp += "<td>"+data.data[i].domain_id+"</td>";
					temp += "<td>"+data.data[i].enabled+"</td>";
					temp += "</tr>";
				}
				
				temp +="</table>";
				
			}else{
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='20%'>이름</th>";
				temp += "<th width='25%'>설명</th>";
				temp += "<th width='25%'>프로젝트 ID</th>";
				temp += "<th width='15%'>도메인 ID</th>";
				temp += "<th width='15%'>활성화됨</th>";
				temp += "</tr>";
				temp += "</thead>";
				temp += "<tbody>";
				temp += "<tr><td colspan='5'>데이터가 없습니다</td>";
				temp += "</tbody></table>";
			}
			$("#project").html(temp);
			
		},
		beforeSend:function(){
			$('.wrap-loading').removeClass('display-none');
		},
		complete:function(){
			$('.wrap-loading').addClass('display-none');
		}
	});
});