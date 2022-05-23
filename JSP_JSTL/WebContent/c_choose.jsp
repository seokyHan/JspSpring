<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 배열임 -->
<c:set var="k" value="1,2,3" />

<c:forEach items="${k }" var="m">

<!--if else if else 와 같은 맥략 -->
<c:choose>
	<c:when test="${m eq 1 }">
		m 값은 1입니다. m=${m }<br/>
	</c:when>
	<c:when test="${m eq 3 }">
		m 값은 3입니다. m=${m }<br/>
	</c:when>
	<c:otherwise>
		m 값은 1도 3도 아닙니다. m=${m }<br/>
	</c:otherwise>
</c:choose>

</c:forEach>