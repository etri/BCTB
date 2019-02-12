$(document).ready(function(){
	$.ajax({
		url : "getVnfList.do",
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
				temp += "<th width='30%'>VNFD</th>";
				temp += "<th width='15%'>Status</th>";
				temp += "</tr>";
				temp += "</thead>";
				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td><input type='radio' name='vnf_id' value='"+data.data[i].vnf_id+"'> </td>";
					temp += "<td>"+data.data[i].vnf_name+"</td>";
					temp += "<td>"+data.data[i].description+"</td>";
					temp += "<td>"+data.data[i].vnfd_name+"</td>";
					temp += "<td>"+data.data[i].status+"</td>";
					temp += "</tr>";
				}
				temp +="</table>";
			}else{
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='10%'>선택</th>"
					temp += "<th width='30%'>Name</th>";
				temp += "<th width='20%'>Description</th>";
				temp += "<th width='30%'>VNFD</th>";
				temp += "<th width='15%'>Status</th>";
				temp += "</tr>";
				temp += "</thead>";
				temp += "<tbody>";
				temp += "<tr><td colspan='5'>데이터가 없습니다.</td></tr>";
				temp += "</tbody></table>";
			}
			$("#vnf").html(temp);
		},
		beforeSend:function(){
			$('.wrap-loading').removeClass('display-none');
		},
		complete:function(){
			$('.wrap-loading').addClass('display-none');
		}
	});
});