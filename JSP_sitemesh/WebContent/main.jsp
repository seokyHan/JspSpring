<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%-- <%@ include file="./header.jsp" %> --%>
<!-- 변수공유 가능 -->
<%-- <jsp:include page="./header.jsp" /> --%>
<!-- 변수공유 불가능 페이지 따로 만들어서 붙임-->
<style>
	body{background:yellow;}
</style>
main.jsp <br/>
header.jsp : message : ${message }
메인에서  <%-- <%@ include file="./header.jsp" %> --%> 가 없어서 컨텍스트 공유 안됨



<%-- <jsp:include page="./footer.jsp" /> --%>