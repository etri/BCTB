$(document).ready(function(){
		$.ajax({
			url : "getImage.do",
			type:"get",
			dataType:"json",
			success : function(data) {
				var temp="";
				if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				
				temp += "<tr>";
				temp += "<td class='table_count_td' colspan='7'>";
				temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
				temp += "</td>";
				temp += "</tr>";
				
				temp += "<tr>";
				temp += "<th width='10%'>소유자</th>"
				temp += "<th width='40%'>이름</th>";
				temp += "<th width='10%'>상태</th>";
				temp += "<th width='10%'>가시성</th>";
				temp += "<th width='10%'>보호됨</th>";
				temp += "<th width='10%'>디스크 포맷</th>";
				temp += "<th width='10%'>크기</th>";
				temp += "</tr>";
				temp += "</thead>";
				for(i=0; i<data.data.length; i++){
					temp += "<tr>";
					temp += "<td>"+data.data[i].project_name+"</td>";
					temp += "<td>"+data.data[i].name+"</td>";
					temp += "<td>"+data.data[i].status+"</td>";
					temp += "<td>"+data.data[i].visibility+"</td>";
					temp += "<td>"+data.data[i]._protected+"</td>";
					temp += "<td>"+data.data[i].format+"</td>";
					
					if(data.data[i].size < 1024){
						temp += "<td>"+((data.data[i].size / 1024)).toFixed(1)+"KB</td>";	
					}else if((data.data[i].size > (1024 * 1024)) && (data.data[i].size < (1024 * 1024 * 1024))){
						temp += "<td>"+((data.data[i].size / 1024)/1024).toFixed(1)+"MB</td>";
					}else if((data.data[i].size > (1024 * 1024 * 1024)) && (data.data[i].size < (1024 * 1024 * 1024 * 1024))){
						temp += "<td>"+((data.data[i].size / 1024) / 1024 / 1024).toFixed(1)+"GB</td>";
					}
						
					
					
					temp += "</tr>";
					}
					temp +="</table>";
				}
				else{
					temp += "<table>";
					temp += "<thead>";
					temp += "<tr>";
					temp += "<th width='10%'>소유자</th>"
					temp += "<th width='40%'>이름</th>";
					temp += "<th width='10%'>상태</th>";
					temp += "<th width='10%'>가시성</th>";
					temp += "<th width='10%'>보호됨</th>";
					temp += "<th width='10%'>디스크 포맷</th>";
					temp += "<th width='10%'>크기</th>";
					temp += "</tr>";
					temp += "</thead>";
					temp += "<tbody>";
					temp += "<tr><td colspan='8'>데이터가 없습니다</td>";
					temp += "</tbody></table>";
				}
					$("#image").html(temp);
				},
				beforeSend:function(){
					$('.wrap-loading').removeClass('display-none');
				},
				complete:function(){
					$('.wrap-loading').addClass('display-none');
				}
			});
		});