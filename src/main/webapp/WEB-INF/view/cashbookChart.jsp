<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookChart.jsp</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script>
<script>
$(document).ready(function(){
	
	const x = [];
	const y = [];
	
	const targetYear = <c:out value = "${targetYear}"/>;
	const targetMonth = <c:out value = "${targetMonth}"/>;
	const memberId = "<c:out value = "${memberId}"/>";
	
	// 동기호출로 x,y 값을 셋팅
	$.ajax({
		async : false, // true : 비동기(기본값), false : 동기
		url : '${pageContext.request.contextPath}/on/chartAjax',
		type : 'get',
		datatype : "json",
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        data:{	
	        	targetYear: targetYear,
	            targetMonth: targetMonth,
	            memberId: memberId
        	 },
		success : function(model){
			console.log("ajax성공");
			
			// JSON 데이터를 객체로 변환
			const parsedData = JSON.parse(model);
			console.log(parsedData);
		      
			$.each(parsedData, function(index, item) {
				$('#target').append('<tr>');
		        $('#target').append('<td>' + item.category + '</td>');
		        $('#target').append('<td>' + item.sum + '</td>');
		        $('#target').append('</tr>');
		        
		        // chart 모델 생성
		        x.push(item.category);
		        y.push(item.sum);
		      });
		},
		error : function(){
			console.log("ajax실패");
		}
	});
	
	//const barColors = ["red", "green","blue","orange","brown"];
	new Chart("target2", {
		type: "bar",
		data: {
	   		labels: x,
	    	datasets: [{
	    		//backgroundColor: barColors,
	    		 backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)'
            ],
            borderWidth: 1,
			data: y,
	    	}]
		},
		options: {
			maxBarThickness: 50,
			scales: {
	            y: {
	                grace: '50%',
	                grid: {
	                    display: false,
	                },
	            },
			},
			plugins: {
				legend: {
		        	display: false
		        }
			},
		},
	});
});
</script>
</head>
<body>
<div class="container w70">
	<!-- navbar -->
	<nav>
		<a href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth }">Calendar</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/cashbookChart?targetYear=${targetYear}&targetMonth=${targetMonth }" class="is-current">Chart</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
	<br>
	<div class="p-t-5">
		<h1 class="dis">${targetYear}년 ${targetMonth+1}월 Chart</h1>
		<div class="dis_r">
			<!-- 오늘 날짜 chart로 이동 -->
			<a class="btn btn-sm b03 cl02 dis" href="${pageContext.request.contextPath}/on/cashbookChart?targetYear=${todayYear}&targetMonth=${todayMonth}">today</a>
			<!-- 이전 달, 다음 달 이동 -->			
			<div class="btn-group btn-group-sm">
				<a class="btn b02 cl02 dis" href="${pageContext.request.contextPath}/on/cashbookChart?targetYear=${targetYear}&targetMonth=${targetMonth-1}">&#10094;</a>
				<a class="btn b02 cl02 dis" href="${pageContext.request.contextPath}/on/cashbookChart?targetYear=${targetYear}&targetMonth=${targetMonth+1}">&#10095;</a>
			</div>
		</div>
	</div>
</div>
<div class="container w85">
	<br>
	<div class="row">
		<div class="col">
			<canvas class="dis_c" id="target2" style="width:30%; max-width:300px; max-height:300px"></canvas>
			<span class="dis_c">${targetYear}년 ${targetMonth+1}월 총 수입, 총 지출 </span>	
		</div>
		<div class="col">
			<canvas id="target3" style="width:30%; max-width:300px; max-height:300px"></canvas>
			<span>최근 3개월 소비 현황</span>	
		</div>
		<div class="col">
			<canvas id="target4" style="width:30%; max-width:300px; max-height:300px"></canvas>
			<span>${targetMonth+1}월 카테고리별 소비 현황</span>	
		</div>
	</div>
</div>
</body>
</html>