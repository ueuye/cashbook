<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  JSP컴파일시 자바코드로 변환되는 c:...(제어문 코드) 커스템태그 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar.jsp</title>
</head>
<body>
	<!-- 변수값or반환값 : EL사용 $ 표현식 -->
	<!-- 
		속성값대신 EL사용 
		ex) 
		request.getAttribute("targetYear") -- requestScope.targetYear 
		(requestScope는 생략가능)
		형변환연산이 필요없다(EL이 자동으로 처리)
	-->
	
	<!-- 자바코드(제어문) : JSTL 사용 -->
		
	<h1>${targetYear}년 ${targetMonth+1}월</h1>
	<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전</a>
	<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음</a>
	
	<table border="1">
		<tr>
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<tr>
			<c:forEach var="i" begin="0" end="${totalCell - 1}" step="1">
				<c:set var="d" value="${i-beginBlank+1}"></c:set>
				
				<c:if test="${i!=0 && i%7==0}">
					</tr><tr>
				</c:if>
				
				<c:if test="${d < 1 || d > lastDate}">
					<td></td>
				</c:if>
				
				<c:if test="${!(d < 1 || d > lastDate)}">
					<td>
						<div>
							<a href="${pageContext.request.contextPath}/calendarByDate?y=${targetYear }&m=${targetMonth }&d=${d}">${d}</a>
						</div>
						<c:forEach var="c" items="${list}">
							<c:if test="${d == fn:substring(c.cashbookDate,8,10) }">
								<div>
									<c:if test="${c.category == '수입' }">
										<span> + ${c.price }</span>
									</c:if>
									<c:if test="${c.category == '지출' }">
										<span style="color:red;"> - ${c.price }</span>
									</c:if>
								</div>
							</c:if>
						</c:forEach>
					</td>
				</c:if>
			</c:forEach>
	</table>
</body>
</html>