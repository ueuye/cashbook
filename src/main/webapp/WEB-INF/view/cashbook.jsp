<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbook.jsp</title>
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
		<a href="#" class="is-current">Cashbook</a>
		<a href="${pageContext.request.contextPath}/on/calendar">Calendar</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="p-t-5">
		<h2 class="dis">${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h2>
		<div class="dis_r">
			<a class="btn b03 cl02 dis" href="${pageContext.request.contextPath}/on/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">추가</a>
		</div>
	</div>
	<br>
	<table border="1" class="table text-center shadow">
		<tr>
			<th>카테고리</th>
			<th>금액</th>
			<th>메모</th>
		</tr>
		<c:forEach var="c" items="${list}">
			<tr>
				<td>${c.category }</td>
				<td>${c.price }</td>
				<td>${c.memo }</td>
			</tr>		
		</c:forEach>
	</table>
</div>
</body>
</html>