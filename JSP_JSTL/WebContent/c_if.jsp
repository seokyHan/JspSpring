<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String str = "abc"; %>

<c:set var="str" value="<%=str %>" />
<!-- scope default는 page request에 담아늫으면 scope가 request로 바뀜 -->
<c:if test="${str eq 'abc'}" var="result" scope="request">
	${str }
</c:if>

::::${result}