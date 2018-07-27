<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SMASH CARE MOVEMENT</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/add.css">

<!-- JQuery -->
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.bundle.js" /> "></script>
<script>
	function isPwdEquals(pwd1, pwd2) {
		if (pwd1 == pwd2 && pwd1 != "" && pwd2 != "") {
			$.ajax({
				type : 'POST',
				url : './MedicAddOk',
				data : {
					'employeeNumber' : $('input[name=employeeNumber]').val(),
					'id' : $('input[name=id]').val(),
					'password' : $('input[name=password]').val(),
					'name' : $('input[name=name]').val(),
					'belong' : $('input[name=belong]').val(),
					'contact' : $('input[name=contact]').val(),
					'address' : $('input[name=address]').val(),
					'birth' : $('input[name=birth]').val(),
					'type' : $('#regType').val()
				},
				success : function(data) {
					window.location.href = "./Medic"
				},
				error : function(xhr, status, error) {
					alert("입력한 정보를 다시한번 확인해주세요!");
				}
			});
		} else {
			alert("입력한 정보를 다시한번 확인해주세요!");
		}
	}
</script>
<script>
	function formChk() {
		alert("삭제가 완료되었습니다.");
	}
	jQuery(document).ready(
			function() {
				$('.addForm').submit(function(e) {
					e.preventDefault();
					var pwd1 = $('input[name=password]').val();
					var pwd2 = $('input[name=password2]').val();
					isPwdEquals(pwd1, pwd2);
				});
				/* 검색창 포커스인 포커스 아웃 효과 */
				$('input').focusin(
						function() {
							$('.search').css('border-color', '#00afec');
							$('.search').css('box-shadow',
									'0 0 15px rgba(0, 175, 236, 0.5)');

							$('input').focusout(function() {
								$('.search').css('border-color', '#e0e0e0');
								$('.search').css('box-shadow', 'none');
							});
						});

				/* 검색 기능 */
				var search = function() {
					var id = $('input').val();
					$('tbody tr').hide();
					var item = $("tbody > tr > td:nth-child(4n+1):contains('"
							+ id + "')");

					$(item).parent().show();
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

		<%@include file="admin_nav.jsp"%>

		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Insert Head -->
				<div class="row content_head">
					<!-- 제목 -->
					<div class="col-lg-3 content_title">
						<p>계정 등록</p>
					</div>
				</div>
				<!-- 입력 폼 -->
				<form class="addForm">
					<div class="row content_body">
						<div class="col-lg-5">
							<label for="doctor_num">*의료진 번호</label>
							<!-- 의료진 번호 입력 -->
							<input type="text" class="form-control" name="employeeNumber">
						</div>
						<div class="col-lg-5">
							<label for="doctor_name">*의료진 이름</label>
							<!-- 의료진 이름 입력 -->
							<input type="text" class="form-control" name="name">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="doctor_id">*계정 아이디</label>
							<!-- 의료진 아이디 입력 -->
							<input type="text" class="form-control" name="id">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="doctor_password">*계정 비밀번호</label>
							<!-- 의료진 비밀번호 입력 -->
							<input type="password" class="form-control" name="password">
						</div>
						<div class="col-lg-5">
							<label for="doctor_password">*비밀번호 확인</label>
							<!-- 의료진 비밀번호 입력 -->
							<input type="password" class="form-control" name="password2">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="doctor_part">*소속 부서</label>
							<!-- 의료진 부서 입력 -->
							<input type="text" class="form-control" name="belong">
						</div>
						<div class="col-lg-5">
							<label for="doctor_tell">*연락처</label>
							<!-- 의료진 연락처 입력 -->
							<input type="text" class="form-control" name="contact">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-10">
							<label for="doctor_adress">*주소</label>
							<!-- 의료진 주소 입력 -->
							<input type="text" class="form-control" name="address">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="doctor_adress">*생년월일</label>
							<!-- 의료진 주소 입력 -->
							<input type="date" class="form-control" name="birth">
						</div>
						<div class="col-lg-5">
							<label for="doctor_adress">*종류</label> <select id="regType" name="type"
								class="form-control">
								<option>주치의</option>
								<option>운동 코디네이터</option>
							</select>
						</div>

					</div>
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