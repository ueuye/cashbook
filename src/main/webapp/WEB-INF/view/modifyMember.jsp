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
<h1>회원정보 수정</h1>
	<form method="post" action="${pageContext.request.contextPath}/modifyMember">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><%=member.getMemberId() %></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>