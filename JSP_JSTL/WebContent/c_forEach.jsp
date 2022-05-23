<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	int sum = 0;
	for(int i = 1; i<11; i++){
		sum += i;
	}
	
	out.println("out.println(sum)" + sum +"<br/>");
%>

<!-- var 는 변수를 표현 forEach에서 step default는 1 (생략가능) -->
<% pageContext.setAttribute("sum", 0); %>
<!-- 를 아래처럼 표현식으로 나타냄 scope="page"는 생략 가능 -->
<c:set scope="page" var="sum" value="0" />

<c:forEach begin="1" end="10" step="1" var="i">
	<c:set var="sum" value="${sum+i }" />
</c:forEach>

out.println(pageContext.getAttribute("sum")) : <% out.println(pageContext.getAttribute("sum")); %> <br>
<%= pageContext.getAttribute("sum") %> <br/>

＄{sum } : ${sum }

<br>

<%
	List<String> strList = new ArrayList<>();
	strList.add(new String("a"));
	strList.add("b");
	
	if(strList!=null) for(String str : strList){
		out.println(str + "<br/>");
	}
%>

<c:set var="strList" value="<%=strList %>" />
<c:forEach items="${strList }" var="str">
	${str }<br/>
</c:forEach>













