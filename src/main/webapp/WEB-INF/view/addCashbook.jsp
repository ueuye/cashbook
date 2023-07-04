<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCashbook.jsp</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">이전</a>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/memberOne">회원정보</a>
	</div>
	
	<h1>가계부 추가</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}"> 
		<input type="hidden" name="memberId" value="${memberId}">
		<input type="hidden" name="cashbookDate" value="${cashbookDate}">
		<table border="1">
			<tr>
				<td>날짜</td>
				<td><input type="text" value="${targetYear}년 ${targetMonth+1}월 ${targetDate}일" readonly="readonly"></td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
	                <select name ="category">
	            		<option>==선택하세요==</option> 
	            		<option value="수입">수입</option>
	           			<option value="지출">지출</option>
	                </select>
	            </td>
			</tr>
			<tr>
				<td>금액</td>
				<td><input type="number" name="price"></td>
			</tr>
			<tr>
				<td>메모</td>
				<td><input type="text" name="memo"></td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>