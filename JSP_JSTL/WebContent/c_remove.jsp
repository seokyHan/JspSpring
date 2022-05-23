<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="msg" value="pageContext" scope="page"/>
<c:set var="msg" value="request" scope="request"/>
<c:set var="msg" value="session" scope="session"/>
<c:set var="msg" value="application" scope="application"/>

<!-- scope를 주지않으면 모든영역의 attribute가 msg인걸 다 지운다 -->
<!-- 그래서 특정 영역만 지우고 싶을땐 scope추가 -->
<c:remove var="msg" scope="session"/>

<ul>
	<li>pageContext : ${pageScope.msg }</li>
	<li>requestContext : ${requestScope.msg }</li>
	<li>sessionContext : ${sessionScope.msg }</li>
	<li>applicationContext : ${applicationScope.msg }</li>
</ul>