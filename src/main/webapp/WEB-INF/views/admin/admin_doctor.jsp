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
	href="${pageContext.request.contextPath}/resources/css/table.css">

<!-- JQuery -->
<script src="js/jquery-3.2.1.min.js"></script>
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

		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg fixed-top">
		<div class="container">
			<!-- 브랜드 로고 -->
			<div class="navbar-brand">
				<img
					src="${pageContext.request.contextPath}/resources/img/nav_brand.png"
					width="300px">
			</div>
			<!-- 메뉴 -->
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
						<!-- 홈 링크 --> <a class="nav-link currnet"
						onclick="location.href='adminMain'">홈으로</a>
					</li>
					<li class="nav-item">
						<!-- 기기 관리 링크 --> <a class="nav-link"
						onclick="location.href='adminDevice'">기기 관리</a>
					</li>
					<li class="nav-item">
						<!-- 계정 관리 링크 --> <a class="nav-link"
						onclick="location.href='adminDoctor'">계정 관리</a>
					</li>
					<li class="nav-item active">
						<!-- 기기 개통 링크 --> <a class="nav-link"
						onclick="location.href='adminConnection'">기기 개통</a>
					</li>
				</ul>
				<!-- 계정 아이디 -->
				<div class="my-auto profile">
					<img
						src="${pageContext.request.contextPath}/resources/img/defualt_profile.png"
						class="rounded-circle my-auto" width="26px" height="26px">
					<!-- 관리자 비밀번호 변경 링크 -->
					<a id="dropdownMenu" data-target="#" data-toggle="dropdown"
						aria-haspopup="true" class="nav-link my-auto dropdown-toggle"
						href="#"> <%=session.getAttribute("id")%> <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu">
						<li><a href="/adminpass">비밀번호 변경</a></li>
						<li><a href="/logout">로그아웃</a></li>
					</ul>
				</div>
			</div>
		</div>
		</nav>

		<!-- Table -->
		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Table Head -->
				<div class="row content_head">
					<!-- 제목 -->
					<div class="col-lg-3 content_title">
						<p>계정 관리</p>
					</div>
					<!-- 검색 -->
					<div class="col-lg-7">
						<div class="search">
							<input type="text" placeholder="의료진 이름을 입력하세요."> <input
								type="submit" class="my-auto mx-auto search_btn" id="search_btn"
								value="">
						</div>
					</div>
					<!-- 등록 -->
					<div class="col-lg-2">
						<button class="add_btn" onclick="location.href='/doctor_add'">+
							등록하기</button>
					</div>
				</div>
			</div>

			<!-- Table Body -->
			<table class="table doctor_manage">
				<thead>
					<tr>
						<th>의료진 이름</th>
						<th>담당 부서</th>
						<th>연락처</th>
						<th>수정 / 삭제</th>
					</tr>
				</thead>
				<!-- 테이블 내용 -->
				<tbody>

					<!-- Sample -->
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>