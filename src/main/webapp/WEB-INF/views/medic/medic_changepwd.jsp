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
</head>
<body>
	<div class="wrap" style="width: 100%;">

		<%@include file="medic_nav.jsp"%>

		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Insert Head -->
				<div class="row content_head">
					<!-- 제목 -->
					<div class="col-lg-4 content_title">
						<p>비밀번호 변경</p>
					</div>
				</div>
				<!-- 입력 폼 -->
				<form method="post">
					<div class="row content_body">
						<div class="col-lg-5">
							<label for="patient_num">현재 비밀번호</label>
							<!-- 새 비밀번호 입력 -->
							<input type="password" class="form-control" name="check">
						</div>
					</div>
					<div class="row content_body">
						<div class="col-lg-5">
							<label for="patient_num">새 비밀번호</label>
							<!-- 새 비밀번호 입력 -->
							<input type="password" class="form-control" name="password">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="patient_disease">비밀번호 확인</label>
							<!-- 비밀번호 확인 -->
							<input type="password" class="form-control" name="passwordcheck">
						</div>
					</div>
					<div class="row">
						<div class="offset-10 col-lg-2">
							<!-- 변경하기 -->
							<input type="submit" class="submit" value="변경 완료">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>