<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>
	<script>
	
		alert("${member.name}님 회원가입을 축하드립니다!\n 회원리스트 페이지로 이동합니다.");
		window.opener.location.href="<%=request.getContextPath()%>/member/list.do";
		window.close();

	</script>
</body>