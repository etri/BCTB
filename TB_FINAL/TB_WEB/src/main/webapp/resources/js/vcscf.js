function vcscf(){
//$(document).ready(function(){
		$.ajax({
			url : "vCSCF2.do",
			type:"get",
			dataType:"json",
			success : function(data) {
				var temp="";
				for(var i=0; i<data.data.length; i++){
					temp += "<table>";
					temp += "<tr>";
					temp += "<th colspan='2'>"+data.data[i].description+"</th></tr>";
					temp += "<tr>";
					temp += "<th width='20%'>Status</th>";
					temp += "<td>"+data.data[i].status+"</td>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th width='20%'>Flavor</th>";
					temp += "<td>"+data.data[i].flavor+"</td>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th width='20%'>Image</th>";
					temp += "<td>"+data.data[i].image+"<td>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th width='20%'>CREATE_TIME</th>";
					temp += "<td>"+data.data[i].create_time+"</td>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th width='20%'>UPDATE_TIME</th>";
					temp += "<td>"+data.data[i].update_time+"</td>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th width='20%'>MGMT IP</th>";
					temp += "<td>"+data.data[i].mgmtIp+"</td>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th width='20%'>Private IP</th>";
					temp += "<td>"+data.data[i].privateIp+"</td>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th width='20%'>MAC</th>";
					temp += "<td>"+data.data[i].mac+"</td>";
					temp += "</tr>";
					temp += "</table>";
					temp += "<br>";
					}
					$("#computenode1").html(temp);
				},	
				beforeSend:function(){
					$('.wrap-loading').removeClass('display-none');
				},
				complete:function(){
					$('.wrap-loading').addClass('display-none');
				}
			});
		//});
		}