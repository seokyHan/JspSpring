<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert('${member.name}님의 회원탈퇴가 정상적으로 이뤄졌습니다.');
	window.close();
	window.opener.parent.location.reload(true);

</script>