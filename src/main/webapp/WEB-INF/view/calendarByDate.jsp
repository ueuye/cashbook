<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendarByDate.jsp</title>
</head>
<body>
	<h1>${targetYear}년 ${targetMonth+1}월 ${targetDay}일 수입/지출 목록</h1>
	<table border="1">
		<tr>
			<td>category</td>
			<td>price</td>
			<td>memo</td>
		</tr>
		<c:forEach var="c" items="${list}">
			<tr>
				<td>${c.category }</td>
				<td>${c.price }</td>
				<td>${c.memo }</td>
			</tr>		
		</c:forEach>
	</table>
</body>
</html>