<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<% pageContext.setAttribute("msg", "pageContext"); %>
<% request.setAttribute("msg", "request");%>
<% session.setAttribute("msg", "session");%>
<% application.setAttribute("msg", "application");%>

스코프확인 세션에 있는 msg 사용하고 싶을때

<h1>${sessionScope.msg }</h1>