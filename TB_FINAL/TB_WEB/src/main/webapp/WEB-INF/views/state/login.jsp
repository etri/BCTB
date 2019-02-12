<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/bctt/resources/css/login.css">
	<script type="text/javascript" src="resources/js/layer.js"></script>
	<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		
		function Login(){
			var id = document.getElementById("login_id").value;
			var pw = document.getElementById("login_pw").value;
			
			if(id == 'etri' && pw == 'etri'){
				document.login_form.action ="/bctt/command/loginSuccess.do";
				document.login_form.submit();
			}else{
				alert('아이디 또는 비밀번호가 일치하지 않습니다.');
				return false;
			}
		}
		
		function EnterLogin(){
			var keyCode = window.event.keyCode;
			if(keyCode==13) Login();
		}
	
	</script>
</head>
<body>
	<div class="login">
		<h1>블록체인 자동화 테스트 도구</h1>
	    <form method="post" id="login_form" name="login_form" onsubmit="return Login()">
	    	<input id="login_id" type="text" name="login_id" placeholder="ID" required="required" />
	        <input id="login_pw" type="password" name="login_pw" placeholder="Password" required="required" onkeydown="EnterLogin()"/>
	        <button type="submit" class="btn btn-primary btn-block btn-large">Log In</button>
	    </form>
	</div>
</body>
</html>