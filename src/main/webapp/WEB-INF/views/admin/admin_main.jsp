<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/home.css">

<script src="<c:url value="/resources/js/bootstrap.js" /> "></script>
<script src="<c:url value="/resources/js/bootstrap.bundle.js" /> "></script>
<title>SMASH CARE MOVEMENT</title>
</head>
<body>
	<div class="wrap">
		<%
			String id = (String) session.getAttribute("id");
			String pwd = (String) session.getAttribute("pwd");
		%>
		<div class="my-auto mx-auto d-flex text-center wrap" style="width: 100%; height: 100%">
			<div class="container my-auto">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<p class="title text-uppercase">
							<span>manager</span> loging
						</p>
						<p class="sub_title_1"><%=id%>님 환영합니다.
							<%=id%>님께서는 현재 관리자로 로그인 하셨습니다.
						</p>
						<p class="sub_title_2">관리자로 로그인한 계정에는 다음과 같은 권한이 허용됩니다. 원하시는
							기능을 클릭해주세요.</p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4">
						<div class="menu_item" onclick="location.href='Device'">
							<img
								src="${pageContext.request.contextPath}/resources/img/home_icon1.png"
								width="230px" height="230px">
							<p class="item_title">기기 관리</p>
							<p class="item_sub_title">Device Management</p>
							<p class="item_contents">
								환자 식별 기기, 포스터 기기를<br>등록, 수정, 삭제, 검색합니다.
							</p>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="menu_item" onclick="location.href='Medic'">
							<img
								src="${pageContext.request.contextPath}/resources/img/home_icon2.png"
								width="230px" height="230px">
							<p class="item_title">계정 관리</p>
							<p class="item_sub_title">Account Management</p>
							<p class="item_contents">의료진의 계정을 등록, 수정 삭제, 검색합니다.</p>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="menu_item" onclick="location.href='Connection'">
							<img
								src="${pageContext.request.contextPath}/resources/img/home_icon3.png"
								width="230px" height="230px">
							<p class="item_title">기기 개통</p>
							<p class="item_sub_title">Device Opening</p>
							<p class="item_contents">
								포스터 기기를 개통하여 사용<br>가능한 상태롤 만듭니다.
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>



</html>
