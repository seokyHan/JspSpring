<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String str = "a,b,c";
	/*default false new StringTokenizer(str,",",false); true일때 구분자 , 까지 출력 */
	StringTokenizer token = new StringTokenizer(str,",");
	
	while(token.hasMoreTokens()){
		out.println(token.nextElement());
		out.println("<br/>");
	}
%>

<hr>
<c:set var="str" value="a,b,c"/>
<c:forTokens var="i" items="${str }" delims=",">
	${i }<br/>
</c:forTokens>