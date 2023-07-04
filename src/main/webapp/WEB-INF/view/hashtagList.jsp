<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hashtagList.jsp</title>
</head>
<body>
	<h1>#${word}</h1>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>카테고리</td>
			<td>날짜</td>
			<td>메모</td>
		</tr>
		<c:forEach var="l" items="${list }">
			<tr>
				<td>${l.memberId }</td>
				<td>${l.category }</td>
				<td>${l.cashbookDate }</td>
				<td>${l.memo }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>