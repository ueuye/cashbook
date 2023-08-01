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
	
	const targetYear = <c:out value = "${targetYear}"/>;
	const targetMonth = <c:out value = "${targetMonth}"/>;
	const memberId = "<c:out value = "${memberId}"/>";
	
	// 월별 총 수입, 총 지출 
	const x = [];
	const y = [];
	
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
		        // chart 모델 생성
		        x.push(item.category);
		        y.push(item.sum);
		      });
		},
		error : function(){
			console.log("ajax실패");
		}
	});
	
	new Chart("target", {
		type: "bar",
		data: {
	   		labels: x,
	    	datasets: [{
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
	// 최근 3개월 소비 현황 
	const x2 = [];
	const y2 = [];
	
	$.ajax({
		async : false, // true : 비동기(기본값), false : 동기
		url : '${pageContext.request.contextPath}/on/chart2Ajax',
		type : 'get',
		datatype : "json",
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        data:{	
	        	targetYear: targetYear,
	            targetMonth: targetMonth,
	            memberId: memberId
        	 },
		success : function(model){
			console.log("ajax2성공");
			
			// JSON 데이터를 객체로 변환
			const parsedData2 = JSON.parse(model);
			console.log(parsedData2);
		      
			$.each(parsedData2, function(index, item) {
		        // chart 모델 생성
		        x2.push(item.month);
		        y2.push(item.sum);
		      });
		},
		error : function(){
			console.log("ajax실패");
		},
	});
	
	new Chart("target2", {
		type: "line",
		data: {
	   		labels: x2,
	    	datasets: [{
	    		 backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.5)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderWidth: 1,
			data: y2,
			pointStyle: 'circle',
		    pointRadius: 10,
		    pointHoverRadius: 15
	    	}]
		},
		options: {
			plugins: {
				legend: {
		        	display: false
		        }
			},
		},
		
	});
	
	// 월별, 카테고리별 소비 현황 
	const x3 = []; 
	const y3 = []; 
	
	$.ajax({
		async : false, // true : 비동기(기본값), false : 동기
		url : '${pageContext.request.contextPath}/on/chart3Ajax',
		type : 'get',
		datatype : "json",
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        data:{	
	        	targetYear: targetYear,
	            targetMonth: targetMonth,
	            memberId: memberId
        	 },
		success : function(model){
			console.log("ajax3성공");
			
			// JSON 데이터를 객체로 변환
			const parsedData3 = JSON.parse(model);
			console.log(parsedData3);
		      
			$.each(parsedData3, function(index, item) {
		        // chart 모델 생성
		        x3.push(item.subcategory);
		        y3.push(item.sum);
		      });
		},
		error : function(){
			console.log("ajax실패");
		},
		options: {
			responsive: true,
			plugins: {
				legend: {
					display: 'false',
				},
			}
		},
	});
	
	new Chart("target3", {
		type: "pie",
		data: {
	   		labels: x3,
	    	datasets: [{
	    		backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                    'rgba(75, 192, 192, 0.5)',
                    'rgba(153, 102, 255, 0.5)',
                ],
                borderColor: ['rgb(255, 99, 132,1.5)',
                    'rgba(54, 162, 235, 1.5)',
                    'rgba(255, 206, 86, 1.5)',
                    'rgba(75, 192, 192, 1.5)',
                    'rgba(153, 102, 255, 1.5)',
               	],
            borderWidth: 1,
			data: y3,
	    	}]
		},
		options: {
			responsive: true,
			plugins: {
				legend: {
					position: 'bottom',
				},
			},
			maintainAspectRatio :false
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
		<a href="${pageContext.request.contextPath}/on/cashbookChart?targetYear=${targetYear}&targetMonth=${targetMonth }" class="is-current">Chart</a>
		<a href="${pageContext.request.contextPath}/on/memberOne">MyPage</a>
		<a href="${pageContext.request.contextPath}/on/logout">Logout</a>
		<div class="nav-underline"></div>
	</nav>
</div>
<br>
<div class="container w80">
	<div class="p-t-5 p-b-30">
		<h1 class="dis">${strTargetMonth} ${targetYear}</h1>
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
<br>
<div class="container w85 shadow p-tb-50 bt">
	<br>
	<div class="row">
		<div class="col dis p-l-40 p-r-30">
			<canvas class="dis_c" id="target" style="width:30%; height:80%; max-height:300px"></canvas>
			<br>
			<span class="dis_c">${targetYear}년 ${targetMonth+1}월 총 수입, 총 지출 </span>	
		</div>
		<div class="col dis p-l-30 p-r-30">
			<canvas class="dis_c" id="target2" style="width:30%; height:80%; max-height:300px"></canvas>
			<br>
			<span class="dis_c">최근 3개월 소비 현황</span>	
		</div>
		<div class="col dis p-l-30 p-r-40">
			<canvas class="dis_c" id="target3" style="width:30%; height:80%; max-height:300px"></canvas>
			<br>
			<span class="dis_c">${targetMonth+1}월 카테고리별 소비 현황</span>	
		</div>
	</div>
</div>
</body>
</html>