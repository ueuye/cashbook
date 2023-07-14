<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyCashbook.jsp</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/on/modifyCashbook?cashbookNo=${cashbook.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}"> 
		<table border="1">
			<tr>
				<td>날짜</td>
				<td><input type="text" value="${targetYear}년 ${targetMonth+1}월 ${targetDate}일" readonly="readonly"></td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
	                <select name ="category">
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
	            		<!-- <option>==선택하세요==</option> 
	            		<option value="수입">수입</option>
	           			<option value="지출">지출</option> -->
	                </select>
	            </td>
			</tr>
			<tr>
				<td>금액</td>
				<td><input type="number" name="price" value="${cashbook.price }"></td>
			</tr>
			<tr>
				<td>메모</td>
				<td><input type="text" name="memo" value="${cashbook.memo }"></td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>