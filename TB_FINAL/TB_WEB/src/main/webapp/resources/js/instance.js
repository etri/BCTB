$(document).ready(function(){
		$.ajax({
			url : "getInstance.do",
			type:"get",
			dataType:"json",
			success : function(data) {
				var temp="";
				if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				
				temp += "<tr>";
				temp += "<td class='table_count_td' colspan='11'>";
				temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
				temp += "</td>";
				temp += "</tr>";
				
				temp += "<tr>";
				temp += "<th width='5%'>선택</th>"
				temp += "<th width='5%'>프로젝트</th>"
				temp += "<th width='10%'>호스트</th>";
				temp += "<th width='5%'>인스턴스 이름</th>";
				temp += "<th width='25%'>이미지 이름</th>";
				temp += "<th width='15%'>IP 주소</th>";
				temp += "<th width='5%'>Flavor</th>";
				temp += "<th width='5%'>Status</th>";
				temp += "<th width='5%'>작업</th>";
				temp += "<th width='5%'>전원 상태</th>";
				temp += "<th width='15%'>생성된 시간</th>";
				temp += "</tr>";
				temp += "</thead>";
				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td><input type='radio' name='id' value='" + data.data[i].id + "'></td>";
					temp += "<td>"+data.data[i].project_name+"</td>";
					temp += "<td>"+data.data[i].host+"</td>";
					temp += "<td><a href='/bctt/command/instanceDetail.do?instance_id=%22"+data.data[i].id+"%22&instance_name=%22"+data.data[i].name+"%22'>"+data.data[i].name+"</a></td>";
					temp += "<td>"+data.data[i].image_name+"</td>";
					temp += "<td>"+data.data[i].addresses+"</td>";
					temp += "<td>"+data.data[i].flavor_name+"</td>";
					temp += "<td>"+data.data[i].status+"</td>";
					temp += "<td>"+data.data[i].task_state+"</td>";
					temp += "<td>"+data.data[i].power_state+"</td>";
					temp += "<td>"+data.data[i].created+"</td>";
					
					temp += "</tr>";
					}
					temp +="</table>";
				}
				else{
					temp += "<table>";
					temp += "<thead>";
					temp += "<tr>";
					temp += "<th width='5%'>선택</th>"
					temp += "<th width='5%'>프로젝트</th>"
					temp += "<th width='10%'>호스트</th>";
					temp += "<th width='5%'>이름</th>";
					temp += "<th width='25%'>이미지 이름</th>";
					temp += "<th width='15%'>IP 주소</th>";
					temp += "<th width='5%'>Flavor</th>";
					temp += "<th width='5%'>Status</th>";
					temp += "<th width='5%'>작업</th>";
					temp += "<th width='5%'>전원 상태</th>";
					temp += "<th width='15%'>생성된 시간</th>";
					temp += "</tr>";
					temp += "</thead>";
					temp += "<tbody>";
					temp += "<tr><td colspan='11'>데이터가 없습니다</td>";
					temp += "</tbody></table>";
				}
					$("#instance").html(temp);
				},
				beforeSend:function(){
					$('.wrap-loading').removeClass('display-none');
				},
				complete:function(){
					$('.wrap-loading').addClass('display-none');
				}
			});
		});