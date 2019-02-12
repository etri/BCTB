<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../include/meta.jsp" %>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<script src="/resources/js/vcscfinfo.js"></script>
</head>
<body>
<div id="wrapper">
	<%@include file="../include/header.jsp" %>
	<div id="container">
		<%@include file="../include/side.jsp" %>
		<div id="contents">
			<div class="wrap-loading display-none">
			    <div><img src="/resources/img/loading_img.gif" /></div>
			</div>
			<div id="location_div">
				<p class="p1">vCSCF Info</p>
			</div>
		<div id="vcscfinfo"></div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</div>
</body>
</html>