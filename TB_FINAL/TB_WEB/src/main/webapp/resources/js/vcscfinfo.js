$(document).ready(function(){
		$.ajax({
			url : "vCSCF.do",
			type:"get",
			dataType:"json",
			success : function(data) {
				if(data.result!="fail"){
				var temp="";
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th colspan='6'>vCSCF</th>";
				temp += "</tr>";
				temp += "<tr>";
				temp += "<th>HSS Address</th>";
				temp += "<th>Domain</th>";
				temp += "<th>V-IP</th>";
				temp += "<th>PCSCF</th>";
				temp += "<th>ICSCF</th>";
				temp += "<th>SCSCF</th>";
				temp += "</tr>";
				temp += "</thead>";
				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td>"+data.data[i].hss_ip+"</td>";
					temp += "<td>"+data.data[i].domain_name+"</td>";
					temp += "<td>"+data.data[i].virtual_ip+"</td>";
					temp += "<td>"+data.data[i].pcscf_domain+"</td>";
					temp += "<td>"+data.data[i].icscf_domain+"</td>";
					temp += "<td>"+data.data[i].scscf_domain+"</td>";
					temp += "</tr>";
					}
					temp +="</table>";
				}
				else{
					temp += "<table>";
					temp += "<thead>";
					temp += "<tr>";
					temp += "<th colspan='6'>vCSCF</th>";
					temp += "</tr>";
					temp += "<tr>";
					temp += "<th>HSS Address</th>";
					temp += "<th>Domain</th>";
					temp += "<th>V-IP</th>";
					temp += "<th>PCSCF</th>";
					temp += "<th>ICSCF</th>";
					temp += "<th>SCSCF</th>";
					temp += "</tr>";
					temp += "</thead>";
					temp += "<tbody>";
					temp += "<tr><td colspan='6'>데이터가 없습니다</td></tr>";
					temp += "</tbody>";
					temp += "</table>";
				}
					$("#vcscfinfo").html(temp);
				},
				beforeSend:function(){
					$('.wrap-loading').removeClass('display-none');
				},
				complete:function(){
					$('.wrap-loading').addClass('display-none');
				}
			});
		});