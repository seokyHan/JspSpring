<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>request parameter message : </h1>
		<ul>
			<li> 스크립트릿 : <% out.print(request.getParameter("message")); %></li>
			<li> 표현식 : <%= request.getParameter("message") %></li>
			<li> EL : ${param.message}</li>
		</ul>	
	<h1>request Attribute message : </h1>
		<ul>
			<li> 스크립트릿 : <% out.print(request.getAttribute("message")); %></li>
			<li> 표현식 : <%= request.getAttribute("message") %></li>
			<li> EL : ${message}</li>
		</ul>
</body>
</html>