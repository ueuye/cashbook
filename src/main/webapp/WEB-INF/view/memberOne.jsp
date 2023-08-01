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
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<div class="container w70">
	<!-- navbar -->
	<nav>
		<a href="${pageContext.request.contextPath}/on/calendar">Calendar</a>
		<a href="${pageContext.request.contextPath}/on/cashbookChart">Chart</a>
		<a href="${pageContext.request.contextPath}/on/memberOne" class="is-current">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="container conc">
		<div class="p-t-5">
			<h1 class="dis">회원정보</h1>
			<div class="dis_r">
				<a class="btn btn-sm b03 cl02 dis" href="${pageContext.request.contextPath}/on/modifyMember">회원정보수정</a>
				<a class="btn btn-sm b03 cl02 dis" href="${pageContext.request.contextPath}/on/removeMember">회원정보탈퇴</a>
			</div>
		</div>
		<br>
		<div class="shadow bt">
		<table class="table table-bordered text-center">
			<tr>
				<td class="td03">아이디</td>
				<td class="td03"><%=member.getMemberId() %></td>
			</tr>
			<tr>
				<td class="td03">비밀번호</td>
				<td class="td03"><%=member.getMemberPw().substring(0,2) %>*****</td>
			</tr>
		</table>
		</div>	
	</div>
</div>	
</body>
</html>