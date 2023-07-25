<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMember.jsp</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<script>
	// 입력폼 유효성 검사
	$(document).ready(function(){
		$('#btn').click(function(){
			if($('#currentPw').val() == ''){
				alert('현재 비밀번호를 입력해주세요');
			}else if($('#memberPw').val() == ''){
				alert('변경할 비밀번호를 입력해주세요');
			}else if($('#confirmPw').val() == ''){
				alert('비밀번호 재확인을 입력해주세요');
			}else if($('#memberPw').val() != $('#confirmPw').val()){
				alert('비밀번호 재확인이 일치하지 않습니다');
				$("#memberPw").val("");
				$("#confirmPw").val("");
	            $('#memberPw').focus();
			}else {
				$('#modifyMember').submit();
			}
		});
	});
</script>
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
		<div class="p-t-5">
			<h1>회원정보 수정</h1>
		</div>
		<br>
		<form method="post" action="${pageContext.request.contextPath}/on/modifyMember" id="modifyMember">
			<div class="shadow">
				<table class="table table-bordered text-center">
					<tr>
						<td class="td03">아이디</td>
						<td class="td03"><%=member.getMemberId() %></td>
					</tr>
					<tr>
						<td class="td03">현재 비밀번호</td>
						<td class="td03"><input type="password" id="currentPw" name="currentPw"></td>
					</tr>
					<tr>
						<td class="td03">변경 비밀번호</td>
						<td class="td03"><input type="password" id="memberPw" name="memberPw"></td>
					</tr>
					<tr>
						<td class="td03">변경 비밀번호 확인</td>
						<td class="td03"><input type="password" id="confirmPw" name="confirmPw"></td>
					</tr>
				</table>
			</div>
			<br>
			<div class="text-center">
				<button id="btn" type="button" class="btn btn-sm b04 ts01 cl02 dis">확 인</button>
			</div>
		</form>	
	</div>
</div>
</body>
</html>