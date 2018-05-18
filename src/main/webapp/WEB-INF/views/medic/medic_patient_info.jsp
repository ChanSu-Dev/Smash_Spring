<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/profile.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.bundle.js" /> "></script>
<script src="<c:url value="/resources/js/chart/Chart.js" /> "></script>

<title>SMASH CARE MOVEMENT</title>
</head>
<body>
	<div class="wrap" style="width: 100%;">

		<%@include file="medic_nav.jsp"%>

		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Profile Head -->
				<div class="row content_head">
					<!-- 환자 이름 -->
					<div class="col-lg-12 content_title">
						<p>환자이름 </p>
					</div>
					<!-- 환자 정보 -->
					<div class="col-lg-3 content_detail">
						<p>환자 번호</p>
						<p>patientNumber</p>
					</div>
					<div class="col-lg-3 content_detail">
						<p>병명</p>
						<p>disease</p>
					</div>
					<div class="col-lg-3 content_detail">
						<p>식별기기 아이디</p>
						<p>deviceNumber</p>
					</div>
					<div class="col-lg-12 content_detail">
						<p>환자 상태</p>
						<hr>
						<p>status</p>
					</div>
				</div>
				<div class="row content_body">
					<!-- 운동 진행 프로그래스바 -->
					<div class="col-lg-4 exercise_progress">
						<p class="body_title">현재 운동 진행 상황</p>
						<hr>
						<div class="item">
							<canvas id="myPieChart" width="500px" height="500px"></canvas>
							<!-- 운동 진행 입력 -->
							<p id="ex_amount">currentExer</p>
						</div>
					</div>

					<!-- 그래프 -->
					<div class="col-lg-6 exercise_amount">
						<p class="body_title">운동량</p>
						<hr>
						<canvas id="myAreaChart" width="100%" height="40"> </canvas>
						<div class="data">
							<!-- 데이터 테이블 -->
							<table>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script>
		// 데이터 추출
		var data1 = $('#date1').text();
		var data2 = $('#date2').text();
		var data3 = $('#date3').text();
		var data4 = $('#date4').text();
		var data5 = $('#date5').text();
		var data6 = $('#date5').text();
		var data7 = $('#date5').text();

		var label1 = Number.parseInt($('#data1').text());
		var label2 = Number.parseInt($('#data2').text());
		var data3 = Number.parseInt($('#data3').text());
		var data4 = Number.parseInt($('#data4').text());
		var data5 = Number.parseInt($('#data5').text());
		var data5 = Number.parseInt($('#data5').text());
		var data5 = Number.parseInt($('#data5').text());

		// Chart.js scripts
		// -- Set new default font family and font color to mimic Bootstrap's default styling
		Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
		Chart.defaults.global.defaultFontColor = '#292b2c';
		// -- Area Chart Example
		var ex = 30 // 운동량
		var ctx = document.getElementById("myPieChart");
		var myPieChart = new Chart(ctx, {
			type : 'pie',
			data : {
				labels : [ "현재 운동량", "전체 운동량" ],
				datasets : [ {
					data : [ ex, 100 - ex ],
					backgroundColor : [ '#004593', 'rgba(2,117,216,0.2)' ],
				} ],
			},
		});

		var ctx = document.getElementById("myAreaChart");
		var myLineChart = new Chart(ctx,
				{
					type : 'line',
					data : {
						labels : [ "05-13", "05-14", "05-15", "05-16", "05-17",
								"05-18" , "05-19" ],
						datasets : [ {
							label : '운동량',
							data : [ 20, 40, 50, 60, 80, 10, 60 ],
							lineTension : 0.0,
							backgroundColor : "rgba(2,117,216,0.2)",
							borderColor : "#004593",
							pointRadius : 3,
							pointBackgroundColor : "#004593",
							pointBorderColor : "#004593",
							pointHoverRadius : 5,
							pointHoverBackgroundColor : "rgba(2,117,216,1)",
							pointHitRadius : 20,
							pointBorderWidth : 0
						} ]
					},
					options : {
						scales : {
							yAxes : [ {
								ticks : {
									min : 0,
									max : 100
								},
								gridLines : {
									color : "rgba(0, 0, 0, .125)",
								}
							} ]
						}
					},
					legend : {
						display : false
					}
				});
	</script>
</body>
</html>