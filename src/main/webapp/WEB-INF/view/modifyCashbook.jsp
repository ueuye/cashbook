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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<script>
	$(document).ready(function(){
		// 카테고리선택시 서브카테고리 리스트 출력
		$('#category').change(function(){ // 대분류가 변경되었을때
			if($('#category').val() == '') {
				$('#subcategory').empty();
				$('#subcategory').append('<option value="">==서브 카테고리==</option>');
			} else{
				$.ajax({
					url:'${pageContext.request.contextPath}/on/category',
					type:'get',
					contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					data:{category: $('#category').val() },
					dataType: 'json',
					success:function(param){
						console.log(param);
						$('#subcategory').empty();
						$('#subcategory').append('<option value="">==서브 카테고리==</option>');
						// param을 subcategory 추가
						$(param).each(function(index, item){
							let str = '<option value="'+item+'">'+item+'</option>';
							$('#subcategory').append(str);                     
						});
					},
				});
			}
		});
		// 입력폼 유효성 검사
		$('#btn').click(function(){
			if($('#category').val() == ''){
				alert('카테고리를 입력해주세요');
			}else if($('#subcategory').val() == ''){
				alert('서브카테고리를 입력해주세요');
			}else if($('#price').val() == ''){
				alert('금액을 입력해주세요');
			}else if($('#memo').val() == ''){
				alert('메모를 입력해주세요');
			}else {
				$('#modifyCashbook').submit();
			}
		});
	});
</script>
<body>
<div class="container w70">
	<!-- navbar -->
	<nav>
		<a href="${pageContext.request.contextPath}/on/calendar">Calendar</a>
		<a href="${pageContext.request.contextPath}/on/cashbookChart">Chart</a>
		<a href="${pageContext.request.contextPath}/on/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="is-current">Cashbook</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<div class="container conc">
		<div class="p-t-5">
			<h1>가계부 수정</h1>
		</div>
		<br>
		<form method="post" id="modifyCashbook" action="${pageContext.request.contextPath}/on/modifyCashbook?cashbookNo=${cashbook.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}"> 
			<div class="shadow">
				<table class="table table-bordered text-center">
					<tr>
						<td class="td03">날짜</td>
						<td class="td03 dis_c"><input class="text-center bn" type="text" value="${targetYear}년 ${targetMonth+1}월 ${targetDate}일" readonly="readonly"></td>
					</tr>
					<tr><!-- 대분류 -->
						<td class="td03">카테고리</td>
						<td class="td03 dis_c">
			                <select id="category" name ="category" class="text-center bn">
				                <c:if test="${cashbook.category eq '수입' }">
				                	<option value="">==메인 카테고리==</option> 
				            		<option value="수입" selected="selected">수입</option>
				           			<option value="지출">지출</option>
				                </c:if>
				                <c:if test="${cashbook.category eq '지출'}">
				                	<option value="">==메인 카테고리==</option> 
				            		<option value="수입">수입</option>
				           			<option value="지출" selected="selected">지출</option>
				                </c:if>
			                </select>
			            </td>
					</tr>
					<tr><!-- 소분류 -->
						<td class="td03">서브 카테고리</td>
						<td class="td03 dis_c">
			                <select name ="subcategory" id="subcategory" class="text-center bn">
			               		<c:if test="${cashbook.subcategory eq '월급' }">
									<option value="">==서브 카테고리==</option>	
									<option value="월급" selected="selected">월급</option>
				           			<option value="용돈">용돈</option>
				           			<option value="주식">주식</option>
				           			<option value="기타">기타</option>		               		
			               		</c:if>
			               		<c:if test="${cashbook.subcategory eq '용돈' }">
									<option value="">==서브 카테고리==</option>	
									<option value="월급">월급</option>
				           			<option value="용돈" selected="selected">용돈</option>
				           			<option value="주식">주식</option>
				           			<option value="기타">기타</option>		               		
			               		</c:if>
			               		<c:if test="${cashbook.subcategory eq '주식' }">
									<option value="">==서브 카테고리==</option>	
									<option value="월급">월급</option>
				           			<option value="용돈">용돈</option>
				           			<option value="주식" selected="selected">주식</option>
				           			<option value="기타">기타</option>		               		
			               		</c:if>
			               		<c:if test="${cashbook.category eq '수입' }">
									<c:if test="${cashbook.subcategory eq '기타' }">
										<option value="">==서브 카테고리==</option>	
										<option value="월급">월급</option>
					           			<option value="용돈">용돈</option>
					           			<option value="주식">주식</option>
					           			<option value="기타" selected="selected">기타</option>		               		
				               		</c:if>			               		
			               		</c:if>
			               		<c:if test="${cashbook.subcategory eq '식비' }">
									<option value="">==서브 카테고리==</option>	
									<option value="식비" selected="selected">식비</option>
				           			<option value="교통비">교통비</option>
				           			<option value="쇼핑">쇼핑</option>
				           			<option value="저축">저축</option>
				           			<option value="기타">기타</option>		               		
			               		</c:if>
			               		<c:if test="${cashbook.subcategory eq '교통비' }">
									<option value="">==서브 카테고리==</option>	
									<option value="식비">식비</option>
				           			<option value="교통비" selected="selected">교통비</option>
				           			<option value="쇼핑">쇼핑</option>
				           			<option value="저축">저축</option>
				           			<option value="기타">기타</option>		               		
			               		</c:if>
			               		<c:if test="${cashbook.subcategory eq '쇼핑' }">
									<option value="">==서브 카테고리==</option>	
									<option value="식비">식비</option>
				           			<option value="교통비">교통비</option>
				           			<option value="쇼핑" selected="selected">쇼핑</option>
				           			<option value="저축">저축</option>
				           			<option value="기타">기타</option>		               		
			               		</c:if>
			               		<c:if test="${cashbook.subcategory eq '저축' }">
									<option value="">==서브 카테고리==</option>	
									<option value="식비">식비</option>
				           			<option value="교통비">교통비</option>
				           			<option value="쇼핑">쇼핑</option>
				           			<option value="저축" selected="selected">저축</option>
				           			<option value="기타">기타</option>		               		
			               		</c:if>
			               		<c:if test="${cashbook.category eq '지출' }">
			               			<c:if test="${cashbook.subcategory eq '기타' }">
										<option value="">==서브 카테고리==</option>	
										<option value="식비">식비</option>
					           			<option value="교통비">교통비</option>
					           			<option value="쇼핑">쇼핑</option>
					           			<option value="저축">저축</option>
					           			<option value="기타" selected="selected">기타</option>		               		
			               			</c:if>
			               		</c:if>
			                </select>
			            </td>
					</tr>
					<tr>
						<td class="td03">금액</td>
						<td class="td03 dis_c"><input class="text-center bn" type="number" id="price" name="price" value="${cashbook.price }"></td>
					</tr>
					<tr>
						<td class="td03">메모</td>
						<td class="td03 dis_c"><input class="text-center bn" type="text" id="memo" name="memo" value="${cashbook.memo }"></td>
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