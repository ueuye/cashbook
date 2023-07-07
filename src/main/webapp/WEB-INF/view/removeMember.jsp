<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember.jsp</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/on/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">이전</a>
	</div>
	
	<h1>회원탈퇴</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/on/removeMember"> 
		<table border="1">
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">탈퇴</button>
	</form>
</body>
</html>