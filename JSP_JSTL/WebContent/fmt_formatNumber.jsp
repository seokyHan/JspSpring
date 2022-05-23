<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
	int price = 1000000000;
	String priceStr = new DecimalFormat("#,###").format(price);
	out.print("￦" + priceStr);
%>

<hr/>

<c:set var="i" value="1000000000" />
<fmt:formatNumber value="${i }" pattern="#,###" var="price" scope="page" />
	￦${price }