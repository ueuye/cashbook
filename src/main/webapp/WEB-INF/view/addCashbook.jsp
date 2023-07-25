<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCashbook.jsp</title>
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
			if($('#category').val() == '==선택하세요=='){
				alert('카테고리를 입력해주세요');
			}else if($('#price').val() == ''){
				alert('금액을 입력해주세요');
			}else if($('#memo').val() == ''){
				alert('메모를 입력해주세요');
			}else {
				$('#addCashbook').submit();
			}
		});
	});
</script>
<body>
<div class="container w70">
	<!-- navbar -->
	<nav>
		<a href="${pageContext.request.contextPath}/on/calendar">Calendar</a>
		<a href="${pageContext.request.contextPath}/on/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="is-current">Cashbook</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="container conc">
		<div class="p-t-5">
			<h1>가계부 추가</h1>
		</div>
		<br>
		<form method="post" id="addCashbook" action="${pageContext.request.contextPath}/on/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}"> 
			<div class="shadow">
				<input type="hidden" name="memberId" value="${memberId}">
				<input type="hidden" name="cashbookDate" value="${cashbookDate}">
				<table class="table table-bordered text-center">
					<tr>
						<td class="td03">날짜</td>
						<td class="td03 dis_c"><input class="text-center bn" type="text" value="${targetYear}년 ${targetMonth+1}월 ${targetDate}일" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="td03">카테고리</td>
						<td class="td03 dis_c">
			                <select name ="category" id="category">
			            		<option>==선택하세요==</option> 
			            		<option value="수입">수입</option>
			           			<option value="지출">지출</option>
			                </select>
			            </td>
					</tr>
					<tr>
						<td class="td03">금액</td>
						<td class="td03 dis_c"><input type="number" name="price" id="price"></td>
					</tr>
					<tr>
						<td class="td03">메모</td>
						<td class="td03 dis_c"><input type="text" name="memo" id="memo"></td>
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