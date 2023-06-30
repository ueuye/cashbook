<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne.jsp</title>
</head>
<body>
	<h1>회원상세정보</h1>
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><%=member.getMemberId() %></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><%=member.getMemberPw().substring(0,2) %>*****</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/modifyMember">회원정보수정</a>
		<a href="${pageContext.request.contextPath}/removeMember">회원정보탈퇴</a>
</body>
</html>