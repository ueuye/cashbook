<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookListByTag.jsp</title>
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
		<a href="#" class="is-current">HashTag</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="container w85 p-t-70">
		<h2 class="cl01 p-b-10"># ${word}</h2>
		<table border="1" class="table text-center">
			<thead>
				<tr>
					<th>번호</th>
					<th>카테고리</th>
					<th>금액</th>
					<th>날짜</th>
					<th>메모</th>
				</tr>
			</thead>
			<c:forEach var="l" items="${list }">
				<tr class="td01">
					<td class="td04">${l.cashbookNo }</td>
					<td class="td04">${l.category }</td>
					<td class="td04">${l.price }</td>
					<td class="td04">${l.cashbookDate }</td>
					<td class="td04">${l.memo }</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<!-- Pagination -->
		<div class="page_wrap">
  			<div class="page_nation">
				<c:if test="${startPage > 1}">
					<a class="arrow prev" href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${startPage-pagePerPage}&word=${word}"></a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<c:if test="${i == currentPage }">
						<a class="active" href="#">${i}</a>
					</c:if>
					<c:if test="${i != currentPage }">
						<a href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${i}&word=${word}">
			    			${i}
			    		</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage != lastPage}">
					<a class="arrow next" href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${startPage+pagePerPage}&word=${word}"></a>
				</c:if>
			</div>
		</div>
	</div>	
</div>
</body>
</html>