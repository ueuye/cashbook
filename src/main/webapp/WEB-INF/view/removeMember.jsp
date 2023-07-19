<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember.jsp</title>
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
		<a href="${pageContext.request.contextPath}/on/memberOne" class="is-current">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="container conc">
		<form method="post" action="${pageContext.request.contextPath}/on/removeMember"> 
			<div class="shadow">
				<table class="table table-bordered text-center">
						<tr>
							<td class="td03">
								<h4 class="cl01 p-t-10">회원탈퇴하시겠습니까?</h4><br>
								<h4 class="p-b-10">탈퇴를 진행하시려면 비밀번호를 입력해주세요.</h4>
							</td>
						</tr>
						<tr>
							<td class="td03"><input type="password" name="memberPw"></td>
						</tr>
				</table>
			</div>
			<br>
			<div class="text-center">
				<button type="submit" class="btn btn-sm b04 ts01 cl02 dis">확 인</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>