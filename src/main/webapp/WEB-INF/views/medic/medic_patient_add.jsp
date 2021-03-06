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
	href="${pageContext.request.contextPath}/resources/css/add.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.bundle.js" /> "></script>
<title>SMASH CARE MOVEMENT</title>
</head>
<body>
	<div class="wrap" style="width: 100%; height: 100%">

		<%@include file="medic_nav.jsp"%>

		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Insert Head -->
				<div class="row content_head">
					<!-- 제목 -->
					<div class="col-lg-3 content_title">
						<p>환자 등록</p>
					</div>
				</div>

				<!-- 입력 폼 -->
				<form method="post" action="PatientAddOK">
					<div class="row content_body">
						<div class="col-lg-5">
							<label for="patient_num">*환자 번호</label>
							<!-- 환자 번호 입력 -->
							<input type="text" class="form-control" name="patientNumber">
						</div>
						<div class="col-lg-5 col-lg-offset-1">
							<label for="patient_name">*환자 이름</label>
							<!-- 환자 이름 입력 -->
							<input type="text" class="form-control" name="patientName">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="patient_disease">*병명</label>
							<!-- 병명 입력 -->
							<input type="text" class="form-control" name="patientDisease">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-10">
							<label for="patient_status">*상태</label>
							<!-- 환자 상태 입력 -->
							<input type="text" class="form-control" name="patientStatus">
						</div>
					</div>

					<div class="row">
						<div class="col-lg-10">
							<label for="patient_codi">*운동 코디네이터 지정</label> <select
								name="patientCodi" class="form-control">
								<c:forEach items="${codiList }" var="list">
									<option value=${list.employeeNumber}>${list.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<%-- 
					<div class="row">
						<div class="col-lg-4">
							<label for="patient_status">*1번 프로그램</label>
							<!-- 환자 운동프로그램 입력 -->
							<select name="program_1" class="form-control" ${isDisabled }>
								<option value="0">미선택</option>
								<c:forEach items="${list }" var="Pno">
									<option value=${Pno.programNumber}>${Pno.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-4">
							<label for="patient_status">*2번 프로그램</label>
							<!-- 환자 운동프로그램 입력 -->
							<select name="program_2" class="form-control" ${isDisabled }>
								<option value="0">미선택</option>
								<c:forEach items="${list }" var="Pno">
									<option value=${Pno.programNumber}>${Pno.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-4">
							<label for="patient_status">*3번 프로그램</label>
							<!-- 환자 운동프로그램 입력 -->
							<select name="program_3" class="form-control" ${isDisabled }>
								<option value="0">미선택</option>
								<c:forEach items="${list }" var="Pno">
									<option value=${Pno.programNumber}>${Pno.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4">
							<label for="patient_status">*4번 프로그램</label>
							<!-- 환자 운동프로그램 입력 -->
							<select name="program_4" class="form-control" ${isDisabled }>
								<option value="0">미선택</option>
								<c:forEach items="${list }" var="Pno">
									<option value=${Pno.programNumber}>${Pno.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-4">
							<label for="patient_status">*5번 프로그램</label>
							<!-- 환자 운동프로그램 입력 -->
							<select name="program_5" class="form-control" ${isDisabled }>
								<option value="0">미선택</option>
								<c:forEach items="${list }" var="Pno">
									<option value=${Pno.programNumber}>${Pno.name }</option>
								</c:forEach>
							</select>
						</div>
					</div> --%>
					<div class="row">
						<div class="offset-10 col-lg-2">
							<!-- 제출하기 -->
							<input type="submit" class="submit" value="+ 등록하기">
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</body>
</html>