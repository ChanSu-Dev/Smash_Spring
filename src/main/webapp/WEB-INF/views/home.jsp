<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
	
	<script src="<c:url value="/resources/js/bootstrap.js" /> "></script>
	<script src="<c:url value="/resources/js/bootstrap.bundle.js" /> "></script>

	<title>SMASH CARE MOVEMENT</title>
</head>
<body>
	<div class="my-auto mx-auto d-flex text-center wrap" style="width: 100%; height: 100%">
           <div class="container my-auto">
               <div class="row">
                   <div class="col-lg-2 mx-auto">
                       <img src="${pageContext.request.contextPath}/resources/img/login_logo.png" height="130px">
                   </div>
               </div>
               <div class="row">
                   <div class="col-lg-10 mx-auto">
                       <p class="title text-uppercase"><span>smash</span> care movement</p>
                       <p class="sub_title">등록하신 아이디와 비밀번호를 입력해주세요.</p>
                   </div>
               </div>
               <div class="row">
                   <div class="col-lg-12 mx-auto box">
                       <form method="post" action="loginCheck">
                           <table class="mx-auto">
                               <tr>
                                   <td class="input_name ">USER ID</td>
                                   <td><input type="text" name="id" class="form-control"></td>
                               </tr>
                               <tr><td colspan="2" class="space_1"></td></tr>
                               <tr>
                                   <td class="input_name">PASSWORD</td>
                                   <td><input type="password" name="pwd" class="form-control"></td>
                               </tr>
                               <tr><td colspan="2" class="space_2"></td></tr>
                               <tr>
                                   <td colspan="2" class="submit"><input type="submit" value="Login" class="btn btn-primary"></td>
                               </tr>
                           </table>
                       </form>
                   </div>
               </div>
           </div>
        </div>
</body>
</html>
