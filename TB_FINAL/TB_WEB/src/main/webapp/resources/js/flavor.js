$(document).ready(function(){
		$.ajax({
			url : "getFlavorList.do",
			type:"get",
			dataType:"json",
			beforeSend:function(){
				$('.wrap-loading').removeClass('display-none');
			},
			success : function(data) {
				var temp="";
				if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				
				temp += "<tr>";
				temp += "<td class='table_count_td' colspan='10'>";
				temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
				temp += "</td>";
				temp += "</tr>";
				
				temp += "<tr>";
				temp += "<th width='10%'>ID</th>";
				temp += "<th width='10%'>Flavor 이름</th>";
				temp += "<th width='10%'>VCPUs</th>";
				temp += "<th width='10%'>RAM</th>";
				temp += "<th width='10%'>Root Disk</th>";
				temp += "<th width='10%'>Ephemeral Disk</th>";
				temp += "<th width='10%'>SWAP Disk</th>";
				temp += "<th width='10%'>RX/TX 요인</th>";
				temp += "<th width='10%'>공용</th>";
				temp += "<th width='10%'>메타데이터</th>";
				
				temp += "</tr>";
				temp += "</thead>";
				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td>"+data.data[i].id+"</td>";
					temp += "<td>"+data.data[i].name+"</td>";
					temp += "<td>"+data.data[i].vcpus+"</td>";
					if(data.data[i].ram > 1024){
						temp += "<td>"+(data.data[i].ram / 1024)+"GB</td>";	
					}else{
						temp += "<td>"+data.data[i].ram+"MB</td>";
					}
					
					temp += "<td>"+data.data[i].root_disk+"GB</td>";
					temp += "<td>"+data.data[i].ephemeral_disk+"GB</td>";
					temp += "<td>"+data.data[i].swap_disk+"MB</td>";
					temp += "<td>"+data.data[i].rxtx_factor+"</td>";
					temp += "<td>"+data.data[i].is_public+"</td>";
					temp += "<td>"+data.data[i].disabled+"</td>";
					temp += "</tr>";
					}
				temp +="</table>";
				}else{
					temp += "<table>";
					temp += "<thead>";
					temp += "<tr>";
					temp += "<th width='10%'>Flavor 이름</th>";
					temp += "<th width='10%'>VCPUs</th>";
					temp += "<th width='10%'>RAM</th>";
					temp += "<th width='10%'>Root Disk</th>";
					temp += "<th width='10%'>Ephemeral Disk</th>";
					temp += "<th width='10%'>SWAP Disk</th>";
					temp += "<th width='10%'>RX/TX 요인</th>";
					temp += "<th width='10%'>ID</th>";
					temp += "<th width='10%'>공용</th>";
					temp += "<th width='10%'메타데이터</th>";
					temp += "</tr>";
					temp += "</thead>";
					temp += "<tbody>";
					temp += "<tr><td colspan='10'>데이터가 없습니다</td></tr>";
					temp += "</tbody></table>";
				}
					$("#flavor").html(temp);
				},
				complete:function(){
					$('.wrap-loading').addClass('display-none');
				}
			});
		});