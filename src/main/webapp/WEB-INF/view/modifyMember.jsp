<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMember.jsp</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/memberOne">이전</a>
	</div>
	
	<h1>회원정보 수정</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/modifyMember">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><%=member.getMemberId() %></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>