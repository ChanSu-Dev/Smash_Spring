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
	href="${pageContext.request.contextPath}/resources/css/table.css">
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
	<div class="wrap" style="width: 100%">

		<%@include file="admin_nav.jsp"%>

		<div class="content mx-auto">
			<div class="container my-auto">
				<!-- Table Head -->
				<div class="row content_head">
					<!-- 제목 -->
					<div class="col-lg-3 content_title">
						<p>기기 관리</p>
					</div>
					<!-- 검색 -->
					<div class="col-lg-7">
						<div class="search">
							<input type="text" placeholder="기기 아이디를 입력하세요."> <input
								type="submit" class="my-auto mx-auto search_btn" id="search_btn"
								value="">
						</div>
					</div>
					<!-- 등록 -->
					<div class="col-lg-2">
						<button class="add_btn" onclick="location.href='DeviceAdd'">+
							등록하기</button>
					</div>
				</div>
			</div>

			<!-- Table Body -->
			<table class="table device_manage">
				<thead>
					<tr>
						<th>기기 아이디</th>
						<th>기기 종류</th>
						<th>기기 상태</th>
						<th>수정 / 삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="dto">
						<tr>
							<c:choose>
								<c:when test="${dto.sort == '포스터 기기'}">
									<td><img
										src="${pageContext.request.contextPath}/resources/img/poster_icon.png"
										width="30px" height="30px">${dto.deviceNumber }</td>
									<td style="color: rgb(248, 181, 0);">${dto.sort}</td>
								</c:when>
								<c:otherwise>
									<td><img
										src="${pageContext.request.contextPath}/resources/img/man_icon.png"
										width="30px" height="30px">${dto.deviceNumber }</td>
									<td style="color: rgb(127, 190, 38);">${dto.sort}</td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${dto.activated == 0}">
									<td>비활성화</td>
								</c:when>
								<c:otherwise>
									<td>활성화</td>
								</c:otherwise>
							</c:choose>
							<td>
								<form method="post" action="DeviceEdit">
									<input type="hidden" value="${dto.deviceNumber}"
										name="deviceNumber"> <input type="hidden" value="edit"
										name="type"> <input type='submit' class="btn_enable"
										value="수정하기" />
								</form>
								<form id="delete" method="post" action="DeviceDelete">
									<input type="hidden" value="${dto.deviceNumber}"
										name="deviceNumber"> <input type="hidden"
										value="delete" name="type"> <input type="submit"
										class="btn_enable" value="삭제하기" onClick="javascript:formChk()">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>