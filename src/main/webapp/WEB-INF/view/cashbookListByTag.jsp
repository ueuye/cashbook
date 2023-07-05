<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination.css">
<title>cashbookListByTag.jsp</title>
</head>
<body>
	<div class="container pt-5">
		<h1>#${word}</h1>
		<br>
		<table border="1" class="table text-center">
			<tr>
				<td>번호</td>
				<td>카테고리</td>
				<td>금액</td>
				<td>날짜</td>
				<td>메모</td>
			</tr>
			<c:forEach var="l" items="${list }">
				<tr>
					<td>${l.cashbookNo }</td>
					<td>${l.category }</td>
					<td>${l.price }</td>
					<td>${l.cashbookDate }</td>
					<td>${l.memo }</td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- Pagination -->
		<div class="page_wrap">
  			<div class="page_nation">
				<c:if test="${startPage > 1}">
					<a class="arrow prev" href="${pageContext.request.contextPath}/cashbookListByTag?currentPage=${startPage-pagePerPage}&word=${word}"></a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<c:if test="${i == currentPage }">
						<a class="active" href="#">${i}</a>
					</c:if>
					<c:if test="${i != currentPage }">
						<a href="${pageContext.request.contextPath}/cashbookListByTag?currentPage=${i}&word=${word}">
			    			${i}
			    		</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage != lastPage}">
					<a class="arrow next" href="${pageContext.request.contextPath}/cashbookListByTag?currentPage=${startPage+pagePerPage}&word=${word}"></a>
				</c:if>
			</div>
		</div>	
	</div>
</body>
</html>