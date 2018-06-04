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
					<c:forEach items="${patientList}" var="patientDto">

						<div class="col-lg-12 content_title">
							<p>${patientDto.patientName }환자</p>
						</div>
						<!-- 환자 정보 -->
						<div class="col-lg-3 content_detail">
							<p>환자 번호</p>
							<p>${patientDto.patientNumber }</p>
						</div>
						<div class="col-lg-3 content_detail">
							<p>병명</p>
							<p>${patientDto.patientDisease }</p>
						</div>
						<div class="col-lg-3 content_detail">
							<p>식별기기 아이디</p>
							<c:choose>
								<c:when test="${deviceNum eq NULL }">
									<p>연결기기 없음</p>
								</c:when>
								<c:otherwise>
									<p>${deviceNum }</p>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-lg-12 content_detail">
							<p>환자 상태</p>
							<hr>
							<p>${patientDto.patientStatus }</p>
						</div>
						<div class="col-lg-4 content_detail">
							<p>1번 프로그램</p>
							<hr>
							<p>${program_1 }</p>
						</div>
						<div class="col-lg-4 content_detail">
							<p>2번 프로그램</p>
							<hr>
							<p>${program_2 }</p>
						</div>
						<div class="col-lg-4 content_detail">
							<p>3번 프로그램</p>
							<hr>
							<p>${program_3 }</p>
						</div>
						<div class="col-lg-4 content_detail">
							<p>4번 프로그램</p>
							<hr>
							<p>${program_4 }</p>
						</div>
						<div class="col-lg-4 content_detail">
							<p>5번 프로그램</p>
							<hr>
							<p>${program_5 }</p>
						</div>
					</c:forEach>
				</div>
				<div class="row content_body">
					<!-- 운동 진행 프로그래스바 -->
					<div class="col-lg-4 exercise_progress">
						<p class="body_title">총 운동 진행 상황</p>
						<hr>
						<div class="item">
							<canvas id="myPieChart" width="500px" height="500px"></canvas>
							<!-- 운동 진행 입력 -->
							<p id="ex_amount">currentExer</p>
						</div>
					</div>

					<!-- 그래프 -->
					<div class="col-lg-6 exercise_amount">
						<p class="body_title">지난 한주간의 운동량</p>
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
		var dataList = new Array();
		var labelsList = new Array();
		
		<c:forEach var="map" items="${map}">
		labelsList.push("${map.key}");	
		dataList.push("${map.value}");
		</c:forEach>

		
		var tot = ${totalExer };	// 총 운동량
		var ex = ${doExer} // 실행한 운동량

		Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
		Chart.defaults.global.defaultFontColor = '#292b2c';

		var ctx = document.getElementById("myPieChart");
		var myPieChart = new Chart(ctx, {
			type : 'pie',
			data : {
				labels : [ "현재 운동량", "부족 운동량" ],
				datasets : [ {
					data : [ ex, tot - ex ],
					backgroundColor : [ '#004593', 'rgba(2,117,216,0.2)' ],
				} ],
			},
		});

		var ctx = document.getElementById("myAreaChart");
		var myLineChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : labelsList,
				datasets : [ {
					label : '운동량',
					data : dataList,
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
							max : ${cnt }
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