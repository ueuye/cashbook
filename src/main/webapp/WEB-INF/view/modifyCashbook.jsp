<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyCashbook.jsp</title>
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
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<a href="${pageContext.request.contextPath}/on/memberOne" class="is-current">MyPage</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="container conc">
		<div class="p-t-5">
			<h1>가계부 수정</h1>
		</div>
		<br>
		<form method="post" action="${pageContext.request.contextPath}/on/modifyCashbook?cashbookNo=${cashbook.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}"> 
			<div class="shadow">
				<table class="table table-bordered text-center">
					<tr>
						<td class="td03">날짜</td>
						<td class="td03" style="display: flex; justify-content: center;"><input class="text-center" style="border:none;" type="text" value="${targetYear}년 ${targetMonth+1}월 ${targetDate}일" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="td03">카테고리</td>
						<td class="td03" style="display: flex; justify-content: center;">
			                <select name ="category" class="text-center" style="border:none;">
				                <c:if test="${cashbook.category eq '수입' }">
				                	<option>==선택하세요==</option> 
				            		<option value="수입" selected="selected">수입</option>
				           			<option value="지출">지출</option>
				                </c:if>
				                <c:if test="${cashbook.category eq '지출'}">
				                	<option>==선택하세요==</option> 
				            		<option value="수입">수입</option>
				           			<option value="지출" selected="selected">지출</option>
				                </c:if>
			                </select>
			            </td>
					</tr>
					<tr>
						<td class="td03">금액</td>
						<td class="td03" style="display: flex; justify-content: center;"><input class="text-center" style="border:none;" type="number" name="price" value="${cashbook.price }"></td>
					</tr>
					<tr>
						<td class="td03">메모</td>
						<td class="td03" style="display: flex; justify-content: center;"><input class="text-center" style="border:none;" type="text" name="memo" value="${cashbook.memo }"></td>
					</tr>
				</table>
			</div>
			<button type="submit" class="btn btn-sm b03 cl02 dis_r_0"   >입력</button>
		</form>
</body>
</html>