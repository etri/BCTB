$(document).ready(function(){
	
	$.ajax({
		url : "getHyperledger.do",
		type:"get",
		dataType:"json",
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
					temp += "<td><a href='/bctt/command/vnfDetail.do?vnf_id=%22"+data.data[i].vnf_id+"%22'>"+data.data[i].profile_name+"</a></td>";
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
			beforeSend:function(){
				$('.wrap-loading').removeClass('display-none');
			},
			complete:function(){
				$('.wrap-loading').addClass('display-none');
			}
		});
	
//	setInterval(function() { 
//	$.ajax({
//		url : "getHyperledger.do",
//		type:"get",
//		dataType:"json",
//		success : function(data) {
//			
//			var temp = "";
//			if (data.result != "fail") {
//				temp += "<table>";
//				temp += "<thead>";
//
//				temp += "<tr>";
//				temp += "<td class='table_count_td' colspan='14'>";
//				temp += "<span class='table_count'>Displaying " + data.data.length + " items</span>";
//				temp += "</td>";
//				temp += "</tr>";
//
//				temp += "<tr>";
//				temp += "<th width='5%'>선택</th>"
//				temp += "<th width='5%'>프로파일 명</th>"
//				temp += "<th width='15%'>생성시간</th>";
//				temp += "<th width='5%'>상태</th>";
//				temp += "<th width='5%'>설명</th>";
//				temp += "<th width='5%'>Orderer 수</th>";
//				temp += "<th width='5%'>Peer Org 수</th>";
//				temp += "<th width='5%'>Org별 Peer 수</th>";
//				temp += "<th width='5%'>Order Type</th>";
//				temp += "<th width='5%'>BatchTimeout</th>";
//				temp += "<th width='5%'>MaxMessageCount</th>";
//				temp += "<th width='5%'>AbsoluteMaxBytes</th>";
//				temp += "<th width='5%'>PreferredMaxBytes</th>";
//				temp += "<th width='5%'>Network</th>";
//				temp += "</tr>";
//				temp += "</thead>";
//				for (i = 0; i < data.data.length; i++) {
//					temp += "<tr>";
//					temp += "<td><input type='radio' name='idx' value='" + data.data[i].idx + "'></td>";
//					temp += "<td><a href='/bctt/command/vnfDetail.do?vnf_id=%22"+data.data[i].vnf_id+"%22'>"+data.data[i].profile_name+"</a></td>";
//					temp += "<td>" + data.data[i].time_stamp + "</td>";
//					temp += "<td>" + data.data[i].status + "</td>";
//					temp += "<td>" + data.data[i].description + "</td>";
//					temp += "<td>" + data.data[i].orderer_cnt + "</td>";
//					temp += "<td>" + data.data[i].peer_org_cnt + "</td>";
//					temp += "<td>" + data.data[i].org_peer_cnt + "</td>";
//					temp += "<td>" + data.data[i].orderer_type + "</td>";
//					temp += "<td>" + data.data[i].batch_timeout + "</td>";
//					temp += "<td>" + data.data[i].max_message_cnt + "</td>";
//					temp += "<td>" + data.data[i].absolute_max_bytes + "</td>";
//					temp += "<td>" + data.data[i].preferred_max_bytes + "</td>";
//					temp += "<td>" + data.data[i].network_name + "</td>";
//					temp += "</tr>";
//				}
//				temp += "</table>";
//			} else {
//				temp += "<table>";
//				temp += "<thead>";
//				temp += "<tr>";
//				temp += "<th width='5%'>선택</th>"
//				temp += "<th width='5%'>프로파일 명</th>"
//				temp += "<th width='15%'>생성시간</th>";
//				temp += "<th width='5%'>상태</th>";
//				temp += "<th width='5%'>설명</th>";
//				temp += "<th width='5%'>Orderer 수</th>";
//				temp += "<th width='5%'>Peer Org 수</th>";
//				temp += "<th width='5%'>Org별 Peer 수</th>";
//				temp += "<th width='5%'>Order Type</th>";
//				temp += "<th width='5%'>BatchTimeout</th>";
//				temp += "<th width='5%'>MaxMessageCount</th>";
//				temp += "<th width='5%'>AbsoluteMaxBytes</th>";
//				temp += "<th width='5%'>PreferredMaxBytes</th>";
//				temp += "<th width='5%'>Network</th>";
//				temp += "</tr>";
//				temp += "</thead>";
//				temp += "<tbody>";
//				temp += "<tr><td colspan='14'>데이터가 없습니다</td>";
//				temp += "</tbody></table>";
//			}
//				$("#hlf").html(temp);
//			},
//			beforeSend:function(){
//				$('.wrap-loading').removeClass('display-none');
//			},
//			complete:function(){
//				$('.wrap-loading').addClass('display-none');
//			}
//		});
//	}, 5000);
});
