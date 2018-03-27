<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	function formChk() {
		alert("삭제가 완료되었습니다.");
	}
	jQuery(document).ready(
			function() {
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
	<div class="wrap" style="width: 100%; height: 100%">
		
		<%@include file="admin_nav.jsp" %>
		
		<!-- Insert -->
		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Insert Head -->
				<div class="row content_head">
					<!-- 제목 -->
					<div class="col-lg-3 content_title">
						<p>기기 등록</p>
					</div>
				</div>
				<!-- 입력 폼 -->
				<form method="post" action="DeviceAddOk">
					<div class="row content_body">
						<div class="col-lg-5">
							<label for="device_id">*기기 아이디</label>
							<!-- 기기 아이디 입력 -->
							<input type="text" class="form-control" name="deviceNumber">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<label for="device_kind">*기기 종류</label>
							<!-- 기기 종류 선택 -->
							<select name="sort" class="form-control">
								<option>포스터 기기</option>
								<option>환자 식별 기기</option>
							</select>
						</div>
						<div class="col-lg-5">
							<label for="device_version">*기기 버젼</label>
							<!-- 기기 버젼 입력 -->
							<input type="text" class="form-control" name="version">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="device_ip4">*IP_4</label>
							<!-- IP4 입력 -->
							<input type="text" class="form-control" name="ipv4_address">
						</div>
						<div class="col-lg-5">
							<label for="device_ip6">*IP_6</label>
							<!-- IP6 입력 -->
							<input type="text" class="form-control" name="ipv6_address">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-5">
							<label for="device_status">*설치 장소</label>
							<!-- 기기 설치 장소 입력 -->
							<input type="text" class="form-control" name="place">
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