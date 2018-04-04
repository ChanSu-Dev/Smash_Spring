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
	href="${pageContext.request.contextPath}/resources/css/table.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.bundle.js" /> "></script>
<title>SMASH CARE MOVEMENT</title>
<script>
	function formChk() {
		alert("삭제가 완료되었습니다.");
	}
	function profile() {
		document.getElementById("profile").submit();
	}
	jQuery(document).ready(function() {
		/* 검색창 포커스인 포커스 아웃 효과 */
		$('input').focusin(function() {
			$('.search').css('border-color', '#00afec');
			$('.search').css('box-shadow', '0 0 15px rgba(0, 175, 236, 0.5)');

			$('input').focusout(function() {
				$('.search').css('border-color', '#e0e0e0');
				$('.search').css('box-shadow', 'none');
			});
		});

		/* 모달 제목 자동 설정 */

		$('.patient_manage a').click(function() {
			$('#profile').submit();
		});

		/* 검색 기능 */
		var search = function() {
			var id = $('input').val();
			$('tbody tr').hide();
			var item = $("tbody > tr > td:nth-child(5n+2) > input");
			item.each(function() {
				var text = $(this).val();
				if (text.indexOf(id) != -1) {
					$(this).parent().parent().show();
				}
			})
		};
		/* 버튼 눌렀을때 검색 */
		$('#search_btn').click(function() {
			search();
		});
		/* 엔터 눌렀을때 검색 */
		$('input').keydown(function(key) {
			if (key.keyCode == 13) {
				search();
			}
		});
	});
</script>
</head>
<body>
	<div class="wrap" style="width: 100%;">

		<%@include file="medic_nav.jsp"%>

		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Table Head -->
				<div class="row content_head">
					<!-- 제목 -->
					<div class="col-lg-3 content_title">
						<p>환자 관리</p>
					</div>
					<!-- 검색 -->
					<div class="col-lg-7">
						<div class="search">
							<input type="text" placeholder="환자 이름을 입력하세요."> <input
								type="submit" class="my-auto mx-auto search_btn" id="search_btn"
								value="">
						</div>
					</div>
					<!-- 등록 -->
					<div class="col-lg-2">
						<button class="add_btn" onclick="location.href='PatientAdd'">+
							등록하기</button>
					</div>
				</div>
			</div>

			<!-- Table Body -->
			<table class="table patient_manage">
				<thead>
					<tr>
						<th>환자 번호</th>
						<th>환자 이름</th>
						<th>병명</th>
						<th>등록기기</th>
						<th>수정 / 삭제</th>
					</tr>
				</thead>
				<!-- 테이블 내용 -->

			</table>
		</div>
		<div class="modal fade" id="Modal" tabindex="-1" role="dialog"
			aria-labelledby="ModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<!-- 모달 제목으로 환자이름 자동 설정 -->
						<h5 class="modal-title" id="ModalLabel">
							<span class="patient_name">Modal title</span>
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<canvas id="myAreaChart" width="100%" height="30"></canvas>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script>
		// Chart.js scripts
		Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
		Chart.defaults.global.defaultFontColor = '#292b2c';
		// -- Area Chart Example
		var ctx = document.getElementById("myAreaChart");

		var myLineChart = new Chart(ctx, {
			type : 'line',
			data : {
				/* x축 항목 */
				labels : [ "2017-04-20 10:10:10", "2017-04-20 10:10:20",
						"2017-04-20 10:10:30", "2017-04-20 10:10:40",
						"2017-04-20 10:10:50" ],
				datasets : [ {
					label : "Sessions",
					lineTension : 0.0,
					backgroundColor : "rgba(2,117,216,0.2)",
					borderColor : "rgba(2,117,216,1)",
					pointRadius : 5,
					pointBackgroundColor : "rgba(2,117,216,1)",
					pointBorderColor : "rgba(255,255,255,0.8)",
					pointHoverRadius : 5,
					pointHoverBackgroundColor : "rgba(2,117,216,1)",
					pointHitRadius : 20,
					pointBorderWidth : 2,
					/* 데이터 값 */
					data : [ 3, 2, 6, 5, 6, 7 ],
				} ],
			},
			options : {
				scales : {
					xAxes : [ {
						time : {
							unit : 'date'
						},
						gridLines : {
							display : false
						},
						ticks : {
							maxTicksLimit : 7
						}
					} ],
					yAxes : [ {
						ticks : {
							/* y축 항목 */
							min : 0,
							max : 10,
							maxTicksLimit : 1
						},
						gridLines : {
							color : "rgba(0, 0, 0, .125)",
						}
					} ],
				},
				legend : {
					display : false
				}
			}
		});
	</script>
</body>
</html>