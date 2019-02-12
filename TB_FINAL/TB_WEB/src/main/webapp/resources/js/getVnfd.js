function view(id){
	$.ajax({
		url:"getVnfd.do",
		data:{vnfd_id:id},
		async:false,
		type:"get",
		dataType:"json",
		success:function(data){
			var temp = "";
			if(data.result!="fail"){
			temp += "<pre>";
			temp += data.list.vnfd;
			temp += "</pre>";
			}
			else{
				temp += "<pre>";
				temp += "값이 없습니다."
				temp += "</pre>";
			}
			$("#getVnfd").html(temp);
		},
		beforeSend:function(){
			$('.wrap-loading').removeClass('display-none');
		},
		complete:function(){
			$('.wrap-loading').addClass('display-none');
		}
	});
}