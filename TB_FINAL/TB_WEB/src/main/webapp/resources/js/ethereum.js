$(document).ready(function(){
	$.ajax({
		url : "getEthereum.do",
		type:"get",
		dataType:"json",
		success : function(data) {
			var temp="";
			if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				
				temp += "<tr>";
				temp += "<td class='table_count_td' colspan='12'>";
				temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
				temp += "</td>";
				temp += "</tr>";
				
				temp += "<tr>";
				temp += "<th width='5%'>선택</th>"
				temp += "<th width='6%'>프로파일 명</th>"
				temp += "<th width='12%'>생성시간</th>";
				temp += "<th width='5%'>상태</th>";
				temp += "<th width='5%'>설명</th>";
				temp += "<th width='10%'>네트워크 타입</th>";
				temp += "<th width='10%'>부트스트랩 노드수</th>";
				temp += "<th width='5%'>클라이언트 수</th>";
				temp += "<th width='5%'>ChainID</th>";
				temp += "<th width='5%'>Difficulty</th>";
				temp += "<th width='5%'>GasLimit</th>";
				temp += "<th width='5%'>Network</th>";
				temp += "</tr>";
				temp += "</thead>";
				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td><input type='radio' name='idx' value='"+data.data[i].idx+"'></td>";
					temp += "<td><a href='/bctt/command/vnfDetail.do?vnf_id=%22"+data.data[i].vnf_id+"%22'>"+data.data[i].profile_name+"</a></td>";
					temp += "<td>" + data.data[i].time_stamp + "</td>";
					temp += "<td>" + data.data[i].status + "</td>";
					temp += "<td>" + data.data[i].description + "</td>";
					temp += "<td>" + data.data[i].network_type + "</td>";
					temp += "<td>" + data.data[i].node_cnt + "</td>";
					temp += "<td>" + data.data[i].client_cnt + "</td>";
					temp += "<td>" + data.data[i].chainid + "</td>";
					temp += "<td>" + data.data[i].difficulty + "</td>";
					temp += "<td>" + data.data[i].gaslimit + "</td>";
					temp += "<td>" + data.data[i].network_name + "</td>";
					temp += "</tr>";
				}
				temp +="</table>";
			}
			else{
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='5%'>선택</th>"
				temp += "<th width='6%'>프로파일 명</th>"
				temp += "<th width='12%'>생성시간</th>";
				temp += "<th width='5%'>상태</th>";
				temp += "<th width='5%'>설명</th>";
				temp += "<th width='10%'>네트워크 타입</th>";
				temp += "<th width='10%'>부트스트랩 노드수</th>";
				temp += "<th width='5%'>클라이언트 수</th>";
				temp += "<th width='5%'>ChainID</th>";
				temp += "<th width='5%'>Difficulty</th>";
				temp += "<th width='5%'>GasLimit</th>";
				temp += "<th width='5%'>Network</th>";
				temp += "</tr>";
				temp += "</thead>";
				temp += "<tbody>";
				temp += "<tr><td colspan='12'>데이터가 없습니다</td>";
				temp += "</tbody></table>";
			}
				$("#ethereum").html(temp);
			},
			beforeSend:function(){
				$('.wrap-loading').removeClass('display-none');
			},
			complete:function(){
				$('.wrap-loading').addClass('display-none');
			}
		});
	
//	setInterval(function() { 
//		$.ajax({
//			url : "getEthereum.do",
//			type:"get",
//			dataType:"json",
//			success : function(data) {
//				var temp="";
//				if(data.result!="fail"){
//					temp += "<table>";
//					temp += "<thead>";
//					
//					temp += "<tr>";
//					temp += "<td class='table_count_td' colspan='12'>";
//					temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
//					temp += "</td>";
//					temp += "</tr>";
//					
//					temp += "<tr>";
//					temp += "<th width='5%'>선택</th>"
//					temp += "<th width='6%'>프로파일 명</th>"
//					temp += "<th width='12%'>생성시간</th>";
//					temp += "<th width='5%'>상태</th>";
//					temp += "<th width='5%'>설명</th>";
//					temp += "<th width='10%'>네트워크 타입</th>";
//					temp += "<th width='10%'>부트스트랩 노드수</th>";
//					temp += "<th width='5%'>클라이언트 수</th>";
//					temp += "<th width='5%'>ChainID</th>";
//					temp += "<th width='5%'>Difficulty</th>";
//					temp += "<th width='5%'>GasLimit</th>";
//					temp += "<th width='5%'>Network</th>";
//					temp += "</tr>";
//					temp += "</thead>";
//					for(i=0; i<data.data.length; i++){
//						temp += "<tr>";
//						temp += "<td><input type='radio' name='idx' value='"+data.data[i].idx+"'></td>";
//						temp += "<td><a href='/bctt/command/vnfDetail.do?vnf_id=%22"+data.data[i].vnf_id+"%22'>"+data.data[i].profile_name+"</a></td>";
//						temp += "<td>" + data.data[i].time_stamp + "</td>";
//						temp += "<td>" + data.data[i].status + "</td>";
//						temp += "<td>" + data.data[i].description + "</td>";
//						temp += "<td>" + data.data[i].network_type + "</td>";
//						temp += "<td>" + data.data[i].node_cnt + "</td>";
//						temp += "<td>" + data.data[i].client_cnt + "</td>";
//						temp += "<td>" + data.data[i].chainid + "</td>";
//						temp += "<td>" + data.data[i].difficulty + "</td>";
//						temp += "<td>" + data.data[i].gaslimit + "</td>";
//						temp += "<td>" + data.data[i].network_name + "</td>";
//						temp += "</tr>";
//					}
//					temp +="</table>";
//				}
//				else{
//					temp += "<table>";
//					temp += "<thead>";
//					temp += "<tr>";
//					temp += "<th width='5%'>선택</th>"
//					temp += "<th width='6%'>프로파일 명</th>"
//					temp += "<th width='12%'>생성시간</th>";
//					temp += "<th width='5%'>상태</th>";
//					temp += "<th width='5%'>설명</th>";
//					temp += "<th width='10%'>네트워크 타입</th>";
//					temp += "<th width='10%'>부트스트랩 노드수</th>";
//					temp += "<th width='5%'>클라이언트 수</th>";
//					temp += "<th width='5%'>ChainID</th>";
//					temp += "<th width='5%'>Difficulty</th>";
//					temp += "<th width='5%'>GasLimit</th>";
//					temp += "<th width='5%'>Network</th>";
//					temp += "</tr>";
//					temp += "</thead>";
//					temp += "<tbody>";
//					temp += "<tr><td colspan='12'>데이터가 없습니다</td>";
//					temp += "</tbody></table>";
//				}
//					$("#ethereum").html(temp);
//				},
//				beforeSend:function(){
//					$('.wrap-loading').removeClass('display-none');
//				},
//				complete:function(){
//					$('.wrap-loading').addClass('display-none');
//				}
//			});
//	}, 5000);
});