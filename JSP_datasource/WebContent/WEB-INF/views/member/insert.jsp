<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1px">
	
	<form action="insert" method="post" id="insert">
		<tr><th>이름</th><td><input id="name" name="name"></td>      <tr>
		<tr><th>ID</th><td><input id="id" name="id"></td><tr>
		<tr><th>PWD</th><td><input id="pwd" name="pwd"></td>      <tr>
		<tr><th>E-Mail</th><td><input id="email" name="email"></td>  <tr>
		<tr><th>전화번호</th><td><input id="phone" name="phone"></td>  <tr>
		<tr><th>주소</th><td><input id="addr" name="addr"></td>      <tr>
	</form>
	
	

</table>
<input type="button" value="추가" onclick="insert()">
<input type="button" value="취소" onclick="cancel()">

<script>
	
	function insert(){
		document.getElementById("insert").submit();
	}
	
	function cancel(){
		check = confirm("작성한 내용은 사라집니다. 취소하시겠습니까?")
		
		if(check){
			opener.parent.location.reload();
			window.close();
		}
	}

</script>