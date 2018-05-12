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
						<p>환자 정보 수정</p>
					</div>
				</div>
				<!-- 입력 폼 -->
				<form method="post" action="PatientEditOk">
					<c:forEach items="${list}" var="dto">
						<div class="row content_body">
							<div class="col-lg-5">
								<label for="patient_num">*환자 번호</label>
								<!-- 환자 번호 입력 -->
								<input type="text" class="form-control" name="patientNumber"
									value="${dto.patientNumber }" readonly="readonly">
							</div>
							<div class="col-lg-5 col-lg-offset-1">
								<label for="patient_name">*환자 이름</label>
								<!-- 환자 이름 입력 -->
								<input type="text" class="form-control" name="patientName"
									value="${dto.patientName }">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-5">
								<label for="patient_disease">*병명</label>
								<!-- 병명 입력 -->
								<input type="text" class="form-control" name="patientDisease"
									value="${dto.patientDisease }">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-10">
								<label for="patient_status">*상태</label>
								<!-- 환자 상태 입력 -->
								<input type="text" class="form-control" name="patientStatus"
									value="${dto.patientStatus }">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-4">
								<label for="patient_status">*1번 프로그램</label>
								<!-- 환자 운동프로그램 입력 -->
								<select name="program_1" class="form-control">
									<c:forEach items="${Plist }" var="Pdto">
										<c:choose>
											<c:when test="${Pdto.programNumber eq dto.program_1}">
												<option value=${Pdto.programNumber } selected>${Pdto.name }
												</option>
											</c:when>
											<c:otherwise>
												<option value=${Pdto.programNumber }>${Pdto.name }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-4">
								<label for="patient_status">*2번 프로그램</label>
								<!-- 환자 운동프로그램 입력 -->
								<select name="program_2" class="form-control">
									<option value="NULL">미선택</option>
									<c:forEach items="${Plist }" var="Pdto">
										<c:choose>
											<c:when test="${Pdto.programNumber eq dto.program_2}">
												<option value=${Pdto.programNumber } selected>${Pdto.name }
												</option>
											</c:when>
											<c:otherwise>
												<option value=${Pdto.programNumber }>${Pdto.name }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-4">
								<label for="patient_status">*3번 프로그램</label>
								<!-- 환자 운동프로그램 입력 -->
								<select name="program_3" class="form-control">
									<option value="NULL">미선택</option>
									<c:forEach items="${Plist }" var="Pdto">
										<c:choose>
											<c:when test="${Pdto.programNumber eq dto.program_3}">
												<option value=${Pdto.programNumber } selected>${Pdto.name }
												</option>
											</c:when>
											<c:otherwise>
												<option value=${Pdto.programNumber }>${Pdto.name }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="offset-10 col-lg-2">
								<!-- 제출하기 -->
								<input type="submit" class="submit" value="+ 수정하기">
							</div>
						</div>
					</c:forEach>
				</form>
			</div>
		</div>

	</div>
</body>
</html>