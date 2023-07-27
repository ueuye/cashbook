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
		<a href="${pageContext.request.contextPath}/on/calendar">Calendar</a>
		<a href="#" class="is-current">Cashbook</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="container w85 p-t-70">
		<h2 class="dis">${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h2>
		<div class="dis_r p-b-30">
			<a class="btn b03 cl02 dis" href="${pageContext.request.contextPath}/on/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">추가</a>
		</div>
		<table border="1" class="table text-center shadow">
			<tr>
				<th>카테고리</th>
				<th>서브 카테고리</th>
				<th>금액</th>
				<th>메모</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach var="c" items="${list}">
				<tr>
					<td class="td04">${c.category }</td>
					<td class="td04">${c.subcategory }</td>
					<td class="td04">${c.price }</td>
					<td class="td04">${c.memo }</td>
					<td class="td04"><a class="an cl01" href="${pageContext.request.contextPath}/on/modifyCashbook?cashbookNo=${c.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">수정</a></td>
					<td class="td04"><a class="an cl01" href="${pageContext.request.contextPath}/on/removeCashbook?cashbookNo=${c.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">삭제</a></td>
				</tr>		
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>