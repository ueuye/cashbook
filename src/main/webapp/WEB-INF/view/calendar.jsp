<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  JSP컴파일시 자바코드로 변환되는 c:...(제어문 코드) 커스템태그 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar.jsp</title>
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
		<a href="${pageContext.request.contextPath}/on/calendar" class="is-current">Calendar</a>
		<a href="${pageContext.request.contextPath}/on/cashbookChart?targetYear=${targetYear}&targetMonth=${targetMonth}">Chart</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<!-- 접속자 수  -->
	<div style="display:inline-block; float: right;">
		<div style="display: inline;">
			<img src="${pageContext.request.contextPath}/css/img/admin1.png" alt="IMG" style="width:15px; height:15px;">
			현재접속자 : ${currentCounter} <!-- application.getAttribute("currentCounter") -->
		</div>
		&nbsp;
		<div style="display: inline;">
			<img src="${pageContext.request.contextPath}/css/img/admin2.png" alt="IMG" style="width:15px; height:15px;">
			오늘접속자 : ${counter} <!-- application.getAttribute("counter") -->
		</div>
		&nbsp;
		<div style="display: inline;">
			<img src="${pageContext.request.contextPath}/css/img/admin2.png" alt="IMG" style="width:15px; height:15px;">
			누적접속자 : ${totalCounter} <!-- application.getAttribute("totalCounter") -->
		</div>
	</div>
	<br>
	<div class="p-t-5">
		<!-- targetMonth calendar -->
		<h1 class="dis">${strTargetMonth} ${targetYear}</h1>
		<div class="dis_r">
			<!-- 오늘 날짜 달력으로 이동 -->
			<a class="btn btn-sm b03 cl02 dis" href="${pageContext.request.contextPath}/on/calendar?targetYear=${todayYear}&targetMonth=${todayMonth}">today</a>
			<!-- 이전 달, 다음 달 이동 -->			
			<div class="btn-group btn-group-sm">
				<a class="btn b02 cl02 dis" href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">&#10094;</a>
				<a class="btn b02 cl02 dis" href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">&#10095;</a>
			</div>
		</div>
	</div>
	<br>
	<div class="p-l-20 p-b-10">
		<h5 class = "dis"># 이달의 해시태그 &nbsp;&nbsp;</h5>
		<div class = "dis">
			<c:forEach var="m" items="${htList}">
				<a class="cl01 an" href="${pageContext.request.contextPath}/on/cashbookListByTag?word=${m.word}">${m.word}(${m.cnt})</a>
			</c:forEach>
		</div>
	</div>
	
	<div class="shadow">
	<table class="table table-bordered">
		<tr class="text-center">
			<th>Sun</th>
			<th>Mon</th>
			<th>Tue</th>
			<th>Wed</th>
			<th>Thu</th>
			<th>Fri</th>
			<th>Sat</th>
		</tr>
		<tr>
			<c:forEach var="i" begin="0" end="${totalCell - 1}" step="1">
				<c:set var="d" value="${i-beginBlank+1}"></c:set>
				
				<c:if test="${i!=0 && i%7==0}">
					</tr><tr>
				</c:if>
				
				<c:if test="${d < 1 || d > lastDate}">
					<td>&nbsp;</td>
				</c:if>
				
				<c:if test="${!(d < 1 || d > lastDate)}">
					<td class="td02">
						<div class="t-r p-b-5">
							<a class="an cl03 p-r-10" href="${pageContext.request.contextPath}/on/cashbook?targetYear=${targetYear }&targetMonth=${targetMonth }&targetDate=${d}">${d}</a>
						</div>
						<c:forEach var="c" items="${sumList}">
							<c:if test="${d == fn:substring(c.cashbookDate,8,10) }">
								<div class="t-r">
									<c:if test="${c.category == '수입' }">
										<span class="cl04">+${c.sumPrice}</span>
									</c:if>
									<c:if test="${c.category == '지출' }">
										<span class="cl01">-${c.sumPrice}</span>
									</c:if>
								</div>
							</c:if>
						</c:forEach>
					</td>
				</c:if>
			</c:forEach>
	</table>
	</div>
	<br>
</div>
</body>
</html>