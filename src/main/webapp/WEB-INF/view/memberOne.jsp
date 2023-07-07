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
	<div>
		<a href="${pageContext.request.contextPath}/on/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/on/calendar">캘린더</a>
	</div>
	
	<h1>회원상세정보</h1>
		<a href="${pageContext.request.contextPath}/on/modifyMember">회원정보수정</a>
		<a href="${pageContext.request.contextPath}/on/removeMember">회원정보탈퇴</a>
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><%=member.getMemberId() %></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><%=member.getMemberPw().substring(0,2) %>*****</td>
			</tr>
		</table>
</body>
</html>