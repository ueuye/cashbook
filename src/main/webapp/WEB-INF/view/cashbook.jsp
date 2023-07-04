<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbook.jsp</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/calendar">이전</a>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/memberOne">회원정보</a>
	</div>
	
	<h1>${targetYear}년 ${targetMonth+1}월 ${targetDate}일 가계부</h1>
	<table border="1">
		<tr>
			<td>카테고리</td>
			<td>금액</td>
			<td>메모</td>
		</tr>
		<c:forEach var="c" items="${list}">
			<tr>
				<td>${c.category }</td>
				<td>${c.price }</td>
				<td>${c.memo }</td>
			</tr>		
		</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">
		추가
	</a>
</body>
</html>