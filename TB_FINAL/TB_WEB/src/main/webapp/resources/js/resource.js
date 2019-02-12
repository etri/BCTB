$(document).ready(function(){
	$.ajax({
		url : "hostList.do",
		type:"get",
		dataType:"json",
		success : function(data) {
			var temp="";
			
			var useCpu = 0;
			var totalCpu = 0;
			
			var useRam = 0;
			var totalRam = 0;
			
			var useDisk = 0;
			var totalDisk = 0;
			
			if(data.result!="fail"){
				temp += "<table>";
				temp += "<thead>";
				
				temp += "<tr>";
				temp += "<td class='table_count_td' colspan='7'>";
				temp += "<span class='table_count'>Displaying "+data.data.length+" items</span>";
				temp += "</td>";
				temp += "</tr>";
				
				temp += "<tr>";
				temp += "<th width='10%'>호스트</th>";
				temp += "<th width='15%'>VCPUs(사용중)</th>";
				temp += "<th width='15%'>VCPUs(전체)</th>";
				temp += "<th width='15%'>RAM(사용중)</th>";
				temp += "<th width='15%'>RAM(전체)</th>";
				temp += "<th width='15%'>로컬 저장소(사용됨)</th>";
				temp += "<th width='15%'>로컬 저장소(전체)</th>";
				temp += "</tr>";
				temp += "</thead>";

				for(i=0; i<data.data.length; i++){
					
					useCpu += Number(data.data[i].useCpu);
					totalCpu += Number(data.data[i].totalCpu);
					
					useRam += Number(data.data[i].useMem);
					totalRam += Number(data.data[i].totalMem);
					
					useDisk += Number(data.data[i].useDisk);
					totalDisk += Number(data.data[i].totalDisk);
					
					temp += "<tr>";
					temp += "<td>"+data.data[i].host+"</a></td>";
					temp += "<td>"+data.data[i].useCpu+"</td>";
					temp += "<td>"+data.data[i].totalCpu+"</td>";
					temp += "<td>"+(data.data[i].useMem / 1024) +"GB</td>";
					temp += "<td>"+(data.data[i].totalMem / 1024).toFixed(2)+"GB</td>";
					temp += "<td>"+data.data[i].useDisk+"GB</td>";
					temp += "<td>"+data.data[i].totalDisk+"GB</td>";
					temp += "</tr>";
				}
				
				temp +="</table>";
				
			}else{
				temp += "<table>";
				temp += "<thead>";
				temp += "<tr>";
				temp += "<th width='10%'>호스트</th>";
				temp += "<th width='15%'>VCPUs(사용중)</th>";
				temp += "<th width='15%'>VCPUs(전체)</th>";
				temp += "<th width='15%'>RAM(사용중)</th>";
				temp += "<th width='15%'>RAM(전체)</th>";
				temp += "<th width='15%'>로컬 저장소(사용됨)</th>";
				temp += "<th width='15%'>로컬 저장소(전체)</th>";
				temp += "</tr>";
				temp += "</thead>";
				temp += "<tbody>";
				temp += "<tr><td colspan='7'>데이터가 없습니다</td>";
				temp += "</tbody></table>";
			}
			$("#computenode0").html(temp);
			
			console.dir("useCpu :" + useCpu + ", totalCpu ;" + totalCpu);
			console.dir("useRam :" + useRam + ", totalRam ;" + totalRam);
			console.dir("useDisk :" + useDisk + ", totalDisk ;" + totalDisk);
			
			var cpuData = {미사용: Number(totalCpu - useCpu), 사용중: useCpu};
			var memData = {미사용: Number(totalRam - useRam), 사용중: useRam};
			var diskData = {미사용: Number(totalDisk - useDisk), 사용중: useDisk};
			
			var chartDonut = c3.generate({
				bindto: "#piechart1",
				data: {
					json: [cpuData],
					keys: {
						value: Object.keys(cpuData),
					},
					type: "donut",
				},
				donut: {
					title: "VCPU 사용량",
				},
				});
			
			var chartDonut = c3.generate({
				  bindto: "#piechart2",
				  data: {
				    json: [memData],
				    keys: {
				      value: Object.keys(memData),
				    },
				    type: "donut",
				  },
				  donut: {
				    title: "메모리 사용량",
				  },
				});
			
			var chartDonut = c3.generate({
				  bindto: "#piechart3",
				  data: {
				    json: [diskData],
				    keys: {
				      value: Object.keys(diskData),
				    },
				    type: "donut",
				  },
				  donut: {
				    title: "디스크 사용량",
				  },
				});
		},
		beforeSend:function(){
			$('.wrap-loading').removeClass('display-none');
		},
		complete:function(){
			$('.wrap-loading').addClass('display-none');
		}
	});
});