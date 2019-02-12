$(document).ready(function(){
		$.ajax({
			url : "getVnfdList.do",
			type:"get",
			dataType:"json",
			success : function(data) {
				var temp="";
				if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='10%'>선택</th>"
				temp += "<th width='30%'>Name</th>";
				temp += "<th width='20%'>Description</th>";
				temp += "</tr>";
				temp += "</thead>";
				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td><input type='radio' name='vnfd_id' value='"+data.data[i].vnfd_id+"'></td>";
					temp += "<td id='view' onclick='view(\""+data.data[i].vnfd_id+"\");layer_open(&apos;layer1&apos;);'>"+data.data[i].vnfd_name+"</td>";
					temp += "<td>"+data.data[i].description+"</td>";
					temp += "</tr>";
					}
					temp +="</table>";
				}
				else{
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='10%'>선택</th>"
				temp += "<th width='30%'>Name</th>";
				temp += "<th width='20%'>Description</th>";
				temp += "</tr>";
				temp += "</thead>";
				temp += "<tbody>";
				temp += "<tr><td colspan='3'>데이터가 없습니다</td>";
				temp += "</tbody></table>";
				}
					$("#vnfd").html(temp);
				},
				beforeSend:function(){
					$('.wrap-loading').removeClass('display-none');
				},
				complete:function(){
					$('.wrap-loading').addClass('display-none');
				}
			});
		});