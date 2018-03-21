<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Navigation -->
		<nav class="navbar navbar-expand-lg fixed-top">
		<div class="container">
			<!-- 브랜드 로고 -->
			<div class="navbar-brand">
				<img src="${pageContext.request.contextPath}/resources/img/nav_brand.png" width="300px">
			</div>
			<!-- 메뉴 -->
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
						<!-- 홈 링크 --> <a class="nav-link currnet"
						onclick="location.href='Main'">홈으로</a>
					</li>
					<li class="nav-item">
						<!-- 계정 관리 링크 --> <a class="nav-link"
						onclick="location.href='Patient'">환자 관리</a>
					</li>
					<li class="nav-item active">
						<!-- 기기 개통 링크 --> <a class="nav-link"
						onclick="location.href='Device'">기기 개통</a>
					</li>
				</ul>
				<!-- 계정 아이디 -->
				<div class="my-auto profile">
					<img src="${pageContext.request.contextPath}/resources/img/defualt_profile.png" class="rounded-circle my-auto"
						width="26px" height="26px">
					<!-- 관리자 비밀번호 변경 링크 -->
					<a id="dropdownMenu" data-target="#" data-toggle="dropdown"
						aria-haspopup="true" class="nav-link my-auto dropdown-toggle"
						href="#"> <%=session.getAttribute("id")%> <span
						class="caret"></span></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu">
						<li><a href="/changemedic">개인정보 변경</a></li>
						<li><a href="/logout">로그아웃</a></li>
					</ul>
				</div>
			</div>
		</div>
		</nav>